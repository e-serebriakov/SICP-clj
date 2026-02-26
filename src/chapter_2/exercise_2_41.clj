(ns chapter-2.exercise-2-41 
  (:require
   [chapter-2.chapter-2 :refer [enumerate-interval flatmap]]))

;; Write a procedure to find all ordered triples
;; of distinct positive integers i, j, and k less than or equal to
;; a given integer n that sum to a given integer s.
(defn equal-sum? [l s]
  (= (reduce + 0 l) s))

(defn distinct-integer-triples [n s]
  (filter (fn [l] (equal-sum? l s))
          (flatmap (fn [x]
                     (flatmap (fn [y]
                                (map (fn [z] (list x y z))
                                     (enumerate-interval (inc y) n)))
                              (enumerate-interval (inc x) n)))
                   (enumerate-interval 1 n))))

(defn distinct-integer-triples [n s]
  (->> (for [i (range 1 (inc n))
             j (range (inc i) (inc n))
             k (range (inc j) (inc n))
             :when (= (+ i j k) s)]
         (list i j k))))

(distinct-integer-triples 5 10)