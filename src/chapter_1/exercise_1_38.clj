(ns chapter-1.exercise-1-38 
  (:require
   [chapter-1.exercise-1-37 :refer [cont-frac-iterative]]))

;; In 1737, the Swiss mathematician Leonhard
;; Euler published a memoir De Fractionibus Continuis, which
;; included a continued fraction expansion for e−2, where `e`
;; is the base of the natural logarithms. In this fraction, the Ni are all 1,
;; and the Di are successively 1, 2, 1, 1, 4, 1, 1, 6, 1, 1, 8,. . ..
;; Write a program that uses your cont-frac procedure from Exercise 1.37 
;; to approximate e, based on Euler’s expansion.
(cont-frac-iterative
 (fn [i] 1.0)
 (fn [i] 
   (if (or (= (mod i 3) 1) (= (mod i 3) 0))
     1.0
     (* 2 (quot (+ i 1) 3))))
 6)