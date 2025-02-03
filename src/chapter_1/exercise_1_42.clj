(ns chapter-1.exercise-1-42 
  (:require
   [chapter-1.chapter-1 :refer [square]]))

;; Let f and дbe two one-argument functions.
;; The composition f after this defined to be the function x → f(g(x)).
;; Define a procedure compose that implements composition. 
;; For example, if `inc` is a procedure that adds 1 to its argument,
;; ((compose square inc) 6) -> 49
(defn compose [f g]
  #(f (g %)))

((compose square inc) 6) 
