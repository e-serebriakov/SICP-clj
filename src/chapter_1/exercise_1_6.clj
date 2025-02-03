(ns chapter-1.exercise-1-6)

; Alyssa P. Hacker doesn’t see why if needs to
; be provided as a special form. “Why can’t I just define it as
; an ordinary procedure in terms of cond?” she asks. Alyssa’s
; friend Eva Lu Ator claims this can indeed be done, and she
; defines a new version of if:
(defn new-if [predicate then-clause else-clause]
  (if predicate then-clause else-clause))

; Eva demonstrates the program for Alyssa:
(new-if (= 2 3) 0 5)
(new-if (= 1 1) 0 5)

; Delighted, Alyssa uses new-if to rewrite the square-root program:
(defn sqr [x] (* x x))
(defn good-enough? [guess x]
  (< (abs (- (sqr guess) x)) 0.001))

(defn average [a b] 
  (/ (+ a b) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (new-if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

; What happens when Alyssa aempts to use this to compute square roots? Explain.
; (sqrt-iter-new-if 1 9)
; Applicative-order evaluation makes the intepreter calculate all arguments of "new-if" as "new-if" is a function
; It will cause an infinite recursion