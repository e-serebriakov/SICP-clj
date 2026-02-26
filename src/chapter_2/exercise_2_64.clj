(ns chapter-2.exercise-2-64 
  (:require
   [chapter-2.chapter-2 :refer [make-tree]]))

;; The following procedure list->tree converts an ordered list
;; to a balanced binary tree. The helper procedure partial-tree 
;; takes as arguments an integer n and list of at least n elements and 
;; constructs a balanced tree containing the first n elements of the list. 
;; The result returned by partial-tree is a pair (formed with cons) whose
;; car is the constructed tree and whose cdr is the list of elements 
;; not included in the tree. 
(defn partial-tree [elements n]
  (if (zero? n)
    (cons '() elements)
    (let [left-size (quot (dec n) 2)
          left-result (partial-tree elements left-size)
          left-tree (first left-result)
          non-left-elements (rest left-result)
          right-size (- n (inc left-size))
          this-entry (first non-left-elements)
          right-result (partial-tree (rest non-left-elements) right-size)
          right-tree (first right-result)
          remaining-elements (rest right-result)]
      (cons (make-tree this-entry left-tree right-tree) remaining-elements))))

(defn list->tree [elements]
  (->> (count elements)
       (partial-tree elements)
       first))

;; a. Write a short paragraph explaining as clearly as you
;;    can how partial-tree works. Draw the tree produced
;;    by list->tree for the list (1 3 5 7 9 11).

;; It takes ordered
;; Takes middle element as a root
;; Recursively calls itself with 
;; 1. elemnts from 0 to middle
;; 2. elements from middle to end
;; subtree from 1 bacomes a left branch
;; subtree from 2 becoms a right branch


(list->tree '(1 3 5 7 9 11)) ; => (5 (1 () (3 () ())) (9 (7 () ()) (11 () ())))
;;    5
;;   / \
;;  1   9
;;  \  / \
;;   3 7  11

;; b. What is the order of growth in the number of steps required 
;; by list->tree to convert a list of n elements?

;; O(n)
;; the algorithm visits each elements exactly once