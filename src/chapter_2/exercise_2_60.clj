(ns chapter-2.exercise-2-60)

;; We specified that a set would be represented as a list with no duplicates.
;; Now suppose we allow duplicates. For instance, the set {1, 2, 3} could be
;; represented as the list (2 3 2 1 3 2 2). Design procedures element-of-set?, 
;; adjoin-set, union-set, and intersection-set that operate on this
;; representation. How does the eﬃciency of each compare with the corresponding
;; procedure for the non-duplicate representation? 
;; Are there applications for which you would use this representation in 
;; preference to the non-duplicate one?

;; With duplicates: O(n) - must scan through the entire list in worst case
;; Without duplicates: O(n)
(defn element-of-set? [set x]
  (cond (empty? set) false
        (= (first set) x) true
        :else (element-of-set? (rest set) x)))

(element-of-set? '(1 2 3) 2)

;; With duplicates: O(1) - simply cons the element to the front
;; Without duplicates: O(n) - must check if element exists first
(defn adjoin-set [set x] (cons x set))

(adjoin-set '(1 2 3) 1)

;; With duplicates: O(n)
;; Without duplicates: O(n²) - must check for duplicates
(def union-set concat)

(union-set '(1 2 3 4) '(1 2 3 5))

;; With duplicates: O(nm)
;; Without duplicates: O(nm)
(defn intersection-set [s1 s2]
  (cond (or (empty? s1) (empty? s2)) '()
        (element-of-set? s2 (first s1)) (cons (first s1)
                                              (intersection-set (rest s1) s2))
        :else (intersection-set (rest s1) s2)))
 
(intersection-set '(1 2 3 2) '(2 2 3 4))