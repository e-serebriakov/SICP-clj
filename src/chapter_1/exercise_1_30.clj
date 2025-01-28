(ns chapter-1.exercise-1-30)

;; The sum procedure above generates a linear
;; recursion. The procedure can be rewritten so that the sum
;; is performed iteratively. Show how to do this by filling in
;; the missing expressions in the following definition:
(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn sum-integers [a b] (sum identity a inc b))

(sum-integers 1 4)

(defn sum-iter [term a next b]
  (letfn [(iter [x result]
            (if (> x b)
              result
              (iter (next x) (+ (term x) result))))]
    (iter a 0)))

(defn sum-integers [a b] (sum-iter identity a inc b))
(sum-integers 1 4)