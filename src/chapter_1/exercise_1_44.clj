(ns chapter-1.exercise-1-44 
  (:require
   [chapter-1.chapter-1 :refer [square]]
   [chapter-1.exercise-1-43 :refer [repeated]]))

;; The idea of smoothing a function is an important concept
;; in signal processing. If f is a function and
;; dx is some small number, then the smoothed version of f is
;; the function whose value at a point x is the average of f(x − dx), 
;; f(x), and f(x + dx). Write a procedure smooth that takes
;; as input a procedure that computes f and returns a procedure 
;; that computes the smoothed f. It is sometimes valuable to repeatedly 
;; smooth a function (that is, smooth the
;; smoothed function, and so on) to obtain the n-fold smoothed
;; function. Show how to generate the n-fold smoothed function 
;; of any given function using smooth and repeated from Exercise 1.43.
(defn smooth [f dx]
  #(/ (+ (f (- % dx))
         (f %)
         (f (+ % dx)))
      3))
(def dx 0.0001)

((smooth square dx) 5)

(defn n-fold-smooth [f dx n]
  ((repeated #(smooth % dx) n) f))
 
((n-fold-smooth square dx 2) 5)
