(ns chapter-1.exercise-1-36 
  (:require
    [clojure.math :as math]))

;; Modify `fixed-point` so that it prints the
;; sequence of approximations it generates, using the newline
;; and display primitives shown in Exercise 1.22. Then find
;; a solution to x^x = 1000 by finding a fixed point of x →
;; log(1000)/ log(x). (Use Scheme’s primitive log procedure,
;; which computes natural logarithms.) Compare the number
;; of steps this takes with and without average damping. (Note
;; that you cannot start `fixed-point` with a guess of 1, as this
;; would cause division by log(1)= 0.)
(defn fixed-point [f first-guess]
  (let [tolerance 0.00001]
    (letfn [(close-enough? [v1 v2]
              (let [diff (abs (- v1 v2))]
                (< diff tolerance)))
            (iterate-guess [guess]
              (println "\n Testing guess:", guess)
              (let [next-guess (f guess)]
                (if (close-enough? guess next-guess)
                  next-guess
                  (iterate-guess next-guess))))]
      (iterate-guess first-guess))))

(fixed-point #(/ (math/log 1000) (math/log %)) 2.0)

(defn average-damp [f]
  #(/ (+ % (f %)) 2))

(fixed-point (average-damp #(/ (math/log 1000) (math/log %))) 2.0)

;; The number of steps is several times lower with average damping