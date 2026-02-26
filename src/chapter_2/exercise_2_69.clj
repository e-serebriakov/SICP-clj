(ns chapter-2.exercise-2-69 
  (:require
   [chapter-2.chapter-2 :refer [adjoin-set make-code-tree make-leaf-set
                                symbols weight]]))

;; The following procedure takes as its argument a list of
;; symbol-frequency pairs (where no symbol appears in more than one pair)
;; and generates a Huﬀman encoding tree according to the Huﬀman algorithm.
;; (define (generate-huffman-tree pairs)
;;   (successive-merge (make-leaf-set pairs)))
;; make-leaf-set is the procedure given above that transforms the list
;; of pairs into an ordered set of leaves. successive-merge is
;; the procedure you must write, using make-code-tree to successively
;; merge the smallest-weight elements of the set until there is only one element
;; left, which is the desired Huﬀman tree. (This procedure is slightly tricky, 
;; but not really complicated. If you find yourself designing a complex
;; procedure, then you are almost certainly doing something wrong. 
;; You can take significant advantage of the fact that we are using 
;; an ordered set representation.)
(defn successive-merge [leaf-sets]
  (let [[frst scnd & others] leaf-sets]
    (if (nil? scnd)
      frst
      (-> (make-code-tree frst scnd)
          (#(adjoin-set % others))
          successive-merge))))

(defn generate-huffman-tree [pairs]
  (successive-merge (make-leaf-set pairs)))

(def sample-pairs
  '((D 1)
    (C 1)
    (B 3)
    (A 8)))
(make-leaf-set sample-pairs)

(def result (generate-huffman-tree sample-pairs))
result
(weight result) 
(symbols result)

