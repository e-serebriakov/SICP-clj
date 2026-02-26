(ns chapter-2.exercise-2-57
  (:require
   [chapter-2.chapter-2 :refer [addend make-product make-sum multiplier
                                product? same-variable? sum? variable?]]
   [chapter-2.exercise-2-56 :refer [base exponent exponentiation?
                                    make-exponentiation]]))

;; Extend the diﬀerentiation program to handle sums 
;; and products of arbitrary numbers of (two or more)
;; terms. Then the last example above could be expressed as
;; (deriv '(* x y (+ x 3)) 'x)
;; Try to do this by changing only the representation for sums
;; and products, without changing the deriv procedure at all.
;; For example, the addend of a sum would be the first term,
;; and the augend would be the sum of the rest of the terms.
(defn augend [exp]
  (let [[_ & operands] exp]
    (if (= (count operands) 2)
      (second operands)
      (cons '+ (rest operands)))))

(defn multiplicand [exp]
  (let [[_ & operands] exp]
    (if (= (count operands) 2)
      (second operands)
      (cons '* (rest operands)))))

(defn deriv [exp var]
  (cond 
    (number? exp) 0
    (variable? exp) (if (same-variable? exp var) 1 0)
    (sum? exp) (->> var
                    ((juxt #(deriv (addend exp) %)
                          #(deriv (augend exp) %)))
                    (apply make-sum))
    (product? exp) (->> [(make-product (multiplier exp)
                                      (deriv (multiplicand exp) var))
                        (make-product (deriv (multiplier exp) var)
                                    (multiplicand exp))]
                       (apply make-sum))
    (exponentiation? exp) (let [b (base exp)
                               e (exponent exp)]
                           (->> (make-exponentiation b (dec e))
                                (make-product (deriv b var))
                                (make-product e)))
    :else (throw (ex-info "unknown expression type: DERIV"
                         {:expression exp}))))

(deriv '(* 3 x y) 'x)
(deriv '(+ (* 3 x y) x y) 'x)