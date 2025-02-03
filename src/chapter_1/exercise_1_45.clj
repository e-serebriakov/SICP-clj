(ns chapter-1.exercise-1-45 
  (:require
   [chapter-1.chapter-1 :refer [average-damp fixed-point-of-transform]]
   [chapter-1.exercise-1-43 :refer [repeated]]
   [clojure.math :as math]))

;; We saw in Section 1.3.3 that aempting to
;; compute square roots by naively finding a fixed point of
;; y → x / y does not converge, and that this can be fixed by
;; average damping. The same method works for finding cube
;; roots as fixed points of the average-damped y → x / y^2. Unfortunately, 
;; the process does not work for fourth roots—a
;; single average damp is not enough to make a fixed-point
;; search for y → x / y^3 converge. On the other hand, if we
;; average damp twice (i.e., use the average damp of the average damp
;; of y → x / y^3) the fixed-point search does converge. 
;; Do some experiments to determine how many av erage damps 
;; are required to compute nth roots as a fixed-
;; point search based upon repeated average damping of y → x / y^(n−1). 
;; Use this to implement a simple procedure for computing nth roots 
;; using fixed-point, average-damp, and the
;; repeated procedure of Exercise 1.43. Assume that any arithmetic 
;; operations you need are available as primitives.
(defn nth-root [x n]
  (fixed-point-of-transform
   #(/ x (math/pow % (- n 1)))
   (repeated average-damp (+ (quot n 3) 1))
   1.0))

(nth-root 125 3)