(ns chapter-1.exercise-1-18)

;; Using the results of Exercise 1.16 and Exercise 1.17, 
;; devise a procedure that generates an iterative process for multiplying
;; two integers in terms of adding, doubling,
;; and halving and uses a logarithmic number of steps
(defn *_1 [a b] (letfn [(iter [sum a b] 
                              (if (= b 1)
                                sum
                                (if (even? b)
                                  (iter (+ sum (* a 2)) a (/ b 2))
                                  (iter (+ sum a) a (dec b)))))]
                  (iter 0 a b)))

(*_1 2 3)
