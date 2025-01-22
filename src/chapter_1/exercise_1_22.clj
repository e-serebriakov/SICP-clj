(ns chapter-1.exercise-1-22)

;; Most Lisp implementations include a primitive called runtime
;; that returns an integer that specifies
;; the amount of time the system has been running (measured,
;; for example, in microseconds). The following timed-prime-test procedure,
;; when called with an integer n, prints n and checks to see if n is prime. 
;; If n is prime, the procedure prints three asterisks
;; followed by the amount of time used in performing the test.
(defn report-prime [elapsed-time]
  (print " *** Prime number found! ***")
  (printf "\nTime elapsed: %d nanoseconds\n" elapsed-time))

(defn smallest-divisor [n] (letfn [(divides? [a b] (= (mod b a) 0))
                                   (find-divisor [n test-divisor] (cond (> (* test-divisor test-divisor) n) n
                                                                        (divides? test-divisor n) test-divisor
                                                                        :else (find-divisor n (inc test-divisor))))]
                             (find-divisor n 2)))

(defn prime? [n] (= n (smallest-divisor n)))

(defn start-prime-test [n start-time]
  (when (prime? n)
    (report-prime (- (System/nanoTime) start-time))))

(defn timed-prime-test [n]
  (println "\nTesting number:" n)
  (start-prime-test n (System/nanoTime)))

(timed-prime-test 1999)

;; Using this procedure, write a procedure search-for-primes
;; that checks the primality of consecutive odd integers in a
;; specified range. Use your procedure to find the three smallest primes 
;; larger than 1000; larger than 10,000; larger than
;; 100,000; larger than 1,000,000. Note the time needed to test
;; each prime. Since the testing algorithm has order of growth
;; of Θ(√n), you should expect that testing for primes around
;; 10,000 should take about √10 times as long as testing for
;; primes around 1000. Do your timing data bear this out?
;; How well do the data for 100,000 and 1,000,000 support the
;; Θ(√n) prediction? Is your result compatible with the notion
;; that programs on your machine run in time proportional to
;; the number of steps required for the computation?
(defn search-for-primes [number-from count]
   (let [start (if (even? number-from)
                 (inc number-from)
                 number-from)]
     (loop [current start
            remaining count]
       (when (> remaining 0)
         (timed-prime-test current)
         (recur (+ current 2) (if (prime? current)
                                (dec remaining)
                                remaining))))))

(search-for-primes 1000 3)
(search-for-primes 100000 3)
(search-for-primes 1000000 3)