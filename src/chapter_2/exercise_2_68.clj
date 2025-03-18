(ns chapter-2.exercise-2-68 
  (:require
   [chapter-2.chapter-2 :refer [leaf? left-branch make-code-tree make-leaf
                                right-branch symbol-leaf]]))

;; The encode procedure takes as arguments a
;; message and a tree and produces the list of bits that gives 
;; the encoded message.

;; Implementation
(defn encode-symbol [symbol tree]
  (letfn [(traverse [t path]
            (cond
              (leaf? t) (when (= symbol (symbol-leaf t)) path)
              :else (or (traverse (left-branch t) (conj path 0))
                       (traverse (right-branch t) (conj path 1)))))]
    (if-let [result (traverse tree [])]
      result
      (throw (ex-info "Symbol not found in Huffman tree"
                     {:symbol symbol
                      :error :symbol-not-found})))))

(defn encode [message tree]
  (if (empty? message)
    '()
    (concat (encode-symbol (first message) tree)
            (encode (rest message) tree))))
;; encode-symbol is a procedure, which you must write, that
;; returns the list of bits that encodes a given symbol according to 
;; a given tree. You should design encode-symbol so that it signals
;; an error if the symbol is not in the tree at all.
;; Test your procedure by encoding the result you obtained in
;; Exercise 2.67 with the sample tree and seeing whether it is
;; the same as the original sample message.

(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree
                   (make-leaf 'B 2)
                   (make-code-tree
                    (make-leaf 'C 1)
                    (make-leaf 'D 1)))))
(def message '(C A B B D A))
(def message-fail '(C A B B D A F))

(encode message sample-tree) ; => (1 1 0 0 1 0 1 0 1 1 1 0)
(encode message-fail sample-tree)
