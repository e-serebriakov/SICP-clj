(ns chapter-1.exercise-1-40 
  (:require
   [chapter-1.chapter-1 :refer [newtons-method]]
   [clojure.math :as math]))

;; Define a procedure cubic that can be used
;; together with the newtons-method procedure in expressions
;; of the form
;; (newtons-method (cubic a b c) 1)
;; to approximate zeros of the cubic x^3 + ax^2 + bx + c.
(defn cubic [a b c]
  #(+ (math/pow % 3)
      (* a (math/pow % 2))
      (* b %)
      c))

(newtons-method (cubic 2 3 4) 1)