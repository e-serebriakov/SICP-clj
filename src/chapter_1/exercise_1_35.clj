(ns chapter-1.exercise-1-35 
  (:require
   [chapter-1.chapter-1 :refer [fixed-point]]))

;; Show that the golden ratio ϕ (Section 1.2.2)
;; is a fixed point of the transformation x → 1 + 1/x , and
;; use this fact to compute ϕ by means of the fixed-point procedure.
(fixed-point #(+ 1 (/ 1 %)) 1.0)