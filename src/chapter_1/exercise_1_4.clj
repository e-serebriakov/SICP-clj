(ns chapter-1.exercise-1-4)

; Observe that our model of evaluation allows
; for combinations whose operators are compound expressions.
; Use this observation to describe the behavior of the following procedure:
(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

; If b > then a + b, otherwise -> a - b
(a-plus-abs-b 1 2)
(a-plus-abs-b 1 (- 2))