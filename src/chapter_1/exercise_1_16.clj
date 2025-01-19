(ns chapter-1.exercise-1-16 
  (:require
   [clojure.math :as math]))

;; Design a procedure that evolves an iterative exponentiation process
;; that uses successive squaring and uses a logarithmic number of steps, as does fast-expt.
;; (Hint: Using the observation that (b ^ (n / 2)) ^ 2 = (b ^ 2) ^ (n / 2), keep,
;; along with the exponent n and the base b, an additional
;; state variable a, and define the state transformation in such
;; a way that the product abn is unchanged from state to state.
;; At the beginning of the process a is taken to be 1, and the
;; answer is given by the value of a at the end of the process.
;; In general, the technique of defining an invariant quantity
;; that remains unchanged from state to state is a powerful
;; way to think about the design of iterative algorithms.)
(defn exp [b n] (letfn [(iter [product base exp] (if (= exp 0)
                                                   product
                                                   (if (even? exp)
                                                     (iter product (math/pow base 2) (/ exp 2))
                                                     (iter (* product base) base (dec exp)))))]
                  (iter 1 b n)
))

(exp 2 2)
(exp 2 3)
(exp 3 3)