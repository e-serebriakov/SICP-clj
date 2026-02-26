(ns chapter-2.exercise-2-65
  (:require
   [chapter-2.chapter-2 :refer [make-tree]]
   [chapter-2.exercise-2-63 :as c2e63]
   [chapter-2.exercise-2-64 :as c2e64]))

;; Use the results of Exercise 2.63 and Exercise 2.64 to give 
;; Θ(n) implementations of union-set and intersection-set for sets implemented
;; as (balanced) binary trees.
(defn merge-lists-union [l1 l2]
  (cond
    (empty? l1) l2
    (empty? l2) l1
    :else (let [[x1 & xs1] l1
                [x2 & xs2] l2]
            (cond 
              (= x1 x2) (cons x1 (merge-lists-union xs1 xs2))
              (< x1 x2) (cons x1 (merge-lists-union xs1 l2))
              :else (cons x2 (merge-lists-union l1 xs2))))))

(defn union-set [s1 s2]
  (let [l1 (c2e63/tree->list-2 s1)
        l2 (c2e63/tree->list-2 s2)
        merged (merge-lists-union l1 l2)]
    (println "Debug union-set:")
    (println "l1:" l1)
    (println "l2:" l2)
    (println "merged:" merged)
    (println "result:" (c2e64/list->tree merged))
    (c2e64/list->tree merged)))

(defn merge-lists-intersection [l1 l2]
  (if
    (or (empty? l1) (empty? l2)) '()
    (let [[x1 & xs1] l1
          [x2 & xs2] l2]
      (cond
        (= x1 x2) (cons x1 (merge-lists-intersection xs1 xs2))
        (< x1 x2) (merge-lists-intersection xs1 l2)
        :else (merge-lists-intersection l1 xs2)))))

(defn intersection-set [s1 s2]
  (let [l1 (c2e63/tree->list-2 s1)
        l2 (c2e63/tree->list-2 s2)]
    (c2e64/list->tree (merge-lists-intersection l1 l2))))

(def tree-1 (make-tree 7 
                       (make-tree 3 
                                 (make-tree 1 '() '()) 
                                 (make-tree 5 '() '()))
                       (make-tree 9 
                                 '() 
                                 (make-tree 11 '() '()))))

tree-1

(def tree-2 (make-tree 8 
                       (make-tree 4 
                                 (make-tree 1 '() '()) 
                                 (make-tree 5 '() '()))
                       (make-tree 9 
                                 '() 
                                 (make-tree 12 '() '()))))

(union-set tree-1 tree-2)
(intersection-set tree-1 tree-2)