(ns chapter-2.exercise-2-79
  (:require
    [chapter-2.chapter-2 :refer 
      [put-op get-op attach-tag apply-generic real-part imag-part magnitude angle]]
    [chapter-1.chapter-1 :refer [gcd]]))

(comment
  "Define a generic equality predicate equ? that
  tests the equality of two numbers, and install it in the generic
  arithmetic package. This operation should work for ordinary numbers,
  rational numbers, and complex numbers."
)
(defn equ? [x y] (apply-generic 'equ? x y))

(defn install-lisp-number-package []
  (letfn [(tag [x] (attach-tag 'lisp-number x))]
    (put-op 'add '(lisp-number lisp-number) (fn [x y] (tag (+ x y))))
    (put-op 'sub '(lisp-number lisp-number) (fn [x y] (tag (- x y))))
    (put-op 'mul '(lisp-number lisp-number) (fn [x y] (tag (* x y))))
    (put-op 'div '(lisp-number lisp-number) (fn [x y] (tag (/ x y))))
    (put-op 'equ? '(lisp-number lisp-number) (fn [x y] (= x y)))
    (put-op 'make 'lisp-number (fn [x] (tag x)))
    'done))

(comment
  (install-lisp-number-package)

  (def make (get-op 'make 'lisp-number))

  (equ? (make 3) (make 4)) ;; Returns false
  (equ? (make 3) (make 3)) ;; Returns true
)

(defn install-rational-package []
  (letfn [(numer [x] (first x))
          (denom [x] (last x))
          (make-rat [n d]
            (let [g (gcd n d)]
              (list (/ n g) (/ d g))))
          (add-rat [x y]
            (make-rat (+ (* (numer x) (denom y))
                         (* (numer y) (denom x)))
                      (* (denom x) (denom y))))
          (sub-rat [x y]
            (make-rat (- (* (numer x) (denom y))
                         (* (numer y) (denom x)))
                      (* (denom x) (denom y))))

          (mult-rat [x y]
            (make-rat (* (numer x) (numer y))
                      (* (denom x) (denom y))))

          (div-rat [x y]
            (make-rat (* (numer x) (denom y))
                      (* (numer y) (denom x))))
          (equ? [x y]
            (= (* (numer x) (denom y))
               (* (numer y) (denom x))))
          (tag [x] (attach-tag 'rational x))]

    (put-op 'add '(rational 'rational) (fn [x y] (tag (add-rat x y))))
    (put-op 'sub '(rational 'rational) (fn [x y] (tag (sub-rat x y))))
    (put-op 'mul '(rational 'rational) (fn [x y] (tag (mult-rat x y))))
    (put-op 'div '(rational 'rational) (fn [x y] (tag (div-rat x y))))
    (put-op 'equ? '(rational 'rational) (fn [x y] (equ? x y)))
    (put-op 'make 'rational (fn [n d] (tag (make-rat n d))))
    'done))

(defn install-complex-package []
  ;; imported procedures from rectangular and polar packages
  (letfn [(make-from-real-imag [x y]
            ((ch2/get-op 'make-from-real-imag 'rectangular) x y))
          (make-from-mag-ang [r a]
            ((ch2/get-op 'make-from-mag-ang 'polar) r a))
          ;; internal procedures
          (add-complex [z1 z2]
            (make-from-real-imag (+ (real-part z1) (real-part z2))
                                (+ (imag-part z1) (imag-part z2))))
          (sub-complex [z1 z2]
            (make-from-real-imag (- (real-part z1) (real-part z2))
                                (- (imag-part z1) (imag-part z2))))
          (mul-complex [z1 z2]
            (make-from-mag-ang (* (magnitude z1) (magnitude z2))
                              (+ (angle z1) (angle z2))))
          (div-complex [z1 z2]
            (make-from-mag-ang (/ (magnitude z1) (magnitude z2))
                              (- (angle z1) (angle z2))))

          (equ? [x y]
            (and (= (ch2/real-part x) (ch2/real-part y))
                 (= (ch2/imag-part x) (ch2/imag-part y))))
          
          ;; interface helper
          (tag [z] (ch2/attach-tag 'complex z))]
    
    ;; interface to rest of the system
    (put-op 'add '(complex complex)
            (fn [z1 z2] (tag (add-complex z1 z2))))
    (put-op 'sub '(complex complex)
            (fn [z1 z2] (tag (sub-complex z1 z2))))
    (put-op 'mul '(complex complex)
            (fn [z1 z2] (tag (mul-complex z1 z2))))
    (put-op 'div '(complex complex)
            (fn [z1 z2] (tag (div-complex z1 z2))))
    (put-op 'make-from-real-imag 'complex
            (fn [x y] (tag (make-from-real-imag x y))))
    (put-op 'make-from-mag-ang 'complex
            (fn [r a] (tag (make-from-mag-ang r a))))

    (put-op 'equ? '(complex complex) equ?)
    
    'done))
