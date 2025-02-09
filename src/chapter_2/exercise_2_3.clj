(ns chapter-2.exercise-2-3)

;; Implement a representation for rectangles in
;; a plane. (Hint: You may want to make use of Exercise 2.2.) In
;; terms of your constructors and selectors, create procedures
;; that compute the perimeter and the area of a given rectangle. 
;; Now implement a diﬀerent representation for rectangles.
;; Can you design your system with suitable abstraction
;; barriers, so that the same perimeter and area procedures
;; will work using either representation?
(defn make-point [x y]
  [x y])

(defn x-coord [p]
  (first p))

(defn y-coord [p]
  (second p))

(defn make-rect-by-points [point1 point2]
  [point1 point2])

(defn width [rect]
  (Math/abs (- (x-coord (first rect))
               (x-coord (second rect)))))

(defn height [rect]
  (Math/abs (- (y-coord (first rect))
               (y-coord (second rect)))))

(defn make-rect-by-measures [point w h]
  [point w h])

(defn width-alt [rect]
  (second rect))

(defn height-alt [rect]
  (last rect))

(defn area [rect]
  (* (width rect) (height rect)))

(defn perimeter [rect]
  (* 2 (+ (width rect) (height rect))))