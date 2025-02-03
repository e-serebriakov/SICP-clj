(ns chapter-1.exercise-1-27 
  (:require
    [clojure.math :as math]))

;; Demonstrate that the Carmichael numbers
;; listed in Footnote 1.47 really do fool the Fermat test. That is,
;; write a procedure that takes an integer n and tests whether
;; a^n is congruent to a modulo n for every a < n, and try your
;; procedure on the given Carmichael numbers.
;; 561, 1105, 1729, 2465, 2821, and 6601

(defn test-int [n]
  (letfn [(exp-mod [base exp m]
            (cond (= exp 0) 1
                  (even? exp) (mod (long (math/pow (exp-mod base (/ exp 2) m)
                                             2)) m)
                  :else (mod (* base (exp-mod base (dec exp) m)) m)))
          (try-it [n a]
            (cond (>= a n) true
                  (not (= (exp-mod a n n) a)) false
                  :else (try-it n (inc a))))]
    
    (try-it n 1)))

(map test-int [561 1105 1729 2465 2821 6601])