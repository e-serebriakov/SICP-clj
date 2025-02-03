(ns chapter-1.exercise-1-43 
  (:require
   [chapter-1.exercise-1-28 :refer [square]]
   [chapter-1.exercise-1-42 :refer [compose]]))

;; If `f` is a numerical function and `n` is a positive integer,
;; then we can form the nth repeated application
;; of `f`, which is defined to be the function whose value at
;; `x` is f(f(...(f(x))...)). For example, if `f` is the function
;; `x → x + 1`, then the nth repeated application of `f` is the
;; function `x → x + n`. If `f` is the operation of squaring a number,
;; then the nth repeated application of `f` is the function
;; that raises its argument to the 2n-th power. Write a procedure
;; that takes as inputs a procedure that computes `f` and a
;; positive integer `n` and returns the procedure that computes
;; the nth repeated application of `f`. 
;; Your procedure should be able to be used as follows:
;; ((repeated square 2) 5)
(defn repeated [f n]
  (letfn [(iter [result i]
            (if (= i n)
              result
              (iter (compose result f) (inc i))))]
    (iter f 1)))

((repeated square 2) 5)
((repeated inc 5) 5)