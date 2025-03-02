(ns chapter-2.exercise-2-48 
  (:require
   [chapter-2.exercise-2-46 :refer [make-vect]]))

;; A directed line segment in the plane can be
;; represented as a pair of vectors — the vector running from
;; the origin to the start-point of the segment, and the vector
;; running from the origin to the end-point of the segment.
;; Use your vector representation from Exercise 2.46 to define
;; a representation for segments with a constructor make-segment
;; and selectors start-segment and end-segment.
(defn make-segment [v1 v2] [v1 v2])
(def start-segment first)
(def end-segment last)

(def v1 (make-vect 1 2))
(def v2 (make-vect 3 4))

(def segm (make-segment v1 v2))