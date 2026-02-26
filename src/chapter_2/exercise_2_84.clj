(ns chapter-2.exercise-2-84
  (:require
   [chapter-2.chapter-2 :refer [contents get-op install-complex-package
                                install-integer-package
                                install-rational-package make-integer
                                make-rational type-tag]]
   [chapter-2.exercise-2-83 :refer [install-raise-package raise]]))

(comment
  "Using the raise operation of Exercise 2.83,
  modify the apply-generic procedure so that it coerces its
  arguments to have the same type by the method of successive raising, 
  as discussed in this section. You will need to devise a way to test
  which of two types is higher in the tower. Do this in a manner that is “compatible”
  with the rest of the system and will not lead to problems in adding new levels to the tower.")
(def type-tower ['integer 'rational 'real 'complex])

(defn type-level [type]
  (.indexOf type-tower type))

(defn raise-until
  "Returns a lazy sequence of successive raises starting with value"
  [value]
  (->> (iterate raise value)
       (take-while some?)
       (map type-tag)
       (map vector (repeat value))))

(defn raise-to-type
  "Raises value to target-type, returns nil if impossible"
  [value target-type]
  (->> (raise-until value)
       (drop-while #(not= (second %) target-type))
       first
       first))

(defn try-direct-operation [op args]
  (when-let [proc (get-op op (map type-tag args))]
    (apply proc (map contents args))))

(defn try-coerced-operation [op args]
  (let [highest-type (->> args
                          (map type-tag)
                          (sort-by type-level >)
                          first)
        raised-args (map #(raise-to-type % highest-type) args)]
    (when (every? some? raised-args)
      (try-direct-operation op raised-args))))

(defn apply-generic [op & args]
  (or (try-direct-operation op args)
      (try-coerced-operation op args)
      (throw (ex-info "No method for these types and couldn't raise"
                      {:op op
                       :types (map type-tag args)}))))

(install-rational-package)
(install-complex-package)
(install-integer-package)
(install-raise-package)

(make-rational 1 2)
(raise (make-integer 4))

(apply-generic 'add (make-integer 5) (make-rational 1 2))

(apply-generic 'add (make-integer 2) (make-integer 4))

(def test-raise (get-op 'raise '(integer)))
(when test-raise
  (println "Raising integer 5:" (test-raise ((get-op 'make 'integer) 5))))
