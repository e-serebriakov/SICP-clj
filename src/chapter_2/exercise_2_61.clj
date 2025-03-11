(ns chapter-2.exercise-2-61)

;; Give an implementation of adjoin-set using the ordered representation.
;; By analogy with element-of-set? show how to take advantage of the ordering to
;; produce a procedure that requires on the average about half
;; as many steps as with the unordered representation.
(defn adjoin-ordered-set [set x]
  (if (empty? set)
    (list x)
    (cond (< x (first set)) (cons x set)
          (= x (first set)) set
          :else (cons (first set) 
                     (adjoin-ordered-set (rest set) x)))))

(defn adjoin-ordered-set [set x]
  (loop [before [] [head & tail :as after] set]
    (cond
      (empty? after) (concat before [x])
      (< x head) (concat before [x] after)
      (= x head) set
      :else (recur (conj before head) tail))))


(adjoin-ordered-set '(1 2 4) 3)
(adjoin-ordered-set '(1 2 3) 3)