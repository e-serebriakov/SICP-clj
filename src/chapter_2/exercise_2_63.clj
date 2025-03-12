(ns chapter-2.exercise-2-63 
  (:require
   [chapter-2.chapter-2 :refer [entry left-branch make-tree]]
   [chapter-2.exercise-2-29 :refer [right-branch]]))

;; Each of the following two procedures converts a binary tree to a list.
;; (define (tree->list-1 tree)
;;   (if (null? tree)
;;     '()
;;     (append (tree->list-1 (left-branch tree)) 
;;             (cons (entry tree)
;;                   (tree->list-1
;;                     (right-branch tree))))))

;; (define (tree->list-2 tree)
;;   (define (copy-to-list tree result-list)
;;     (if (null? tree)
;;       result-list
;;       (copy-to-list (left-branch tree)
;;                     (cons (entry tree)
;;                           (copy-to-list
;;                             (right-branch tree)
;;                             result-list)))))
;;   (copy-to-list tree '()))

(defn tree->list-1 [tree]
  (if (empty? tree)
    '()
    (concat (tree->list-1 (left-branch tree))
            (cons (entry tree)
                  (tree->list-1 (right-branch tree))))))

(defn tree->list-2 [tree]
  (letfn [(copy-to-list [tree result-list]
          (if (empty? tree)
            result-list
            (copy-to-list (left-branch tree)
                          (cons (entry tree)
                                (copy-to-list (right-branch tree)
                                              result-list)))))]
    (copy-to-list tree '())))

;; a. Do the two procedures produce the same result for
;;    every tree? If not, how do the results diﬀer? What lists
;;    do the two procedures produce for the trees in Figure 2.16?

;;    Tree 1:          Tree 2:          Tree 3:
;;      7                3                5
;;     / \              / \              / \
;;    3   9            1   7            3   9
;;   / \   \              / \          /   / \
;;  1   5   11          5   9         1   7   11
;;                           \
;;                            11

(def tree-1 (make-tree 7 
                       (make-tree 3 
                                 (make-tree 1 '() '()) 
                                 (make-tree 5 '() '()))
                       (make-tree 9 
                                 '() 
                                 (make-tree 11 '() '()))))

(def tree-2 (make-tree 3 
                       (make-tree 1 '() '())
                       (make-tree 7 
                                 (make-tree 5 '() '())
                                 (make-tree 9 
                                           '() 
                                           (make-tree 11 '() '())))))

(def tree-3 (make-tree 5 
                       (make-tree 3 
                                 (make-tree 1 '() '()) 
                                 '())
                       (make-tree 9 
                                 (make-tree 7 '() '()) 
                                 (make-tree 11 '() '()))))

(tree->list-1 tree-1) ; => (1 3 5 7 9 11)
(tree->list-2 tree-1) ; => (1 3 5 7 9 11)


(tree->list-1 tree-2) ; => (1 3 5 7 9 11)
(tree->list-2 tree-2) ; => (1 3 5 7 9 11)

(tree->list-1 tree-3) ; => (1 3 5 7 9 11)
(tree->list-2 tree-3) ; => (1 3 5 7 9 11)


;; b. Do the two procedures have the same order of growth
;;    in the number of steps required to convert a balanced
;;    tree with n elements to a list? If not, which one grows
;;    more slowly?

;; tree->list-2 is more efficient because we don't copy items with concat, 
;; we add items one at a time with cons.
;; tree->list-1: O(n log n)
;; tree->list-2: O(n)