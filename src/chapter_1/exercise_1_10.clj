(ns chapter-1.exercise-1-10)

; The following procedure computes a mathematical function called Ackermann’s function.
(defn A [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (A (- x 1) (A x (- y 1)))))

; What are the values of the following expressions?
(A 1 10)
; (A (- 1 1) (A 1 (- 10 1)))
; (A 0 (A 1 9))
; (A 0 (A (- 1 1) (A 1 (- 9 1))))
; (A 0 (A 0 (A 1 8)))
; (A 0 (A 0 (A (- 1 1) (A 1 (- 8 1)))))
; (A 0 (A 0 (A 0 (A 1 7))))
; (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 7 1))))))
; (A 0 (A 0 (A 0 (A 0 (A 1 6)))))
; (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 6 1)))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 5))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 5 1))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 4)))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 4 1)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 3))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 3 1))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 2)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 2 1)))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 8)))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 16))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 32)))))
; (A 0 (A 0 (A 0 (A 0 64))))
; (A 0 (A 0 (A 0 128)))
; (A 0 (A 0 256))
; (A 0 512)
; 1024

(A 2 4)
; (A (- 2 1) (A 2 (- 4 1)))
; (A 1 (A 2 3))
; (A 1 (A (- 2 1) (A 2 (- 3 1))))
; (A 1 (A 1 (A 2 2)))
; (A 1 (A 1 (A (- 2 1) (A 2 (- 2 1)))))
; (A 1 (A 1 (A 1 (A 2 1))))
; (A 1 (A 1 (A 1 2)))
; (A 1 (A 1 (A (- 1 1) (A 1 (- 2 1)))))
; (A 1 (A 1 (A 0 (A 1 1))))
; (A 1 (A 1 (A 0 2)))
; (A 1 (A 1 4))
; (A 1 (A (- 1 1) (A 1 (- 4 1))))
; (A 1 (A 0 (A 1 3)))
; (A 1 (A 0 (A (- 1 1) (A 1 (- 3 1)))))
; (A 1 (A 0 (A 0 (A 1 2))))
; (A 1 (A 0 (A 0 (A (- 1 1) (A 1 (- 2 1))))))
; (A 1 (A 0 (A 0 (A 0 (A 1 1)))))
; (A 1 (A 0 (A 0 4)))
; (A 1 16)
; (A (- 1 1) (A 1 (- 16 1)))
; (A 0 (A 1 15))
; (A 0 (A (- 1 1) (A 1 (- 15 1))))
; (A 0 (A 0 (A 1 14)))
; (A 0 (A 0 (A (- 1 1) (A 1 (- 14 1)))))
; (A 0 (A 0 (A 0 (A 1 13))))
; (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 13 1))))))
; (A 0 (A 0 (A 0 (A 0 (A 1 12)))))
; (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 12 1)))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 11))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 11 1))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 10)))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 10 1)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 9))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 9 1))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 8)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 8 1)))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 7))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 7 1))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 6)))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 6 1)))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 5))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 5 1))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 4)))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 4 1)))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 3))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 3 1))))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 2)))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 2 1)))))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 8)))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 16))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 32)))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 64))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 128)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 256))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 512)))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 1024))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 2048)))))
; (A 0 (A 0 (A 0 (A 0 4096))))
; (A 0 (A 0 (A 0 8192)))
; (A 0 (A 0 16384))
; (A 0 32768)
; 65536

(A 3 3) ; 65536
; (A (- 3 1) (A 3 (- 3 1))) 
; (A 2 (A 3 2)) 
; (A 2 (A (- 3 1) (A 3 (- 2 1)))) 
; (A 2 (A 2 (A 3 1))) 
; (A 2 (A 2 2)) 
; (A 2 (A (- 2 1) (A 2 (- 2 1)))) 
; (A 2 (A 1 (A 2 1))) 
; (A 2 (A 1 2)) 
; (A 2 (A (- 1 1) (A 1 (- 2 1)))) 
; (A 2 (A 0 (A 1 1))) 
; (A 2 4) 
; (A (- 2 1) (A 2 (- 4 1)))
; (A 1 (A 2 3))
; (A 1 (A (- 2 1) (A 2 (- 3 1))))
; (A 1 (A 1 (A 2 2)))
; (A 1 (A 1 (A (- 2 1) (A 2 (- 2 1)))))
; (A 1 (A 1 (A 1 (A 2 1))))
; (A 1 (A 1 (A 1 2)))
; (A 1 (A 1 (A (- 1 1) (A 1 (- 2 1)))))
; (A 1 (A 1 (A 0 (A 1 1))))
; (A 1 (A 1 (A 0 2)))
; (A 1 (A 1 4))
; (A 1 (A (- 1 1) (A 1 (- 4 1))))
; (A 1 (A 0 (A 1 3)))
; (A 1 (A 0 (A (- 1 1) (A 1 (- 3 1)))))
; (A 1 (A 0 (A 0 (A 1 2))))
; (A 1 (A 0 (A 0 (A (- 1 1) (A 1 (- 2 1))))))
; (A 1 (A 0 (A 0 (A 0 (A 1 1)))))
; (A 1 (A 0 (A 0 4)))
; (A 1 16)
; (A (- 1 1) (A 1 (- 16 1)))
; (A 0 (A 1 15))
; (A 0 (A (- 1 1) (A 1 (- 15 1))))
; (A 0 (A 0 (A 1 14)))
; (A 0 (A 0 (A (- 1 1) (A 1 (- 14 1)))))
; (A 0 (A 0 (A 0 (A 1 13))))
; (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 13 1))))))
; (A 0 (A 0 (A 0 (A 0 (A 1 12)))))
; (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 12 1)))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 11))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 11 1))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 10)))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 10 1)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 9))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 9 1))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 8)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 8 1)))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 7))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 7 1))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 6)))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 6 1)))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 5))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 5 1))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 4)))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 4 1)))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 3))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 3 1))))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 2)))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A (- 1 1) (A 1 (- 2 1)))))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4))))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 8)))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 16))))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 32)))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 64))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 128)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 256))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 512)))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 1024))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 2048)))))
; (A 0 (A 0 (A 0 (A 0 4096))))
; (A 0 (A 0 (A 0 8192)))
; (A 0 (A 0 16384))
; (A 0 32768)

; Consider the following procedures, where A is the procedure defined above:
(defn f [n] (A 0 n)) ; calculates 2 * n
(defn g [n] (A 1 n)) ; calculates 2 ^ n
(defn h [n] (A 2 n)) ; calculates 2 for n = 0 or 2 ^ h (n - 1) -> 2 ^ (2 ^ (2 ^ (2 ^ ....)))
(defn k [n] (* 5 n n)) ; calculates 5 * n ^ 2