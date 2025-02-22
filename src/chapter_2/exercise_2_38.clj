(ns chapter-2.exercise-2-38)

;; The accumulate procedure is also known as
;; fold-right, because it combines the first element of the sequence
;; with the result of combining all the elements to the right. 
;; There is also a fold-left, which is similar to fold-right,
;; except that it combines elements working in the opposite direction:
(defn fold-right [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence) 
        (fold-right op initial (rest sequence)))))

(defn fold-left [op initial sequence]
  (if (empty? sequence)
    initial
    (op (fold-left op initial (rest sequence))
        (first sequence))))
    
(fold-right / 1 (list 1 2 3)) ; => 3/2
(fold-left / 1 (list 1 2 3)) ; => 1/6

(fold-right list nil (list 1 2 3)) ; => (1 (2 (3 nil)))
(fold-left list nil (list 1 2 3))  ; => (((nil 3) 2) 1)

;; Give a property that op should satisfy to guarantee that
;; fold-right and fold-left will produce the same values for any sequence.

;; The op must satisfy associativity, (op (op a b) c) = (op a (op b c))
;; Examples: + and *
(fold-right + 0 (list 1 2 3)) ; => 6
(fold-left + 0 (list 1 2 3))  ; => 6