(ns chapter-2.exercise-2-10
  (:require
   [chapter-2.exercise-2-7 :refer [lower-bound make-interval upper-bound]]
   [chapter-2.exercise-2-9 :refer [mul-interval]]))


;; Ben Bitdiddle, an expert systems programmer,
;; looks over Alyssa’s shoulder and comments that it is
;; not clear what it means to divide by an interval that spans
;; zero. Modify Alyssa’s code to check for this condition and
;; to signal an error if it occurs.
(defn div-interval [x y]
  (if (<= (* (lower-bound y)
             (upper-bound y))
          0)
    (throw (ex-info "Divisions by an interval that spans zero"
                    {:interval y}))
    (mul-interval
     x
     (make-interval (/ 1.0 (upper-bound y))
                    (/ 1.0 (lower-bound y))))))

(div-interval (make-interval 1 1) (make-interval -1 2))
                    