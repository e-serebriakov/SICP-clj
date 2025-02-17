(ns chapter-2.exercise-2-8 
  (:require
   [chapter-2.exercise-2-7 :refer [lower-bound make-interval upper-bound]]))

;; Using reasoning analogous to Alyssa’s, describe
;; how the diﬀerence of two intervals may be computed.
;; Define a corresponding subtraction procedure, called sub-interval.
(defn sub-interval [a b]
  (make-interval (- (lower-bound a) (upper-bound b))
                 (- (upper-bound a) (lower-bound b))))