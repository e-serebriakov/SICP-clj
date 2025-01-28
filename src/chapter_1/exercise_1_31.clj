(ns chapter-1.exercise-1-31)

;; a. e sum procedure is only the simplest of a vast number
;; of similar abstractions that can be captured as higher-order procedures.
;; Write an analogous procedure called product that returns
;; the product of the values of a function at points over a given range.
;; Show how to define factorial in terms of product. Also use product
;; to compute approximations to π using the formula
;; (π/4) = 2/3 * 4/3 * 4/5 * 6/5 * 6/7 * 8/7...
;; b. If your product procedure generates a recursive process,
;; write one that generates an iterative process.
;; If it generates an iterative process, write one that generates a recursive process.

(defn product-recursive [term a next b]
  (if (> a b)
    1
    (* (term a)
       (product-recursive term (next a) next b))))

(defn product-iterative [term a next b]
  (letfn [(iter [x result]
                (if (> x b)
                  result
                  (iter (next x) (* result (term x)))))]
    (iter a 1)))

(defn product-integers-1 [a b] (product-recursive identity a inc b))
(defn product-integers-2 [a b] (product-iterative identity a inc b))

(product-integers-1 1 3)
(product-integers-2 1 3)

(defn pi-product [n]
  (letfn [(term [x]
                (* (/ (* 2 x)
                      (dec (* 2 x)))
                   (/ (* 2 x)
                      (inc (* 2 x)))))]
    (* 4 (product-iterative term 1 inc n))))

(pi-product 12)