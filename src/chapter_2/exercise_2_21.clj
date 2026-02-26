(ns chapter-2.exercise-2-21)


;; The procedure square-list takes a list of
;; numbers as argument and returns a list of the squares of those numbers.
;; (square-list (list 1 2 3 4))
;; (1 4 9 16)
;; Here are two diﬀerent definitions of square-list. 
;; Complete both of them by filling in the missing expressions:
(defn square-list [items]
  (if (empty? items)
    nil
    (cons (* (first items) (first items))
          (square-list (rest items)))))

(square-list (list 1 2 3 4))

(defn square-list [items]
  (map #(* % %) items))

(square-list (list 1 2 3 4))