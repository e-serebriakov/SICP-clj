(ns chapter-2.exercise-2-43 
  (:require
   [chapter-2.chapter-2 :refer [flatmap]]
   [chapter-2.exercise-2-42 :refer [adjoin-position draw-board empty-board
                                    safe?]]))

;; Louis Reasoner is having a terrible time doing Exercise 2.42.
;; His queens procedure seems to work, but it runs extremely slowly. 
;; (Louis never does manage to wait long enough for it
;; to solve even the 6×6 case.) When Louis
;; asks Eva Lu Ator for help, she points out that he has interchanged 
;; the order of the nested mappings in the flatmap, writing it as
;; (flatmap
;;   (lambda (new-row)
;;     (map (lambda (rest-of-queens)
;;            (adjoin-position new-row k rest-of-queens))
;;          (queen-cols (- k 1))))
;;   (enumerate-interval 1 board-size))
;; Explain why this interchange makes the program run slowly.
;; Estimate how long it will take Louis’s program to solve the
;; eight-queens puzzle, assuming that the program in Exercise
;; 2.42 solves the puzzle in time T.

;; Original
(defn queens [board-size]
  (letfn [(queen-cols [k]
            (if (zero? k)
              (list empty-board)
              (->> (queen-cols (dec k))
                   (flatmap (fn [rest-of-queens]
                              (->> (range 1 (inc board-size))
                                   (map #(adjoin-position % k rest-of-queens)))))
                   (filter #(safe? k %)))))]
    (queen-cols board-size)))

(queens 6)
(println (draw-board (first (queens 6)) 6))

;; Louis's version
;; the recursive call to queen-cols is inside the loop over rows, 
;; which means it gets called board-size times instead of just once
;; T(board-size)^(board-size - 1)
;; T — original
;; board-size — caused by (range 1 (inc board-size))
;; board-size - 1 levels of recursion
(defn queens [board-size]
  (letfn [(queen-cols [k]
            (if (zero? k)
              (list empty-board)
              (->> (range 1 (inc board-size))
                   (flatmap (fn [new-row] 
                              (->> (queen-cols (dec k))
                                   (map (fn [rest-of-queens]
                                          (adjoin-position new-row k rest-of-queens))))))
                   (filter #(safe? k %))
              )))]
    (queen-cols board-size)))

(queens 6)
(println (draw-board (first (queens 6)) 6))
