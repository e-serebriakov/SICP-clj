(ns chapter-2.exercise-2-14 
  (:require
   [chapter-2.exercise-2-10 :refer [div-interval]]
   [chapter-2.exercise-2-7 :refer [make-interval]]))

;; Demonstrate that Lem is right. Investigate
;; the behavior of the system on a variety of arithmetic expressions. 
;; Make some intervals A and B, and use them in computing the expressions 
;; A/A and A/B. You will get the most insight by using intervals
;; whose width is a small percentage of the center value. 
;; Examine the results of the computation in center-percent form (see Exercise 2.12).
(def a (make-interval 148 150))
(def b (make-interval 98 100))

(div-interval a a) ;; we expect 1 but get different answer
(div-interval a b)