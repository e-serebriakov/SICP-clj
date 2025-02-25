(ns chapter-2.exercise-2-44)

;; Define the procedure up-split used by corner-split. 
;; It is similar to right-split, except that it switches
;; the roles of below and beside.
(defn up-split [painter n]
  (if (= n 0)
    painter
    (let [smaller (up-split painter (dec n))]
      (below paiter (beside smaller smaller)))))