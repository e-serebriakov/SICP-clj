(ns chapter-2.exrcise-2-50 
  (:require
   [chapter-2.chapter-2 :refer [frame-coord-map]]
   [chapter-2.exercise-2-46 :refer [make-vect sub-vect]]
   [chapter-2.exercise-2-47 :refer [make-frame]]))

;; Define the transformation flip-horiz, which
;; flips painters horizontally, and transformations that rotate
;; painters counterclockwise by 180 degrees and 270 degrees.
(defn transform-painter [painter origin corner1 corner2]
  (fn [frame]
    (let [m (frame-coord-map frame)
          new-origin (m origin)]
      (painter (make-frame
                new-origin
                (sub-vect (m corner1) new-origin)
                (sub-vect (m corner2) new-origin))))))

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