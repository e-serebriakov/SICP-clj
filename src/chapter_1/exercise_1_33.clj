(ns chapter-1.exercise-1-33)

;; You can obtain an even more general version of `accumulate` (Exercise 1.32)
;; by introducing the notion of a `filter` on the terms to be combined. 
;; That is, combine only those terms derived from values in the range that sat-
;; isfy a specified condition. The resulting `filtered-accumulate`
;; abstraction takes the same arguments as accumulate, together 
;; with an additional predicate of one argument that
;; specifies the filter. Write `filtered-accumulate` as a procedure. 
;; Show how to express the following using `filtered-accumulate`:
(defn smallest-divisor [n]
  (letfn [(divides? [a b] (= (mod b a) 0))
          (find-divisor [n test-divisor]
                        (cond (> (* test-divisor test-divisor) n) n
                              (divides? test-divisor n) test-divisor
                              :else (find-divisor n (inc test-divisor))))]
    (find-divisor n 2)))

(defn prime? [n] (= n (smallest-divisor n)))

(defn filtered-accumelate [combiner filter null-value term a next b]
  (letfn [(iter [x result]
                (if (> x b)
                  result
                  (iter (next x) (if (filter x)
                                   (combiner (term x) result)
                                   result))))]
    (iter a null-value)))

;; a. the sum of the squares of the prime numbers in the
;; interval `a` to `b` (assuming that you have a `prime?` predicate already written)
(defn sum-of-prime-squares [a b]
  (letfn [(term [x] (* x x))]
    (filtered-accumelate + prime? 0 term a inc b)))

(sum-of-prime-squares 1 4)

;; b. the product of all the positive integers less than `n` that
;; are relatively prime to `n` (i.e., all positive integers `i` < `n`
;; such that GCD(i, n)= 1).
(defn product-of-relative-primes [n]
  (letfn [(gcd [a b]
            (if (= b 0)
              a
              (gcd b (mod a b))))
          (relative-prime? [x]
            (= (gcd x n) 1))]
    (filtered-accumelate * relative-prime? 1 identity 1 inc n)))

(product-of-relative-primes 4)