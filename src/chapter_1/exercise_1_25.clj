(ns chapter-1.exercise-1-25 
  (:require
    [clojure.math :as math]))

;; Alyssa P. Hacker complains that we went to
;; a lot of extra work in writing expmod. After all, she says,
;; since we already know how to compute exponentials, we
;; could have simply written
(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (math/pow (fast-expt b (/ n 2)) 2)
        :else (* b (fast-expt b (- n 1)))))

(defn fast-exp-mod [base exp m]
 (mod (fast-expt base exp) m))

(defn exp-mod [base exp m]
            (cond (= exp 0) 1
                  (even? exp) (mod (exp-mod base (/ exp 2) m) m)
                  :else (mod (* base (exp-mod base (dec exp) m)) m)))

;; Is she correct? Would this procedure serve as well for our
;; fast prime tester? Explain.
(exp-mod 7 10 13)
;; (mod (exp-mod 7 (/ 10 2) 13) 13)
;; (mod (exp-mod 7 5 13) 13)
;; (mod (mod (* 7 (exp-mod 7 (dec 5) 13)) 13) 13)
;; (mod (mod (* 7 (exp-mod 7 4 13)) 13) 13)
;; (mod (mod (* 7 ((mod (exp-mod 7 (/ 4 2) 13) 13))) 13) 13)
;; (mod (mod (* 7 ((mod (exp-mod 7 2 13) 13))) 13) 13)
;; (mod (mod (* 7 (mod (mod (exp-mod 7 (/ 2 2) 13) 13) 13)) 13) 13)
;; (mod (mod (* 7 (mod (mod (exp-mod 7 1 13) 13) 13)) 13) 13)
;; (mod (mod (* 7 (mod (mod (mod (* 7 (exp-mod 7 (dec 1) 13)) 13) 13) 13)) 13) 13)
;; (mod (mod (* 7 (mod (mod (mod (* 7 (exp-mod 7 0 13)) 13) 13) 13)) 13) 13)
;; (mod (mod (* 7 (mod (mod (mod (* 7 1) 13) 13) 13)) 13) 13)
;; (mod (mod (* 7 (mod (mod (mod 7 13) 13) 13)) 13) 13)
;; (mod (mod (* 7 (mod (mod 7 13) 13)) 13) 13)
;; (mod (mod (* 7 (mod 7 13)) 13) 13)
;; (mod (mod (* 7 7) 13) 13)
;; (mod (mod 49 13) 13)
;; (mod 10 13)
;; 10
;; Takes the modulo at each step
;; Never lets intermediate values grow larger than m

(fast-exp-mod 7 10 13)
;; (mod (fast-expt 7 10) 13)
;; (mod (math/pow (fast-expt 7 (/ 10 2)) 2) 13)
;; (mod (math/pow (fast-expt 7 5) 2) 13)
;; (mod (math/pow (* 7 (fast-expt 7 (- 5 1))) 2) 13)
;; (mod (math/pow (* 7 (fast-expt 7 4)) 2) 13)
;; (mod (math/pow (* 7 (math/pow (fast-expt 7 (/ 4 2)) 2)) 2) 13)
;; (mod (math/pow (* 7 (math/pow (fast-expt 7 2) 2)) 2) 13)
;; (mod (math/pow (* 7 (math/pow (math/pow (fast-expt 7 (/ 2 2)) 2) 2)) 2) 13)
;; (mod (math/pow (* 7 (math/pow (math/pow (fast-expt 7 1) 2) 2)) 2) 13)
;; (mod (math/pow (* 7 (math/pow (math/pow (* 7 (fast-expt 7 (- 1 1))) 2) 2)) 2) 13)
;; (mod (math/pow (* 7 (math/pow (math/pow (* 7 (fast-expt 7 0)) 2) 2)) 2) 13)
;; (mod (math/pow (* 7 (math/pow (math/pow (* 7 1) 2) 2)) 2) 13)
;; (mod (math/pow (* 7 (math/pow (math/pow 7 2) 2)) 2) 13)
;; (mod (math/pow (* 7 (math/pow 49 2)) 2) 13)
;; (mod (math/pow (* 7 2401) 2) 13)
;; (mod 282475249 13) // we can loose precision here
;; 4?