(ns chapter-2.exrcise-2-50 
  (:require
   [chapter-2.chapter-2 :refer [transform-painter]]
   [chapter-2.exercise-2-46 :refer [make-vect]]))

;; Define the transformation flip-horiz, which
;; flips painters horizontally, and transformations that rotate
;; painters counterclockwise by 180 degrees and 270 degrees.

(defn flip-horiz [painter]
  (transform-painter painter
                     (make-vect 1.0 0.0)
                     (make-vect 0.0 0.0)
                     (make-vect 1.0 1.0)))


(defn rotate180 [painter]
  (transform-painter painter
                     (make-vect 1.0 1.0)
                     (make-vect 0.0 1.0)
                     (make-vect 1.0 0.0)))

(defn rotate270 [painter]
  (transform-painter painter
                     (make-vect 0.0 1.0)
                     (make-vect 0.0 0.0)
                     (make-vect 1.0 1.0)))