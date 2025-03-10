(ns chapter-2.exercise-2-59 
  (:require
   [chapter-2.chapter-2 :refer [elements-of-set?]]))

;; Implement the union-set operation for the
;; unordered-list representation of sets.
(defn union-set [s1 s2]
  (cond (empty? s1) s2
        (empty? s2) s1
        (elements-of-set? (first s1) s2) (cons (first s1) (union-set (rest s1) (rest s2)))
        :else (cons (first s1) (union-set (rest s1) s2))))

(defn union-set [s1 s2]
  (distinct (concat s1 s2)))

(defn union-set [s1 s2]
  (reduce (fn [result elem]
            (if (elements-of-set? elem result)
              result
              (cons elem result)))
          s2
          s1))
        
(union-set '(1 2 3) '(2 3 4))