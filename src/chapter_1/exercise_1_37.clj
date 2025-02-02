(ns chapter-1.exercise-1-37)

;; a. An infinite continued fraction is an expression of the form
;; f = N_1 / (D_1 + (N_2 / (D_2 + (N_3 / (... + (N_k / D_k))))))
;; As an example, one can show that the infinite continued fraction
;; expansion with the Ni and the Di all equal to 1 produces 1/ϕ, 
;; where ϕ is the golden ratio (described in Section 1.2.2). 
;; One way to approximate an infinite continued fraction is to truncate
;; the expansion after a given number of terms. Such a truncation—
;; a so-called k-term finite continued fraction—has the form
;; N_1 / (D_1 + (N_2 / (D_2 + (N_3 / (... + (N_k / D_k))))))
;; Suppose that n and d are procedures of one argument
;; (the term index i) that return the Ni and Di of the
;; terms of the continued fraction. Define a procedure
;; `cont-frac` such that evaluating `(cont-frac n d k)`
;; computes the value of the k-term finite continued fraction.
;; Check your procedure by approximating 1/ϕ using
;; (cont-frac (fn [i] 1.0)
;;            (fn [i] 1.0)
;;            10)
;; for successive values of k. How large must you make
;; `k` in order to get an approximation that is accurate to 4 decimal places?
(defn cont-frac-recursive [n d k]
  (letfn [(recur-frac [i]
            (if (> i k)
              0
              (/ (n i)
                 (+ (d i)
                    (recur-frac (inc i))))))]
    (recur-frac 1)))

(cont-frac-recursive (fn [i] 1.0)
           (fn [i] 1.0)
           7)

(defn cont-frac-iterative [n d k]
  (letfn [(iter [i result]
            (if (< i 1)
              result
              (iter (dec i) (/ (n i) (+ (d i) result)))))]
    (iter k 0)))

(cont-frac-iterative (fn [i] 1.0)
           (fn [i] 1.0)
           7)

(doseq [k (range 1 15)]
  (println "k =" k "value =" (cont-frac-iterative (fn [i] 1.0) (fn [i] 1.0) k)))



