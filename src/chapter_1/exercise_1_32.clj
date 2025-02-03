(ns chapter-1.exercise-1-32)

;; a. Show that sum and product (Exercise 1.31) are both
;; special cases of a still more general notion called accumulate
;; that combines a collection of terms, using some general accumulation function:
;; (accumulate combiner null-value term a next b)
;; `accumulate` takes as arguments the same `term` and
;; range specifications as `sum` and `product`, together with
;; a `combiner` procedure (of two arguments) that specifies
;; how the current term is to be combined with the
;; accumulation of the preceding terms and a null-value
;; that specifies what base value to use when the terms run out.
;; Write accumulate and show how sum and
;; product can both be defined as simple calls to accumulate.

;; Recursive
(defn accumulate [combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term a)
              (accumulate combiner null-value term (next a) next b))))
;; Iterative
(defn accumulate [combiner null-value term a next b]
  (letfn [(iter [x result]
                (if (> x b)
                  result
                  (iter (next x) (combiner result (term x)))))]
    (iter a null-value)))

(defn sum [term a next b]
  (accumulate + 0 term a next b))

(defn sum-integers [a b] (sum identity a inc b))
(sum-integers 1 4)

(defn product [term a next b]
  (accumulate * 1 term a next b))

(defn product-integers [a b] (product identity a inc b))
(product-integers 1 3)