(ns chapter-1.exercise-1-46)

;; Exercise 1.46
;; Several of the numerical methods described in this chapter are instances of an
;; extremely general computational strategy known as iterative improvement.
;; Iterative improvement says that, to compute something, we start with an initial
;; guess for the answer, test if the guess is good enough, and otherwise improve
;; the guess and continue the process using the improved guess as the new guess.
;; 
;; Write a procedure iterative-improve that takes two procedures as arguments:
;; - a method for telling whether a guess is good enough
;; - a method for improving a guess
;; 
;; iterative-improve should return as its value a procedure that takes a guess as
;; argument and keeps improving the guess until it is good enough.
;; 
;; Rewrite the sqrt procedure of Section 1.1.7 and the fixed-point procedure of
;; Section 1.3.3 in terms of iterative-improve.
(defn iterative-improve [good-enough? improve-guess]
  (fn [guess]
    (->> guess
         (iterate improve-guess)
         (filter good-enough?)
         first)))

(defn sqrt [x]
  (let [good-enough? (fn [guess]
                       (< (Math/abs (- (* guess guess) x)) 0.001))
        improve-guess (fn [guess]
                        (/ (+ guess (/ x guess)) 2))]
    ((iterative-improve good-enough? improve-guess) 1.0)))

(sqrt 36)

(defn fixed-point [f first-guess]
  (let [tolerance 0.00001
        good-enough? (fn [guess]
                       (< (Math/abs (- (f guess) guess)) tolerance))
        improve-guess f]
    ((iterative-improve good-enough? improve-guess) first-guess)))

(fixed-point #(+ 1 (/ 1 %)) 1.0)