(ns chapter-2.exercise-2-1 
  (:require
   [chapter-1.chapter-1 :refer [gcd]]
   [chapter-2.chapter-2 :refer [print-rat]]))

;; Define a beer version of make-rat that handles both 
;; positive and negative arguments. make-rat should
;; normalize the sign so that if the rational number is positive,
;; both the numerator and denominator are positive, and if
;; the rational number is negative, only the numerator is negative.
(defn make-rat [n d]
  (let [g (gcd (abs n) (abs d))
        sign (if (pos? (* n d)) 1 -1)]
    [(* sign (/ (abs n) g))
     (/ (abs d) g)]))

(print-rat (make-rat 1 2))
(print-rat (make-rat -1 2))
(print-rat (make-rat -1 -2))