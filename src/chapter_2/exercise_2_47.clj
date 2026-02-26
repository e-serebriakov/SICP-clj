(ns chapter-2.exercise-2-47)

;; Here are two possible constructors for frames:
;; (define (make-frame origin edge1 edge2)
;;   (list origin edge1 edge2))
;; (define (make-frame origin edge1 edge2)
;;   (cons origin (cons edge1 edge2)))
;; For each constructor supply the appropriate selectors to
;; produce an implementation for frames.
(defn make-frame [origin edge1 edge2]
  (list origin edge1 edge2))

(def origin-frame first)
(def edge-1-frame second)
(def edge-2-frame last)

(def frame (make-frame 1 2 3))
(origin-frame frame)
(edge-1-frame frame)
(edge-2-frame frame)


(defn make-frame [origin edge1 edge2]
  (cons origin (cons edge1 edge2)))

(def origin first)
(def edge-1 (comp first rest))
(def edge-2 (comp first rest rest))

(origin frame)
(edge-1 frame)
(edge-2 frame)
