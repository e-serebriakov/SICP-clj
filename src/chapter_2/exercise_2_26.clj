(ns chapter-2.exercise-2-26)

;; Suppose we define x and y to be two lists:
(def x (list 1 2 3))
(def y (list 4 5 6))
;; What result is printed by the interpreter in response to evaluating 
;; each of the following expressions:
(concat x y) ;; (1 2 3 4 5 6)
(cons x y) ;; ((1 2 3) 4 5 6)
(list x y) ;; ((1 2 3) (4 5 6))