(ns chapter-2.exercise-2.11 
  (:require
   [chapter-2.exercise-2-7 :refer [lower-bound make-interval upper-bound]]))

;; In passing, Ben also cryptically comments:
;; “By testing the signs of the endpoints of the intervals, it is
;; possible to break mul-interval into nine cases, only one
;; of which requires more than two multiplications.” Rewrite
;; this procedure using Ben’s suggestion.
;; After debugging her program, Alyssa shows it to a potential user,
;; who complains that her program solves the wrong problem. 
;; He wants a program that can deal with numbers
;; represented as a center value and an additive tolerance; for
;; example, he wants to work with intervals such as 3.5 ±0.15
;; rather than [3.35, 3.65]. Alyssa returns to her desk and fixes
;; this problem by supplying an alternate constructor and alternate selectors:
(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))
(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))
(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))

;; Unfortunately, most of Alyssa’s users are engineers. Real
;; engineering situations usually involve measurements with
;; only a small uncertainty, measured as the ratio of the width
;; of the interval to the midpoint of the interval. Engineers
;; usually specify percentage tolerances on the parameters of
;; devices, as in the resistor specifications given earlier.

(defn mul-interval [x y]
  (let [lx (lower-bound x)
        ux (upper-bound x)
        ly (lower-bound y)
        uy (upper-bound y)]
    (cond
      (and (>= lx 0) (>= ly 0))
      (make-interval (* lx ly) (* ux uy))

      (and (<= ux 0) (>= ly 0))
      (make-interval (* lx uy) (* ux ly))

      (and (>= lx 0) (<= uy 0))
      (make-interval (* ux ly) (* lx uy))

      (and (<= ux 0) (<= uy 0))
      (make-interval (* ux uy) (* lx ly))

      (and (< lx 0) (> ux 0) (>= ly 0))
      (make-interval (* lx uy) (* ux uy))

      (and (< lx 0) (> ux 0) (<= uy 0))
      (make-interval (* ux ly) (* lx ly))

      (and (>= lx 0) (< ly 0) (> uy 0))
      (make-interval (* ux ly) (* ux uy))

      (and (<= ux 0) (< ly 0) (> uy 0))
      (make-interval (* lx uy) (* lx ly))

      :else
      (make-interval (min (* lx uy) (* ux ly))
                     (max (* lx ly) (* ux uy))))
    ))

(defn make-percentage-width [w p]
  (let [dw (* (/ w 100) p)]
    (make-interval (- w dw) (+ w dw))))