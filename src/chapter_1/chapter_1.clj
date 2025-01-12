(ns chapter-1.chapter-1)

486

(+ 137 349)

(- 100 43)

(+ 2.7 10)

(+ 21 35 12 7)

(+ (* 3 5) (- 10 6))

(+ (* 3
      (+ (* 2 4)
         (+ 3 5)))
   (+ (- 10 7)
      6))

(def size 2)

size

(* 5 size)

(defn sqr [x] (* x x))

(sqr 4)

(sqr (sqr 8))

(defn sum-of-squares [x y] (+ (sqr x) (sqr y)))

(sum-of-squares 3 4)

(defn f [a] (sum-of-squares (+ a 1) (* a 2)))

(f 5)

;; Normal-order evaluation — full substitution first
;; (f 5)
;; (sum-of-squares (+ 5 1) (* 5 2))
;; (+ (square (+ 5 1)) (square (* 5 2)))
;; (+ (* (+ 5 1) (+ 5 1)) (* (* 5 2) (* 5 2)))
;; (+ (* 6 6) (* 10 10))
;; (+ 36 100)
;; 136

;; Applicative-order evaluation
;; (f 5)
;; (sum-of-squares (+ 5 1) (* 5 2)) 
;; (sum-of-squares 6 10)
;; (+ (sqr 6) (sqr 10))
;; (+ (* 6 6) (* 10 10))
;; (+ 36 100)
;; 136

(defn abs-1 [x] 
  (cond
    (> x 0) x
    (< x 0) (- x)
    (= x 0) 0
    )
  )

(abs-1 0)
(abs-1 1)
(abs-1 (- 1))

(defn abs-2 [x]
  (cond
    (< x 0) (- x)
    :else x))

(abs-2 0)
(abs-2 1)
(abs-2 (- 1))

(defn abs-3 [x]
  (if (< x 0) (- x) x))

(abs-3 0)
(abs-3 1)
(abs-3 (- 1))

(defn between-3-and-10 [x] (and (> x 3) (< x 10)))

(between-3-and-10 3)
(between-3-and-10 4)

(defn good-enough? [guess x]
  (< (abs (- (sqr guess) x)) 0.001))

(defn average [a b] 
  (/ (+ a b) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(sqrt 9)

(sqrt (+ 100 37))

