(ns chapter-2.exercise-2-45 
  (:require
   [chapter-2.chapter-2 :refer [below beside test-painter]]))

;; right-split and up-split can be expressed
;; as instances of a general splitting operation. 
;; Define a procedure split with the property that evaluating
;; (define right-split (split beside below))
;; (define up-split (split below beside))
;; produces procedures right-split and up-split with the
;; same behaviors as the ones already defined.
(defn split [first-op second-op]
  (fn [painter n]
    (if (= n 0)
      painter
      (let [smaller ((split first-op second-op) painter (dec n))]
        (first-op painter (second-op painter smaller))))))

(def right-split (split beside below))
(def up-split (split below beside))

(def test-paint (test-painter "X"))
(right-split test-paint 2)
(up-split test-paint 1)

