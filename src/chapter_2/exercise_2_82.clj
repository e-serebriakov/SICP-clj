(ns chapter-2.exercise-2-82
  (:require
   [chapter-2.chapter-2 :refer [contents get-coercion get-op type-tag]]))

(comment
  "Show how to generalize apply-generic to handle coercion in the general case of multiple arguments.
  One strategy is to attempt to coerce all the arguments to the type of the first argument, 
  then to the type of the second argument, and so on. Give an example of a situation
  where this strategy (and likewise the two-argument version given above) 
  is not sufficiently general. (Hint: Consider the case where there are some suitable mixed-type
  operations present in the table that will not be tried.)"

  (defn apply-generic [op & args]
    (let [type-tags (map type-tag args)
          proc (get-op op type-tags)]
      (if proc
        (apply proc (map contents args))
        (if (= (count type-tags) 2)
          (let [[type1 type2] type-tags
                [a1 a2] args
                t1->t2 (get-coercion type1 type2)
                t2->t1 (get-coercion type2 type1)]
            (cond
              t1->t2 (apply-generic op (t1->t2 a1) a2)
              t2->t1 (apply-generic op a1 (t2->t1 a2))
              :else (throw (ex-info "No method for these types: APPLY-GENERIC"
                                    {:op op :type-tags type-tags}))))
          (throw (ex-info "No method for these types: APPLY-GENERIC"
                          {:op op :type-tags type-tags})))))))

(defn try-coerce-all [args target-type get-coercion]
  (when (->> args
             (map #(if (= (type-tag %) target-type)
                     %
                     (some-> (get-coercion (type-tag %) target-type)
                             (apply [%]))))
             (every? some?))
    args))

(defn apply-generic [op & args]
  (let [type-tags (map type-tag args)
        proc (get-op op type-tag)]
    (or
     (and proc (apply proc (map contents args)))
     (->> type-tags
          distinct
          (reduce (fn [_ target-type]
                    (when-let [result (some->> (try-coerce-all args target-type get-coercion)
                                               (apply apply-generic op))]
                      (reduced result)))
                  nil))
     (throw (ex-info "No method for these types"
                     {:op op :types type-tags})))))


