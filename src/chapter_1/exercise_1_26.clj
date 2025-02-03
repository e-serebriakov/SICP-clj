(ns chapter-1.exercise-1-26)

;; Louis Reasoner is having great diﬃculty doing Exercise 1.24.
;; His `fast-prime?` test seems to run more slowly than his `prime?` test.
;; Louis calls his friend Eva Lu Ator over to help.
;; When they examine Louis’s code, they find that he has rewritten the `expmod`
;; procedure to use an explicit multiplication, rather than calling square:
(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (* (expmod base (/ exp 2) m)
                            (expmod base (/ exp 2) m))
                         m)
        :else (mod (* base (expmod base (- exp 1) m)) m)))

;; “I don’t see what diﬀerence that could make,” says Louis.
;; “I do.” says Eva. “By writing the procedure like that, you
;; have transformed the Θ(log n) process into a Θ(n) process.”
;; Explain.

;; It forces interepreter calculate `(expmod base (/ exp 2) m)` twice
;; for each step that's why he transformed the Θ(log n) process into a Θ(n) process.