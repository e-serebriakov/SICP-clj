(ns chapter-2.exercise-2-17)

;; Define a procedure last-pair that returns
;; the list that contains only the last element of a given (nonempty)
;; list:
(defn last-pair [l]
  (cond 
    (empty? l) nil
    (nil? (next l)) (list (first l))
    :else (last-pair (rest l))))

(last-pair (list 1 2 3))
(last-pair ())

(defn last-pair [l]
  (take-last 1 l))
  
(last-pair (list 1 2 3))
(last-pair ())

(defn last-pair [l]
  (when (seq l)
    (list (last l))))

(last-pair (list 1 2 3))
(last-pair ())