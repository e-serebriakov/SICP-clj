(ns chapter-2.exercise-2-46)

;; A two-dimensional vector v running from
;; the origin to a point can be represented as a pair consisting
;; of an x-coordinate and a y-coordinate. Implement a data
;; abstraction for vectors by giving a constructor make-vect
;; and corresponding selectors xcor-vect and ycor-vect. In
;; terms of your selectors and constructor, implement proce-
;; dures add-vect, sub-vect, and scale-vect that perform
;; the operations vector addition, vector subtraction, 
;; and multiplying a vector by a scalar:
;; (x1, y1) + (x2, y2) = (x1 + x2, y1 + y2),
;; (x1, y1) − (x2, y2) = (x1 − x2, y1 − y2),
;; s·(x, y) = (sx, sy).
(defn make-vect [x y] [x y])

(def xcor-vect first)
(def ycor-vect last)

(defn add-vect [v1 v2]
  (make-vect (+ (xcor-vect v1) (xcor-vect v2))
             (+ (ycor-vect v1) (ycor-vect v2))))

(defn sub-vect [v1 v2]
  (make-vect (- (xcor-vect v1) (xcor-vect v2))
             (- (ycor-vect v1) (ycor-vect v2))))

(defn scale-vect [v s]
  (make-vect (* s (xcor-vect v))
           (* s (ycor-vect v))))


(def vec1 (make-vect 3 4))
(def vec2 (make-vect 5 6))
(add-vect vec1 vec2)
(sub-vect vec1 vec2)
(scale-vect vec1 4)