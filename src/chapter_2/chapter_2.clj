(ns chapter-2.chapter-2 
  (:require
   [chapter-1.chapter-1 :refer [gcd]]))

(defn make-rat [n d] [n d])

(defn make-rat [n d] 
  (let [g (gcd n d)]
    [(/ n g) (/ d g)]))

(def numer first)

(def denom last)

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
           
(defn mult-rat [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y))
            (* (numer y) (denom x))))

(defn equal-rat? [x y]
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))

(defn print-rat [x]
  (print "")
  (print (numer x))
  (print "/")
  (print (denom x)))

(def x [1 2])
(first x)
(last x)
(rest x)
(subvec x 1)
(def y [3 4])
(def pair-of-pairs [x y])
(first (first pair-of-pairs))

(def one-half (make-rat 1 2))
(def one-third (make-rat 1 3))
(print-rat one-half)
(print-rat (add-rat one-half one-third))
(print-rat (add-rat one-third one-third))