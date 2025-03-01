(ns chapter-2.exercise-2-49
  (:require [chapter-2.exercise-2-46 :refer [make-vect]]
            [chapter-2.exercise-2-48 :refer [make-segment]]))

;; Use segments->painter to define the following primitive painters:
;; a. The painter that draws the outline of the designated frame.
;; b. The painter that draws an "X" by connecting opposite corners of the frame.
;; c. The painter that draws a diamond shape by connecting
;;    the midpoints of the sides of the frame.
;; d. The wave painter.

(defn segments->painter [segment-list]
  (fn [frame]
    (doseq [segment segment-list]
      (draw-line
       ((frame-coord-map frame)
        (start-segment segment))
       ((frame-coord-map frame)
        (end-segment segment))))))


;; a. Outline painter
(def outline-painter
  (segments->painter
   [(make-segment (make-vect 0 0) (make-vect 1 0))    ; bottom
    (make-segment (make-vect 1 0) (make-vect 1 1))    ; right
    (make-segment (make-vect 1 1) (make-vect 0 1))    ; top
    (make-segment (make-vect 0 1) (make-vect 0 0))]))  ; left

;; b. X painter
(def x-painter
  (segments->painter
   [(make-segment (make-vect 0 0) (make-vect 1 1))    ; diagonal 1
    (make-segment (make-vect 0 1) (make-vect 1 0))]))  ; diagonal 2

;; c. Diamond painter
(def diamond-painter
  (segments->painter
   [(make-segment (make-vect 0.5 0) (make-vect 1 0.5))   ; bottom-right
    (make-segment (make-vect 1 0.5) (make-vect 0.5 1))   ; top-right
    (make-segment (make-vect 0.5 1) (make-vect 0 0.5))   ; top-left
    (make-segment (make-vect 0 0.5) (make-vect 0.5 0))]))  ; bottom-left