(ns chapter-2.exercise-2-62)

;; Give a Θ(n) implementation of union-set for sets represented as ordered lists
(defn union-ordered-set [s1 s2]
  (let [[x1 & xs1] s1
        [x2 & xs2] s2]
    (cond (empty? s1) s2
          (empty? s2) s1
          (= x1 x2) (cons x1 (union-ordered-set xs1 xs2))
          (> x1 x2) (cons x2 (union-ordered-set s1 xs2))
          :else (cons x1 (union-ordered-set xs1 s2)))))

(union-ordered-set '(1 2 4) '(1 2 3 5))