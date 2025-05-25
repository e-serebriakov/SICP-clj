(ns chapter-2.exercise-2-85
  (:require
   [chapter-2.chapter-2 :refer [apply-generic attach-tag contents get-op put-op]]
   [chapter-2.exercise-2-79 :refer [equ? install-lisp-number-package install-rational-package install-complex-package]]
   [chapter-2.exercise-2-83 :refer [raise install-raise-package]]))

(comment
  "This section mentioned a method for simplifying a data object by lowering it 
  in the tower of types as far as possible. Design a procedure `drop` that accomplishes this
  for the tower described in Exercise 2.83. The key is to decide, in some general way, 
  whether an object can be lowered. For example, the complex number 1.5 + 0i
  can be lowered as far as real, the complex number 1 + 0i can be lowered as far as integer,
  and the complex number 2 + 3i cannot be lowered at all. Here is a plan for determining 
  whether an object can be lowered: Begin by defining a generic operation project that pushes 
  an object down in the tower. For example, projecting a complex number would involve 
  throwing away the imaginary part. Then a number can be dropped if, when we project it and raise
  the result back to the type we started with, we end up with something equal to what we started with. 
  Show how to implement this idea in detail, by writing a drop procedure that drops an object
  as far as possible. You will need to design the various projection operations and install project
  as a generic operation in the system. You will also need to make use of a generic equality 
  predicate, such as described in Exercise 2.79. Finally, use drop to rewrite apply-generic
  from Exercise 2.84 so that it simplifies its answers.")

(+ 2 2)

;; Helper functions for complex numbers
(defn real-part [z]
  (first (contents z)))

(defn imag-part [z]
  (second (contents z)))

;; Helper functions for rational numbers
(defn numer [x]
  (first x))

(defn denom [x]
  (second x))

;; Projection operations for each type
(defn project-complex [z]
  (let [real (real-part z)
        imag (imag-part z)]
    (when (zero? imag)
      (attach-tag 'real real))))

(defn project-real [x]
  (let [int-val (int x)]
    (when (= x (double int-val))
      (attach-tag 'integer int-val))))

(defn project-rational [r]
  (let [n (numer (contents r))
        d (denom (contents r))]
    (when (= d 1)
      (attach-tag 'integer n))))

;; Install rectangular package for complex numbers
(defn install-rectangular-package []
  (letfn [(tag [x] (attach-tag 'rectangular x))]
    (put-op 'real-part '(rectangular) first)
    (put-op 'imag-part '(rectangular) second)
    (put-op 'magnitude '(rectangular) 
            (fn [z] (Math/sqrt (+ (* (first z) (first z))
                                 (* (second z) (second z))))))
    (put-op 'angle '(rectangular)
            (fn [z] (Math/atan2 (second z) (first z))))
    (put-op 'make-from-real-imag 'rectangular
            (fn [x y] (tag [x y])))
    (put-op 'make-from-mag-ang 'rectangular
            (fn [r a] (tag [(* r (Math/cos a))
                           (* r (Math/sin a))])))
    'done))

;; Install all necessary packages
(defn install-all-packages []
  ;; First install base packages
  (install-rectangular-package)
  (install-lisp-number-package)
  (install-rational-package)
  (install-complex-package)
  (install-raise-package)
  
  ;; Then install projection operations
  (put-op 'project '(complex) project-complex)
  (put-op 'project '(real) project-real)
  (put-op 'project '(rational) project-rational)
  
  'done)

;; Install packages
(install-all-packages)

;; Generic projection operation
(defn project [x]
  (apply-generic 'project x))

;; Main drop function
(defn drop [x]
  (let [projected (project x)]
    (if (and projected (equ? (raise projected) x))
      (drop projected)
      x)))

;; Modified apply-generic to use drop
(defn apply-generic-with-drop [op & args]
  (let [result (apply apply-generic op args)]
    (if (or (= op 'project) (= op 'raise))
      result
      (drop result))))

;; Define constructors
(def make-complex (get-op 'make-from-real-imag 'complex))
(def make-real (get-op 'make 'lisp-number))
(def make-rational (get-op 'make 'rational))
(def make-integer (get-op 'make 'lisp-number))

;; Test the operations
(let [c1 (make-complex 1.5 0)
      c2 (make-complex 1 0)
      c3 (make-complex 2 3)]
  (println "Testing operations:")
  (println "c1 =" c1)
  (println "project(c1) =" (project c1))
  (println "raise(project(c1)) =" (raise (project c1)))
  (println "equ?(c1, raise(project(c1))) =" (equ? c1 (raise (project c1))))
  (println "drop(c1) =" (drop c1))
  (println "\ndrop(c2) =" (drop c2))
  (println "\ndrop(c3) =" (drop c3)))
