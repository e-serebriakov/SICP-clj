(ns chapter-2.exercise-2-83
  (:require
   [chapter-2.chapter-2 :refer [apply-generic attach-tag contents denom
                                make-complex-from-real-imag make-rational
                                numer put-op]]))

(comment
  "Suppose you are designing a generic arithmetic system 
  for dealing with the tower of types shown in Figure 2.25: 
  integer, rational, real, complex. For each type (except complex),
  design a procedure that raises objects of that type one level in
  the tower. Show how to install a generic raise operation 
  that will work for each type (except complex).;")
(defn integer->rational [n]
  (make-rational (contents n) 1))

(defn rational->complex [r]
  (make-complex-from-real-imag (/ (double (numer r))
                                  (denom r))
                               0))

(defn real->complex [x]
  (make-complex-from-real-imag x 0))

(defn install-raise-package []
  ;; internal procedures
  (defn tag-integer [x]
    (attach-tag 'integer x))

  ;; interface to the rest of the system
  (put-op 'raise '(integer)
          (fn [x] (integer->rational x)))

  (put-op 'raise '(rational)
          (fn [r] (rational->complex r)))

  (put-op 'raise '(real)
          (fn [x] (real->complex x)))

  'done)

(defn raise [x]
  (apply-generic 'raise x))
