(ns chapter-2.chapter-2 
  (:require
   [chapter-1.chapter-1 :refer [gcd]]
   [chapter-1.exercise-1-33 :refer [prime?]]
   [chapter-2.exercise-2-46 :refer [add-vect make-vect scale-vect xcor-vect
                                    ycor-vect]]
   [chapter-2.exercise-2-47 :refer [edge-1-frame edge-2-frame origin-frame]]
   [chapter-2.exrcise-2-50 :refer [transform-painter]]))

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


;; Nested Mappings
(accumulate concat nil (map (fn [i]
                              (map (fn [j] (list i j))
                                   (enumerate-interval 1 (dec i))))
                            (enumerate-interval 1 10)))

(defn flatmap [proc seq]
  (accumulate concat nil (map proc seq)))

(defn prime-sum? [pair]
  (prime? (+ (first pair) (last pair))))

(defn make-pair-sum [pair]
  (list (first pair) (last pair) (+ (first pair) (last pair))))

(defn prime-sum-pairs [n]
  (map make-pair-sum
       (filter prime-sum? (flatmap
                           (fn [i]
                             (map (fn [j] (list i j))
                                  (enumerate-interval 1 (dec i))))
                           (enumerate-interval 1 n)))))
                           
(prime-sum-pairs 10)

(defn permutations [s]
  (if (empty? s) (list nil)
      (flatmap (fn [x]
                 (map (fn [p] (cons x p))
                      (permutations (filter #(not= x %) s))))
               s)))

(permutations (list 1 2 3))

;; 2.2.4 Example: A Picture Language
(defn test-painter 
  "Creates a simple test painter that just returns its label"
  [label]
  label)

(defn beside
  "Combines two painters side by side"
  [p1 p2]
  (str "(" p1 " beside " p2 ")"))

(defn below
  "Combines two painters one below another"
  [p1 p2]
  (str "(" p1 " below " p2 ")"))

(defn frame-coord-map [frame]
  (fn [v]
    (add-vect
     (origin-frame frame)
     (add-vect (scale-vect (xcor-vect v) (edge-1-frame frame))
               (scale-vect (ycor-vect v) (edge-2-frame frame))))))

(defn beside [painter1 painter2]
  (let [split-point (make-vect 0.5 0.0)
        paint-left (transform-painter painter1
                                      (make-vect 0.0 0.0)
                                      split-point
                                      (make-vect 0.0 1.0))
        paint-right (transform-painter painter2
                                       split-point
                                        (make-vect 1.0 0.0)
                                        (make-vect 0.5 1.0))]
    (fn [frame] 
      (paint-left frame)
      (paint-right frame))))

(defn flip-vert [painter]
  (transform-painter painter
                     (make-vect 0.0 1.0)    ; new origin point
                     (make-vect 1.0 1.0)    ; new end point for edge1
                     (make-vect 0.0 0.0)))  ; new end point for edge2

(defn square-of-four [tl tr bl br]
  (fn [painter]
    (let [top (beside (tl painter) (tr painter))
          bottom (beside (bl painter) (br painter))]
      (below bottom top))))