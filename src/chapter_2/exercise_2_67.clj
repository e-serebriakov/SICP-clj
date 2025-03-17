(ns chapter-2.exercise-2-67 
  (:require
   [chapter-2.chapter-2 :refer [decode make-code-tree make-leaf]]))

;; Define an encoding tree and a sample message:
(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree
                   (make-leaf 'B 2)
                   (make-code-tree
                    (make-leaf 'C 1)
                    (make-leaf 'D 1)))))

(def sample-message '(1 1 0 0 1 0 1 0 1 1 1 0))

(decode sample-message sample-tree) ; => [C A B B D A]