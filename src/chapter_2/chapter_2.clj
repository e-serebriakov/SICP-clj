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

(defn cons1 [x y] [x y])
(cons1 1
       (cons1 2
             (cons1 3
                    (cons1 4 nil))))

(def l (list 1 2 3 4))
(first l)
(rest l)
(nth l 2)
(conj l 5)
(concat l l)
(reduce conj l l)

(defn scale-tree [root factor]
  (cond 
    (nil? root) nil
    (number? root) (* root factor)
    :else (list (scale-tree (first root) factor)
                (scale-tree (last root) factor))))

(scale-tree (list (list 1 2) (list 3 (list 4 5))) 10)

(defn scale-tree [root factor]
  (map (fn [node]
         (if (number? node)
           (* node factor)
           (scale-tree node factor)))
       root))
       
(scale-tree (list (list 1 2) (list 3 (list 4 5))) 10)

(defn filter-1 [predicate sequence]
  (when (seq sequence)
    (if (predicate (first sequence))
      (cons (first sequence) (filter-1 predicate (rest sequence)))
      (filter-1 predicate (rest sequence)))))

(filter-1 odd? (list 1 2 3 4 5))

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence) 
        (accumulate op initial (rest sequence)))))

(accumulate + 0 (list 1 2 3 4 5))

(defn enumerate-interval [low high]
  (if (> low high)
    nil
    (cons low (enumerate-interval (inc low) high))))
    
(enumerate-interval 1 5)

(defn enumerate-tree [tree]
  (cond 
    (not (sequential? tree)) (list tree)
    (empty? tree) nil
    :else (concat (enumerate-tree (first tree))
                  (enumerate-tree (rest tree)))))

(defn enumerate-tree [tree]
  (if (sequential? tree)
    (mapcat enumerate-tree (seq tree))
    (list tree)))

(enumerate-tree '(1 (2 3) 4))
(enumerate-tree '((1 2) (3 4)))

(defn sum-odd-squares [tree]
  (accumulate + 0 (map #(* % %) (filter odd? (enumerate-tree tree)))))

(sum-odd-squares '(1 (2 3) 4))

(defn fib [n]
  (if (< n 2)
    n
    (+ (fib (- n 1))
       (fib (- n 2)))))

(defn even-fibs [n]
  (filter even? (map fib (enumerate-interval 0 n))))

(even-fibs 4)