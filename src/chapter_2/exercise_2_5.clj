(ns chapter-2.exercise-2-5 
  (:require
    [clojure.math :as math]))

;; Show that we can represent pairs of nonnegtive 
;; integers using only numbers and arithmetic operations
;; if we represent the pair a and b as the integer that is
;; the product 2^a3^b. Give the corresponding definitions of the
;; procedures cons, car, and cdr.
(defn cons1 [a b]
  (* (math/pow 2 a)
     (math/pow 3 b)))

(defn count-divisions [x n]
  (letfn [(iter [i result]
                (if (zero? (mod i n))
                  (iter (/ i n) (inc result))
                  result))]
    (iter x 0)))

(defn car [x]
  (count-divisions x 2))


(defn cdr [x] 
  (count-divisions x 3))

(def pair (cons1 2 3))
pair
(car pair)
(cdr pair)


