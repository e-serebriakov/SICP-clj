(ns chapter-1.exercise-1.39 
  (:require
   [chapter-1.exercise-1-37 :refer [cont-frac-recursive]]))

;; A continued fraction representation of the
;; tangent function was published in 1770 by the German mathematician
;; J.H. Lambert:
;; tanx = x / (1 - (x^2 / (3 - (x^2 (5 - ....)))))
;; where `x` is in radians. Define a procedure `(tan-cf x k)` that
;; computes an approximation to the tangent function based
;; on Lambert’s formula. `k` specifies the number of terms to
;; compute, as in Exercise 1.37.
(defn tan-cf [x k]
  (cont-frac-recursive
   (fn [i] 
     (if (= i 1)
       x
       (- (* x x))))
   (fn [i] (+ 1 (* 2 (dec i))))
   k))

(tan-cf 0 10)
(tan-cf (/ Math/PI 4) 10)
(tan-cf (/ Math/PI 2) 10)