(ns chapter-2.exercise-2-9
  (:require
   [chapter-2.exercise-2-7 :refer [lower-bound make-interval upper-bound]]
   [chapter-2.exercise-2-8 :refer [sub-interval]]))

;; The width of an interval is half of the diﬀerence 
;; between its upper and lower bounds. The width is a
;; measure of the uncertainty of the number specified by the
;; interval. For some arithmetic operations the width of the
;; result of combining two intervals is a function only of the
;; widths of the argument intervals, whereas for others the
;; width of the combination is not a function of the widths of
;; the argument intervals. Show that the width of the sum (or
;; diﬀerence) of two intervals is a function only of the widths
;; of the intervals being added (or subtracted). Give examples
;; to show that this is not true for multiplication or division.
(defn width [interval]
  (/ (- (upper-bound interval)
        (lower-bound interval))
     2))

;; (width (sub [a b] [c d]))
;; (width [a - d b - c])
;; ((a - d) - (b - c)) / 2
;; (a - d - b + c) / 2
;; (a - b + c - d) / 2
;; (a - b) / 2 + (c - d) / 2
;; (width [a b]) + (width [c d])

(def interval-1 (make-interval 0 4)) ;; width 2
(def interval-2 (make-interval 6 10)) ;; width 2

(width (sub-interval interval-2 interval-1))
(+ (width interval-2) (width interval-1))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
                  (max p1 p2 p3 p4))))


(width (mul-interval interval-1 interval-2)) ;; 20

(def interval-1a (make-interval 1 5)) ;; width 2
(def interval-2b (make-interval 7 11)) ;; width 2
(width (mul-interval interval-1a interval-2b)) ;; 24


  