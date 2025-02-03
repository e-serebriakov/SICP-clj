(ns chapter-1.exercise-1-11)

; A function f is defined by the rule that
; f(n) = n if n < 3 else f(n - 1) + 2 * f(n - 2) + 3 * f(n - 3)
; Write a procedure that computes f process. Write a procedure that computes f iterative process.

; Recursive
(defn f [n] 
  (if (< n 3) n
      (+ (f (- n 1))
         (* 2 (f (- n 2)))
         (* 3 (f (- n 3))))))

(f 4)
; (+ (f (- 4 1)) (* 2 (f (- 4 2))) (* 3 (f (- 4 3))))
; (+ (f 3) (* 2 (f 2)) (* 3 (f 1)))
; (+ (+ (f (- 3 1)) (* 2 (f (- 3 2))) (* 3 (f (- 3 3)))) (* 2 2) (* 3 1))
; (+ (+ (f 2) (* 2 (f 1)) (* 3 (f 0))) (* 2 2) (* 3 1))
; (+ (+ 2 (* 2 1) (* 3 0)) (* 2 2) (* 3 1))
; (+ (+ 2 2 0) (* 2 2) (* 3 1))
; (+ 4 4 3)
; 11

(defn f [n]
  (letfn [(f-iter [fn-3 fn-2 fn-1 n] (if (= n 0) fn-3
                                        (f-iter fn-2
                                                fn-1
                                                (+ fn-1
                                                   (* 2 fn-2)
                                                   (* 3 fn-3)) (- n 1))))]
    (f-iter 0 1 2 n)))

(f 4)
; (f-iter 0 1 2 4)
; (f-iter 1 2 (+ 2 (* 2 1) (* 3 0)) (- 4 1))
; (f-iter 1 2 (+ 2 2 0) 3)
; (f-iter 1 2 4 3)
; (f-iter 2 4 (+ 4 (* 2 2) (* 3 1)) (- 3 1))
; (f-iter 2 4 (+ 4 4 3) 2)
; (f-iter 2 4 11 2)
; (f-iter 4 11 (+ 11 (* 2 4) (* 3 2)) (- 2 1))
; (f-iter 4 11 (+ 11 8 6) 1)
; (f-iter 4 11 25 1)
; (f-iter 11 25 (+ 25 (* 2 11) (* 3 4)) (- 1 1))
; (f-iter 11 25 (+ 25 22 12) 0)
; (f-iter 11 25 59 0)
; 11