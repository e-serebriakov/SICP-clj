(ns chapter-2.exercise-2-51 
  (:require
   [chapter-2.chapter-2 :refer [beside]]
   [chapter-2.exercise-2-46 :refer [make-vect]]
   [chapter-2.exrcise-2-50 :refer [rotate270 transform-painter]]))

;; Define the below operation for painters. below
;; takes two painters as arguments. The resulting painter, given
;; a frame, draws with the first painter in the bottom of the
;; frame and with the second painter in the top. Define below
;; in two diﬀerent ways—first by writing a procedure that is
;; analogous to the beside procedure given above, and again
;; in terms of beside and suitable rotation operations (from
;; Exercise 2.50).
(defn below [painter1 painter2]
  (let [split-point (make-vect 0.0 0.5)
        paint-top (transform-painter painter2
                                     split-point
                                     (make-vect 1.0 0.5)
                                     (make-vect 0.0 1.0))
        paint-bottom (transform-painter painter1
                                        (make-vect 0.0 0.0)
                                        (make-vect 1.0 0.0)
                                        split-point)]
    (fn [frame] 
      (paint-top frame)
      (paint-bottom frame))))

(defn beside-rotation [painter1 painter2]
  (let [painter1-270 (rotate270 painter1)
        painter2-270 (rotate270 painter2)
        beside-result (beside painter1-270 painter2-270)]
    (rotate270 (rotate270 (rotate270 beside-result)))))