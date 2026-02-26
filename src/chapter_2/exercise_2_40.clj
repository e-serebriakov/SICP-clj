(ns chapter-2.exercise-2-40 
  (:require
   [chapter-2.chapter-2 :refer [enumerate-interval flatmap make-pair-sum
                                prime-sum?]]))

;; Define a procedure unique-pairs that, given
;; an integer n, generates the sequence of pairs (i, j) with 1 ≤ j < i ≤ n. 
;; Use unique-pairs to simplify the definition of prime-sum-pairs given above.
(defn unique-pairs [n]
  (flatmap (fn [x]
             (map (fn [y] (list x y))
                  (enumerate-interval 1 (dec x))))
           (enumerate-interval 1 n)))

(unique-pairs 5)

(defn prime-sum-pairs [n]
  (map make-pair-sum
       (filter prime-sum? (unique-pairs n))))

(prime-sum-pairs 5)