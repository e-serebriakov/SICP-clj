(ns chapter-2.exercise-2-31)

;; Abstract your answer to Exercise 2.30 to
;; produce a procedure tree-map with the property that square-
;; tree could be defined as
;; (define (square-tree tree) (tree-map square tree))
(defn tree-map [func tree]
  (map (fn [node]
         (if (number? node)
           (func node)
           (tree-map func node))) tree))

(defn tree-map [func root]
  (clojure.walk/postwalk
    #(if (number? %) (func %) %)
    root))

(defn square-tree [tree] 
  (tree-map #(* % %) tree))

(def l (list 1
             (list 2 (list 3 4) 5)
             (list 6 7)))

(square-tree l)