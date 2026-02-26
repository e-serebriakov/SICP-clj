(ns chapter-1.exercise-1-19)

;; There is a clever algorithm for computing
;; the Fibonacci numbers in a logarithmic number of steps.
;; Recall the transformation of the state variables a and b in
;; the fib-iter process of Section 1.2.2: a ← a + b and b ← a.
;; Call this transformation T, and observe that applying T
;; over and over again n times, starting with 1 and 0, produces
;; the pair Fib(n + 1) and Fib(n). In other words, the Fibonacci
;; numbers are produced by applying T^n , the nth power of the
;; transformation T, starting with the pair (1, 0). Now consider
;; T to be the special case of p = 0 and q = 1 in a family of
;; transformations Tpq , where Tpq transforms the pair (a, b)
;; according to a ← bq + aq + ap and b ← bp + aq. Show
;; that if we apply such a transformation Tpq twice, the eﬀect
;; is the same as using a single transformation Tp′q′ of the
;; same form, and compute p′ and q′ in terms of p and q. This
;; gives us an explicit way to square these transformations,
;; and thus we can compute Tn using successive squaring, as
;; in the fast-expt procedure. Put this all together to com-
;; plete the following procedure, which runs in a logarithmic
;; number of steps

;; a1 = bq + aq + ap
;; b1 = bp + aq

;; a2 = b1q + a1q + a1p
;; b2 = b1p + a1q

;; a2 = (bp + aq)q + (bq + aq + ap)q + (bq + aq + ap)p
;; b2 = (bp + aq)p + (bq + aq + ap)q

;; a2 = bpq + aq^2 + bq^2 + aq^2 + apq + bpq + apq + ap^2
;; b2 = bp^2 + apq + bq^2 + aq^2 + apq

;; a2 = b(pq + q^2 + pq) + a(q^2 + q^2 + qp + qp + p^2)
;; b2 = b(p^2 + q^2) + a(pq + q^2 + pq)

;; a2 = b(2pq + q^2) + a(2q^2 + 2qp + p^2) // a = bp' + aq' + ap'
;; b2 = b(p^2 + q^2) + a(2qp + q^2) // b = bp' + aq'

;; p' = p^2 + q^2
;; q' = 2pq + q^2

(defn fib [n] (letfn [(iter [a b p q count]
                            (cond (= count 0) b
                                  (even? count) (iter a
                                                      b
                                                      (+ (* p p) (* q q))
                                                      (+ (* 2 p q) (* q q))
                                                      (/ count 2))
                                  :else (iter (+ (* b q) (* a q) (* a p))
                                              (+ (* b p) (* a q))
                                              p
                                              q
                                              (dec count))))]
                (iter 1 0 0 1 n)))

(fib 7)
(map fib '(0 1 2 3 4 5))
