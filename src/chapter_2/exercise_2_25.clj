(ns chapter-2.exercise-2-25)

;; Give combinations of cars and cdrs that
;; will pick 7 from each of the following lists:
;; (1 3 (5 7) 9)
;; ((7))
;; (1 (2 (3 (4 (5 (6 7))))))
(def list-1 (list 1 3  (list 5 7) 9))
(last (first (rest (rest list-1))))

(def list-2 (list (list 7)))
(first (first list-2))

(def list-3 (list 1 
                  (list 2 
                        (list 3 
                              (list 4 
                                    (list 5 
                                          (list 6 7)))))))

(last (last (last (last (last (last list-3))))))