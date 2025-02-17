(ns chapter-2.exercise-2-22)

;;  Louis Reasoner tries to rewrite the first square-
;; list procedure of Exercise 2.21 so that it evolves an iterative process:
(defn square-list [items]
  (letfn [(iter [things answer]
            (if (empty? things)
              answer
              (iter (rest things)
                    (cons (* (first things) (first things))
                          answer))))]
    (iter items '())))

;; Unfortunately, defining square-list this way produces the
;; answer list in the reverse order of the one desired. Why?
;; Louis then tries to fix his bug by interchanging the arguments to cons:

(square-list '(1 2 3 4))
;; (iter '(1 2 3 4) '())
;; (iter '(2 3 4) '(1))
;; (iter '(3 4) '(4 1))
;; (iter '(4) '(9 4 1))
;; (iter '() '(16 9 4 1))
;; '(16 9 4 1)

(defn square-list [items]
  (letfn [(iter [things answer]
            (if (empty? things)
              answer
              (iter (rest things)
                    (cons answer
                          (* (first things) (first things))))))]
    (iter items '())))
;; It doesn't work as cons expects the first argument to be a list and the second to be an element

;; Correct
(defn square-list [items]
  (letfn [(iter [things answer]
            (if (empty? things)
              answer
              (iter (rest things)
                    (concat answer
                            (list (* (first things) (first things)))))))]
    (iter items '())))

(square-list '(1 2 3 4))
