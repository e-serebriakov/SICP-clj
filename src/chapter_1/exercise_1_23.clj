(ns chapter-1.exercise-1-23)

;; The `smallest-divisor` procedure shown at
;; the start of this section does lots of needless testing: After it
;; checks to see if the number is divisible by 2 there is no point
;; in checking to see if it is divisible by any larger even numbers. 
;; This suggests that the values used for `test-divisor`
;; should not be 2, 3, 4, 5, 6,. . ., but rather 2, 3, 5, 7, 9,. . ..
;; To implement this change, define a procedure `next` that returns 3
;; if its input is equal to 2 and otherwise returns its input plus 2. 
;; Modify the `smallest-divisor` procedure to use
;; (next test-divisor) instead of (+ test-divisor 1).
;; With `timed-prime-test` incorporating this modified version
;; of `smallest-divisor`, run the test for each of the 12
;; primes found in Exercise 1.22. Since this modification halves
;; the number of test steps, you should expect it to run about
;; twice as fast. Is this expectation confirmed? If not, what is
;; the observed ratio of the speeds of the two algorithms, and
;; how do you explain the fact that it is diﬀerent from 2?
(defn divides? [a b] (= (mod b a) 0))

(defn find-divisor [next-fn n test-divisor]
            (cond (> (* test-divisor test-divisor) n) n
                  (divides? test-divisor n) test-divisor
                  :else (find-divisor next-fn n (next-fn test-divisor))))

(defn original-smallest-divisor [n] (find-divisor inc n 2))

(defn smallest-divisor [n] (letfn [(next [a] (if (= a 2) 3 (+ a 2)))]
                             (find-divisor next n 2)))

(defn prime? [n] (= n (smallest-divisor n)))

(defn original-prime? [n]
  (= n (original-smallest-divisor n)))

(defn time-test [prime-fn n]
  (let [start-time (System/nanoTime)]
    (prime-fn n)
    (- (System/nanoTime) start-time)))

(defn compare-speeds [n]
  (let [original-time (time-test original-prime? n)
        optimized-time (time-test prime? n)
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
