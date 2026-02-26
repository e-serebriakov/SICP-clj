(ns chapter-2.exercise-2-77 
  (:require
   [chapter-2.chapter-2 :as ch2]))

;; Louis Reasoner tries to evaluate the expression (magnitude z)
;; where z is the object shown in Figure 2.24. To his surprise, instead of
;; the answer 5 he gets an error message from apply-generic,
;; saying there is no method for the operation magnitude on the types (complex).
;; He shows this interaction to Alyssa P. Hacker, who says “The
;; problem is that the complex-number selectors were never
;; defined for complex numbers, just for polar and rectangular numbers.
;; All you have to do to make this work is add the following to the complex package:”

(ch2/put-op 'real-part '(complex) ch2/real-part)
(ch2/put-op 'imag-part '(complex) ch2/imag-part)
(ch2/put-op 'magnitude '(complex) ch2/magnitude)
(ch2/put-op 'angle '(complex) ch2/angle)

;; Describe in detail why this works. As an example, trace
;; through all the procedures called in evaluating the expression (magnitude z) 
;; where z is the object shown in Figure 2.24. 
;; In particular, how many times is apply-generic invoked? 
;; What procedure is dispatched to in each case?

(def z (ch2/make-complex-from-real-imag 4 3))
z

;; when we call (maginute z) without adding operation to the table
;; we get an error since z has a type `(complex (rectangular (4 3)))`
;; and not `(rectangular (4 3))`
(defn magnitude [z]
  (ch2/apply-generic 'magnitude z))

(magnitude z)
;; (magnitude '(complex '(rectangular (4 3)))) 
;; (apply-generic 'magnitude '(complex '(rectangular (4 3)))) 
;; (magnitude '(rectangular (4 3)))                 Calls magnitude again 
;; (apply-generic 'magnitude '(rectangular (4 3)))
;; (magnitude-rectangular (contents '(rectangular (4 3)))) 
;; (magnitude-rectangular (4 3))   
;; 5.0
