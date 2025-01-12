(ns chapter-1.exercise-1-8)

; Newton’s method for cube roots is based on
; the fact that if y is an approximation to the cube root of x,
; then a better approximation is given by the value
; ((x / (y ^ 2)) + 2 * y) / 3
; Use this formula to implement a cube-root procedure analogous to the square-root procedure.

(defn sqr [x] (* x x))
(defn cube [x] (* x x x))

(defn good-enough? [guess x]
  (< (abs (- (cube guess) x)) 0.0001))

(defn improve [guess x]
  (/ (+ (/ x (sqr guess)) (* 2 guess)) 3))

(defn cube-root-iter [guess x]
  (if (good-enough? guess x)
    guess
    (cube-root-iter (improve guess x) x)))

(cube-root-iter 1.0 27)