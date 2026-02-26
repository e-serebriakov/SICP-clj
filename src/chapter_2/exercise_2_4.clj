(ns chapter-2.exercise-2-4)

;; Here is an alternative procedural representation of pairs. 
;; For this representation, 
;; verify that (car (cons x y)) yields x for any objects x and y.
(defn cons1 [x y] 
  #(% x y))
 
(defn car [z]
  (z (fn [p q] p)))

(car (cons1 1 2))
(car #(% 1 2))
(#(% 1 2) (fn [p q] p))
((fn [p q] p) 1 2)
;; What is the corresponding definition of cdr? (Hint: To verify 
;; that this works, make use of the substitution model of Section 1.1.5.)
(defn cdr [z]
  (z (fn [p q] q)))

(cdr (cons1 1 2))
(cdr #(% 1 2))
(#(% 1 2) (fn [p q] q))
((fn [p q] q) 1 2)



