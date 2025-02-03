(ns chapter-1.exercise-1-34)

;; Suppose we define the procedure
(defn f [g] (g 2))
;; Then we have
(defn square [x] (* x x))
(f square) ; 4
(f #(* % (+ % 1))) ; 6
;; What happens if we (perversely) ask the interpreter to evaluate
;; the combination (f f)? Explain.
;; (f f)
;; (f 2)
;; (2 2)
;; It leads to an error