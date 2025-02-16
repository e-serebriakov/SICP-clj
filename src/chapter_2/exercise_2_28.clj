(ns chapter-2.exercise-2-28)

;; Write a procedure fringe that takes as argument 
;; a tree (represented as a list) and returns a list whose
;; elements are all the leaves of the tree arranged in let-to-right order.
;; For example,
;; (define x (list (list 1 2) (list 3 4))) 
;; (fringe x) -> (1 2 3 4)
;; (fringe (list x x)) -> (1 2 3 4 1 2 3 4)
(defn fringe [tree]
  (reduce (fn [acc, x]
            (concat acc
                    (if (list? x)
                      (fringe x)
                      (list x))))
          ()
          tree))

(defn fringe [tree]
  (reduce (fn [acc, x]
            (into acc
                    (if (coll? x)
                      (fringe x)
                      [x])))
          []
          tree))

(defn fringe [tree]
  (filter (complement coll?) (tree-seq coll? seq tree)))

(defn fringe [tree]
  (mapcat #(if (coll? %) (fringe %) [%]) tree))

(defn fringe [tree]
  (flatten tree))

(def x (list (list 1 2) (list 3 4)))
(fringe x)
(fringe (list x x))