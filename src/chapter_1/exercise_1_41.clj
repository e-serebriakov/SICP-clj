(ns chapter-1.exercise-1-41)

;; Define a procedure `double` that takes a procedure
;; of one argument as argument and returns a procedure
;; that applies the original procedure twice. For example,
;; if `inc` is a procedure that adds 1 to its argument, then
;; (double inc) should be a procedure that adds 2. What
;; value is returned by
;; (((double (double double)) inc) 5)
(defn double [f]
  #(f (f %)))

((double inc) 1) ;; 3

(((double (double double)) inc) 5) ;; 21