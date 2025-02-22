(ns chapter-2.exercise-2-39 
  (:require
   [chapter-2.exercise-2-38 :refer [fold-left fold-right]]))

;; Complete the following definitions of reverse
;; (Exercise 2.18) in terms of fold-right and fold-left from Exercise 2.38:
;; (define (reverse sequence)
;;   (fold-right (lambda (x y) ⟨??⟩) nil sequence))
;; (define (reverse sequence)
;;   (fold-left (lambda (x y) ⟨??⟩) nil sequence))
(def s (list 1 2 3))

(defn reverse-fr [sequence]
  (fold-right (fn [x acc] (concat acc (list x)))
              nil
              sequence))

(reverse-fr s)


(defn reverse-fl [sequence]
  (fold-left (fn [acc x] (concat acc (list x))) 
             nil
              sequence))

(reverse-fl s)