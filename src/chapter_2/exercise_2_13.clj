(ns chapter-2.exercise-2-13 
  (:require
   [chapter-2.exercise-2-10 :refer [div-interval]]
   [chapter-2.exercise-2-7 :refer [lower-bound make-interval upper-bound]]
   [chapter-2.exercise-2-9 :refer [mul-interval]]))

;; Show that under the assumption of small
;; percentage tolerances there is a simple formula for the approximate 
;; percentage tolerance of the product of two intervals
;; in terms of the tolerances of the factors. You may simplify 
;; the problem by assuming that all numbers are positive.
;; After considerable work, Alyssa P. Hacker delivers her finished system.
;; Several years later, after she has forgoen all
;; about it, she gets a frenzied call from an irate user, Lem E.
;; Tweakit. It seems that Lem has noticed that the formula for 
;; parallel resistors can be wrien in two algebraically equivalent ways:
;; R_1 * R_2 / (R_1 + R_2)
;; and
;; 1 / (1 / R_1 + 1 / R_2)
;; He has written the following two programs, each of which
;; computes the parallel-resistors formula diﬀerently:
(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn par1 [r1 r2]
  (div-interval (mul-interval r1 r2)
                (add-interval r1 r2)))

(defn par2 [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one
                  (add-interval (div-interval one r1)
                                (div-interval one r2)))))
;; Lem complains that Alyssa’s program gives diﬀerent answers 
;; for the two ways of computing. is is a serious complaint.

(def interval-1 (make-interval 1 5))
(def interval-2 (make-interval 2 6))
(par1 interval-1 interval-2)
(par2 interval-1 interval-2)
;; The difference occurs because interval arithmetic accumulates uncertainties
;; differently depending on the sequence of operations. Even though the formulas
;; are algebraically equivalent, they produce different interval widths due to
;; the different order of operations.