(ns chapter-2.exercise-2-81
  (:require
   [chapter-2.chapter-2 :refer [contents get-op type-tag]]))

(comment
  "Louis Reasoner has noticed that apply-generic
  may try to coerce the arguments to each other’s type even
  if they already have the same type. Therefore, he reasons,
  we need to put procedures in the coercion table to coerce
  arguments of each type to their own type. For example, in
  addition to the scheme-number->complex coercion shown
  above, he would do:"
  (defn lisp-number->lisp-number [n] n)
  (defn complex->complex [z] z)

  (put-coercion 'lisp-number 'lisp-number lisp-number->lisp-number)
  (put-coercion 'complex 'complex complex->complex)

  (defn apply-generic [op & args]
    (let [type-tags (map type-tag args)
          proc (get-op op type-tags)]
      (if proc
        (apply proc (map contents args))
        (if (= (count type-tags) 2)
          (let [type1 (first type-tags)
                type2 (second type-tags)
                a1 (first args)
                a2 (second args)]
            (let [t1->t2 (get-coercion type1 type2)
                  t2->t1 (get-coercion type2 type1)]
              (cond t1->t2 (apply-generic op (t1->t2 a1) a2)
                    t2->t1 (apply-generic op a1 (t2->t1 a2))
                    :else (throw (ex-info "No method for these types: APPLY-GENERIC"
                                          {:op op :type-tags type-tags})))))
          (throw (ex-info "No method for these types: APPLY-GENERIC"
                          {:op op :type-tags type-tags})))))))
(comment
  "a. With Louis’s coercion procedures installed, what happens if apply-generic
  is called with two arguments of type scheme-number or two arguments of type complex
  for an operation that is not found in the table for those types? 
  For example, assume that we’ve defined a generic exponentiation operation:
  (define (exp x y) (apply-generic 'exp x y))
  and have put a procedure for exponentiation in the
  Lisp-number package but not in any other package:
  ;; following added to Scheme-number package
  (put 'exp '(lisp-number lisp-number)
  (lambda (x y) (tag (expt x y))))
  ; using primitive expt
  What happens if we call exp with two complex numbers as arguments?
  
  Answer: It will cause an infinite loop")

(comment
  "b. Is Louis correct that something had to be done about
  coercion with arguments of the same type, or does apply-generic work correctly as is?")

(comment
  "c. Modify apply-generic so that it doesn’t try coercion if the two arguments have the same type."

  (defn apply-generic [op & args]
    (let [type-tags (map type-tag args)
          proc (get-op op type-tags)]
      (if proc
        (apply proc (map contents args))
        (if (= (count type-tags) 2)
          (let [type1 (first type-tags)
                type2 (second type-tags)
                a1 (first args)
                a2 (second args)]
            (if (= type1 type2)
              (throw (ex-info "Can't coerce equal types: APPLY-GENERIC"
                              {:op op :type-tags type-tags}))
              (let [t1->t2 (get-coercion type1 type2)
                    t2->t1 (get-coercion type2 type1)]
                (cond t1->t2 (apply-generic op (t1->t2 a1) a2)
                      t2->t1 (apply-generic op a1 (t2->t1 a2))
                      :else (throw (ex-info "No method for these types: APPLY-GENERIC"
                                            {:op op :type-tags type-tags}))))))
          (throw (ex-info "No method for these types: APPLY-GENERIC"
                          {:op op :type-tags type-tags})))))))


