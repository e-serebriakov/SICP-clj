(ns chapter-1.exercise-1-20)

;; The process that a procedure generates is
;; of course dependent on the rules used by the interpreter.
;; As an example, consider the iterative gcd procedure given
;; above. Suppose we were to interpret this procedure using
;; normal-order evaluation, as discussed in Section 1.1.5. (The
;; normal-order-evaluation rule for if is described in Exercise
;; 1.5.) Using the substitution method (for normal order), illustrate
;; the process generated in evaluating (gcd 206 40) and
;; indicate the remainder operations that are actually performed. 
;; How many remainder operations are actually performed in the normal-order
;; evaluation of (gcd 206 40)? In the applicative-order evaluation?
(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (mod a b))))

;; Normal-order evaluation
(gcd 206 40)
;; Expand according to definition
;; (if (= 40 0)
;;    206
;;    (gcd 40 (mod 206 40)))

;; 40 ≠ 0, so evaluate second branch
;; Substitute 40 and (mod 206 40) into gcd definition
;; (if (= (mod 206 40) 0)                          ; Eval #1: (mod 206 40) = 6
;;   40
;;    (gcd (mod 206 40) (mod 40 (mod 206 40))))

;; 6 ≠ 0, so evaluate second branch
;; Substitute expressions into gcd definition
;; (if (= (mod 40 (mod 206 40)) 0)                 ; Eval #2: (mod 206 40) = 6
                                                ; Eval #3: (mod 40 6) = 4
;;    (mod 206 40)
;;    (gcd (mod 40 (mod 206 40))
;;         (mod (mod 206 40) (mod 40 (mod 206 40)))))

;; 4 ≠ 0, so evaluate second branch
;; Substitute expressions into gcd definition
;; (if (= (mod (mod 206 40)                        ; Eval #4: (mod 206 40) = 6
;;            (mod 40 (mod 206 40))) 0)           ; Eval #5: (mod 206 40) = 6
                                                ; Eval #6: (mod 40 6) = 4
                                                ; Eval #7: (mod 6 4) = 2
;;    (mod 40 (mod 206 40))
;;    (gcd (mod (mod 206 40) (mod 40 (mod 206 40)))
;;         (mod (mod 40 (mod 206 40))
;;              (mod (mod 206 40) (mod 40 (mod 206 40))))))

;; 2 ≠ 0, so evaluate second branch
;; Substitute expressions into gcd definition
;; (if (= (mod (mod 40 (mod 206 40))              ; Eval #8: (mod 206 40) = 6
;;            (mod (mod 206 40)                   ; Eval #9: (mod 40 6) = 4
;;                 (mod 40 (mod 206 40)))) 0)     ; Eval #10: (mod 206 40) = 6
                                               ; Eval #11: (mod 40 6) = 4
                                               ; Eval #12: (mod 6 4) = 2
                                               ; Eval #13: (mod 4 2) = 0
;;     (mod (mod 206 40)                          
;;         (mod 40 (mod 206 40)))
;;    (gcd ...))  ; We don't need to expand this since the if condition is true

;; 0 = 0, so evaluate first branch
;; (mod (mod 206 40)                              ; Eval #14: (mod 206 40) = 6
;;     (mod 40 (mod 206 40)))                    ; Eval #15: (mod 206 40) = 6
                                              ; Eval #16: (mod 40 6) = 4
                                              ; Eval #17: (mod 6 4) = 2

; Final result: 2

;; Applicative-order evaluation
(gcd 206 40)
(gcd 40 (mod 206 40))  ; mod evaluates to 6
(gcd 40 6)
(gcd 6 (mod 40 6))     ; mod evaluates to 4
(gcd 6 4)
(gcd 4 (mod 6 4))      ; mod evaluates to 2
(gcd 4 2)
(gcd 2 (mod 4 2))      ; mod evaluates to 0
(gcd 2 0)
