(ns chapter-1.chapter-1 
  (:require
   [clojure.math :refer [cos]]))

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

(defn sqrt2 [x] (letfn [(good-enough? [guess] (< (abs (- (* guess guess) x)) 0.001))
                        (average [a b] (/ (+ a b) 2))
                        (improve [guess] (average guess (/ x guess)))
                        (sqrt-iter [guess] (if (good-enough? guess )
                                               guess
                                               (sqrt-iter (improve guess))))]
                  (sqrt-iter 1.0)))

(sqrt2 9)

(defn factorial [n]
  (if (= n 1)
    n
    (* n (factorial (- n 1)))))

(factorial 3)

(defn factorial2 [n]
  (letfn [(iter [product counter]
                (if (> counter n)
                  product
                  (iter (* product counter) (+ counter 1))))]
  (iter 1 1)))

(factorial2 3)

(defn count-change [amount] 
  (letfn [(first-denomination [kind-of-coins] (cond (= kind-of-coins 1) 1
                                                    (= kind-of-coins 2) 5
                                                    (= kind-of-coins 3) 10
                                                    (= kind-of-coins 4) 25
                                                    (= kind-of-coins 5) 50))
          (cc [amount kind-of-coins] (cond
                                       (= amount 0) 1
                                       (or (< amount 0)
                                           (= kind-of-coins 0)) 0
                                       :else (+ (cc amount (- kind-of-coins 1))
                                                (cc (- amount (first-denomination kind-of-coins))
                                                    kind-of-coins))))]
  (cc amount 5)))

(count-change 100)

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (mod a b))))
   
(gcd 206 40)

(defn sum [term a next b]
  (letfn [(iter [x result]
            (if (> x b)
              result
              (iter (next x) (+ (term x) result))))]
    (iter a 0)))

(defn pi-sum [a b]
  (letfn [(pi-term [x] (/ 1.0 (* x (+ x 2))))
          (pi-next [x] (+ x 4))]
    (sum pi-term a pi-next b)))

(pi-sum 1 5)

(defn pi-sum-lambdas [a b]
  (sum #(/ 1.0 (* % (+ % 2))) a #(+ % 4) b))

(pi-sum-lambdas 1 5)

(defn fixed-point [f first-guess]
  (let [tolerance 0.00001]
    (letfn [(close-enough? [v1 v2]
              (let [diff (abs (- v1 v2))]
                (< diff tolerance)))
            (iterate-guess [guess]
              (let [next-guess (f guess)]
                (if (close-enough? guess next-guess)
                  next-guess
                  (iterate-guess next-guess))))]
      (iterate-guess first-guess))))

(defn average [x y]
  (/ (+ x y) 2))

(defn sqrt [x]
  (fixed-point #(average % (/ x %)) 1.0))

(sqrt 4.0)
(fixed-point #(cos %) 1.0)