(ns chapter-1.exercise-1-3)

; Define a procedure that takes three numbers 
; as arguments and returns the sum of the squares of the two
; larger numbers.
(defn sqr [x] (* x x))

(defn sum-of-squares [x y] (+ (sqr x) (sqr y)))

(defn sum-of-biggest-numbers [a b c]
  (cond
    (and (> a c) (> b c)) (sum-of-squares a b)
    (and (> c a) (> b a)) (sum-of-squares b c)
    :else (sum-of-squares a c)))

(sum-of-biggest-numbers 1 2 3)