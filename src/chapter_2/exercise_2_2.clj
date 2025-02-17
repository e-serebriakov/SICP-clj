(ns chapter-2.exercise-2-2)

;; Consider the problem of representing line
;; segments in a plane. Each segment is represented as a pair
;; of points: a starting point and an ending point. Define a
;; constructor `make-segment` and selectors `start-segment` and
;; `end-segment` that define the representation of segments in
;; terms of points. Furthermore, a point can be represented
;; as a pair of numbers: the `x` coordinate and the `y` coordinate. 
;; Accordingly, specify a constructor `make-point` and
;; selectors `x-point` and `y-point` that define this representation. 
;; Finally, using your selectors and constructors, define a
;; procedure `midpoint-segment` that takes a line segment as
;; argument and returns its midpoint (the point whose coordinates 
;; are the average of the coordinates of the endpoints).
;; To try your procedures, you’ll need a way to print points:
(defn make-point [x y] [x y])
(def x-point first)
(def y-point last)
(defn make-segment [start end] [start end])
(def start-segment first)
(def end-segment last)

(defn print-point [p]
  (println (str "(" (x-point p) "," (y-point p) ")")))

(defn midpoint-segment [segment]
  (let [[x1 y1] (start-segment segment)
        [x2 y2] (end-segment segment)]
    (make-point (/ (+ x1 x2) 2)
                (/ (+ y1 y2) 2))))

(def p1 (make-point 2 2))
(print-point p1)
(def p2 (make-point 10 10))
(print-point p2)
(def segment (make-segment p1 p2))
(print-point (start-segment segment))
(print-point (end-segment segment))

(print-point (midpoint-segment segment))
