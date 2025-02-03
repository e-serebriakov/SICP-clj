(ns chapter-1.exercise-1-21)

;; Use the smallest-divisor procedure to find
;; the smallest divisor of each of the following numbers: 199, 1999, 19999.

(defn smallest-divisor [n] (letfn [(divides? [a b] (= (mod b a) 0))
                                   (find-divisor [n test-divisor] (cond (> (* test-divisor test-divisor) n) n
                                                                        (divides? test-divisor n) test-divisor
                                                                        :else (find-divisor n (inc test-divisor))))]
                             (find-divisor n 2)))

(smallest-divisor 199) ;; 199
(smallest-divisor 1999) ;; 1999
(smallest-divisor 19999) ;; 7