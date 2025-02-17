(ns chapter-2.exercise-2-7)

;; Alyssa’s program is incomplete because she
;; has not specified the implementation of the interval abstraction.
;; Here is a definition of the interval constructor
(defn make-interval [a b] [a b])
;; Define selectors upper-bound and lower-bound to complete
;; the implementation.
(def upper-bound last)
(def lower-bound first)