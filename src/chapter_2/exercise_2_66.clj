(ns chapter-2.exercise-2-66 
  (:require
   [chapter-2.chapter-2 :refer [entry make-tree left-branch right-branch]]))

;; Implement the lookup procedure for the case
;; where the set of records is structured as a binary tree, 
;; ordered by the numerical values of the keys.
(defn lookup [given-key set-of-records]
  (cond
    (empty? set-of-records) false
    (= given-key (entry set-of-records)) true
    (< given-key (entry set-of-records)) (lookup given-key
                                                 (left-branch set-of-records))
    :else (lookup given-key (right-branch set-of-records))))


(def tree-1 (make-tree 7 
                       (make-tree 3 
                                 (make-tree 1 '() '()) 
                                 (make-tree 5 '() '()))
                       (make-tree 9 
                                 '() 
                                 (make-tree 11 '() '()))))

(lookup 2 '())   ; => false
(lookup 7 tree-1) ; => true
(lookup 3 tree-1) ; => true