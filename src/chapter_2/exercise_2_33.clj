(ns chapter-2.exrcise-2-33 
  (:require
   [chapter-2.chapter-2 :refer [accumulate]]))

;;  Fill in the missing expressions to complete
;; the following definitions of some basic list-manipulation
;; operations as accumulations:
;; (define (map p sequence)
;;   (accumulate (lambda (x y) ⟨??⟩) nil sequence))
;; (define (append seq1 seq2)
;;   (accumulate cons ⟨??⟩ ⟨??⟩))
;; (define (length sequence)
;;   (accumulate ⟨??⟩ 0 sequence))

(defn map [p sequence]
  (accumulate (fn [x y] (cons (p x) y)) nil sequence))
 
(map #(+ % 1) '(1 2 3))

(defn append [seq1 seq2]
  (accumulate cons seq2 seq1))

(append '(1 2 3) '(4 5 6))

(defn length [sequence]
  (accumulate (fn [x y] (inc y)) 0 sequence))

(length '(1 2 3 4 5 6))