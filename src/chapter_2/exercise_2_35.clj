(ns chapter-2.exercise-2-35 
  (:require
   [chapter-2.chapter-2 :refer [accumulate]]))

;; Redefine count-leaves from Section 2.2.2
;; (define (count-leaves x)
;;   (cond ((null? x) 0)
;;     ((not (pair? x)) 1)
;;     (else (+ (count-leaves (car x))
;;              (count-leaves (cdr x))))))
;; as an accumulation:
;; (define (count-leaves t)
;;   (accumulate ⟨??⟩ ⟨??⟩ (map ⟨??⟩ ⟨??⟩)))
(defn count-leaves [t]
  (accumulate +
              0
              (map (fn [node]
                     (if (sequential? node)
                       (count-leaves node)
                       1))
                   t)))

(count-leaves '(1 (2 3) 4))