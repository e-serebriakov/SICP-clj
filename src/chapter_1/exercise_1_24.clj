(ns chapter-1.exercise-1-24)

;; Modify the timed-prime-test procedure of Exercise 1.22 to use 
;; fast-prime? (the Fermat method), and test each of the 12 primes you found 
;; in that exercise. Since the Fermat test has Θ(log n) growth, how 
;; would you expect the time to test primes near 1,000,000 to compare with the
;; time needed to test primes near 1000? Do your data bear this out? 
;; Can you explain any discrepancy you find?
(defn fermat-test [n]
  (letfn [(exp-mod [base exp m]
            (cond (= exp 0) 1
                  (even? exp) (mod (exp-mod base (/ exp 2) m) m)
                  :else (mod (* base (exp-mod base (dec exp) m)) m)))
          (try-it [a]
            (= (exp-mod a n n) a))]
    (try-it (inc (rand-int (dec n))))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (dec times))
        :else false))

(defn fermit-prime? [n] (fast-prime? n 7))

(defn divides? [a b] (= (mod b a) 0))

(defn find-divisor [next-fn n test-divisor]
            (cond (> (* test-divisor test-divisor) n) n
                  (divides? test-divisor n) test-divisor
                  :else (find-divisor next-fn n (next-fn test-divisor))))

(defn smallest-divisor [n] (find-divisor inc n 2))

(defn original-prime? [n]
  (= n (smallest-divisor n)))
                                 
(defn time-test [prime-fn n]
  (let [start-time (System/nanoTime)]
    (prime-fn n)
    (- (System/nanoTime) start-time)))

(defn compare-speeds [n]
  (let [original-time (time-test original-prime? n)
        optimized-time (time-test fermit-prime? n)
        ratio (double (/ original-time optimized-time))]
    (println "\nTesting number:" n)
    (printf "Original version: %d ns\n" original-time)
    (printf "Optimized version: %d ns\n" optimized-time)
    (printf "Speed ratio: %.2fx\n" ratio)
    ratio))

(defn test-sequence []
  (let [numbers [1009 1013 1019 10007 10009 10037 100003 100019 100043 1000003 1000033 1000037]
        ratios (map compare-speeds numbers)]
    (printf "\nAverage speed ratio: %.2fx\n" (double (/ (reduce + ratios) (count ratios))))))

;; Run the comparison tests
(test-sequence)

;; Original method (trial division): Θ(√n)
;; Fermat test: Θ(log n)
;; For numbers near 1,000 vs 1,000,000:
;; For trial division (√n): √1,000,000 ≈ 31.6 times slower than √1,000
;; For Fermat test (log n): log(1,000,000) is only about 2 times slower than log(1,000)