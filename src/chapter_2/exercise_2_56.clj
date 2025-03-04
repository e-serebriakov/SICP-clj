(ns chapter-2.exercise-2-56 
  (:require
   [chapter-2.chapter-2 :refer [addend augend make-product make-sum
                                multiplicand multiplier product?
                                same-variable? sum? variable?]]))

;; Show how to extend the basic diﬀerentiator
;; to handle more kinds of expressions. 
;; For instance, implement the diﬀerentiation rule
;; d(u^n)/dx = nu^(n-1)*(du/dx)
;; by adding a new clause to the deriv program and defining
;; appropriate procedures exponentiation?, base, exponent,
;; and make-exponentiation. (You may use the symbol **
;; to denote exponentiation.) Build in the rules that anything
;; raised to the power 0 is 1 and anything raised to the power
;; 1 is the thing itself.
(defn exponentiation? [x]
  (and (seq? x) (= (first x) '**)))

(defn make-exponentiation [base exponent]
  (if (zero? exponent)
    1
    (list '** base exponent)))

(defn base [exponentiation]
  (second exponentiation))

(defn exponent [exponentiation]
  (last exponentiation))

;; (defn deriv [exp var]
;;   (cond (number? exp) 0
;;         (variable? exp) (if (same-variable? exp var) 1 0)
;;         (sum? exp) (make-sum (deriv (addend exp) var)
;;                              (deriv (augend exp) var))
;;         (product? exp) (make-sum
;;                         (make-product (multiplier exp)
;;                                       (deriv (multiplicand exp) var))
;;                         (make-product (deriv (multiplier exp) var)
;;                                       (multiplicand exp)))
;;         (exponentiation? exp) (let [b (base exp)
;;                                     e (exponent exp)]
;;                                 (make-product (make-product e
;;                                                             (make-exponentiation b (dec e)))
;;                                               (deriv b var)))
;;         :else (throw (ex-info "unknown expression type: DERIV"
;;                               {:expression exp}))))

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

(deriv '(** x 3) 'x)
(deriv '(+ (** x 3) (* 2 x)) 'x)