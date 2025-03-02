(ns chapter-2.exercise-2-44 
  (:require
   [chapter-2.chapter-2 :refer [below beside test-painter]]))

;; Define the procedure up-split used by corner-split. 
;; It is similar to right-split, except that it switches
;; the roles of below and beside.
(defn up-split [painter n]
  (if (= n 0)
    painter
    (let [smaller (up-split painter (dec n))]
      (below painter (beside smaller smaller)))))

(def test-paint (test-painter "X"))
(println (up-split test-paint 2))