(ns chapter-1.exercise-1-7)

; The good-enough? test used in computing square roots will not be very eﬀective for finding the square
; roots of very small numbers.
; Also, in real computers, arithmetic operations are almost always performed with limited precision.
; This makes our test inadequate for very large numbers.
; Explain these statements, with examples showing how the test fails for small and large numbers. 
; An alternative strategy for implementing good-enough? is to watch how guess changes from one iteration to the next and to
; stop when the change is a very small fraction of the guess.
; Design a square-root procedure that uses this kind of end test. 
; Does this work better for small and large numbers?
(defn sqr [x] (* x x))

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

; Small numbers
; The absolute difference test can fail
; the tolerance (e.g., 0.001) is too large relative to the number being tested
(sqrt-iter 0.001 0.0000001)
; The actual result is 0.00031622776601683794
; Is the guess is 0.001, the square of the guess is 0.000001
; The difference |0.000001 - 0.0000001| = 0.0000009, which is less than 0.001.
; It will return initial guess (0.001) as the result

; Large number
(sqrt-iter 100.0 1000000000)
; The absolute tolerance (0.001) is too small relative to the magnitude if the number
; It leads to unnecessary iterations to meet this strict condition

(defn good-enough? [guess x]
  (< (abs (/ (sqr guess) x)) 0.0001))

(sqrt-iter 1.0 0.000001)
(sqrt-iter 100.0 1000000000)

