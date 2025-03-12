(ns chapter-2.chapter-2 
  (:require
   [chapter-1.chapter-1 :refer [gcd]]
   [chapter-1.exercise-1-33 :refer [prime?]]
   [chapter-2.exercise-2-46 :refer [add-vect make-vect scale-vect sub-vect
                                    xcor-vect ycor-vect]]
   [chapter-2.exercise-2-47 :refer [edge-1-frame edge-2-frame make-frame
                                    origin-frame]]))

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

(defn transform-painter [painter origin corner1 corner2]
  (fn [frame]
    (let [m (frame-coord-map frame)
          new-origin (m origin)]
      (painter (make-frame
                new-origin
                (sub-vect (m corner1) new-origin)
                (sub-vect (m corner2) new-origin))))))

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


;; 2.3 Symbolic Data
(def a 1)
a
(+ 1 a)
(first '(a b c))
(= 'a 'a) ; => true

(defn memq
  "If the symbol is not contained in the
   list (i.e., is not eq? to any item in the list), then memq returns false. Other-
   wise, it returns the sublist of the list beginning with the first occurrence
   of the symbol:
  
   Examples:
   (memq 'apple '(pear apple prune)) => (apple prune)
   (memq 'apple '(pear banana prune)) => false"
  [item x]
  (cond (empty? x) false
        (= item (first x)) x
        :else (memq item (rest x))))

(memq 'apple '(pear banana prune))
(memq 'apple '(pear apple prune))

(defn variable? [x]
  (symbol? x))
 
(defn same-variable? [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(defn make-sum-v1 [v1 v2]
  (list '+ v1 v2))

(defn =number? [exp num]
  (and (number? exp) (= exp num)))

(defn make-sum [a1 a2]
  (cond (=number? a1 0) a2
        (=number? a2 0) a1
        (and (number? a1) (number? a2)) (+ a1 a2)
        :else (list '+ a1 a2)))

(defn make-product-v1 [v1 v2]
  (list '* v1 v2))

(defn make-product [m1 m2]
  (cond (or (=number? m1 0) (=number? m2 0)) 0
        (=number? m1 1) m2
        (=number? m2 1) m1
        (and (number? m1) (number? m2)) (* m1 m2)
        :else (list '* m1 m2)))
 
(defn sum? [x]
  (and (seq? x) (= (first x) '+)))

(defn addend [x]
  (second x))

(defn augend [x]
  (last x))

(defn product? [x]
  (and (seq? x) (= (first x) '*)))

(defn multiplier [p]
  (second p))

(defn multiplicand [p]
  (last p))

(defn deriv [exp var]
  (cond (number? exp) 0
        (variable? exp) (if (same-variable? exp var) 1 0)
        (sum? exp) (make-sum (deriv (addend exp) var)
                             (deriv (augend exp) var))
        (product? exp) (make-sum
                        (make-product (multiplier exp)
                                      (deriv (multiplicand exp) var))
                        (make-product (deriv (multiplier exp) var)
                                      (multiplicand exp)))
        :else (throw (ex-info "unknown expression type: DERIV" {:expression exp}))))

(deriv '(+ x 3) 'x)
(deriv '(* x y) 'x)
(deriv '(* (* x y) (+ x 3)) 'x)

;; Example: Representing Sets
(defn elements-of-set? [x set]
  (cond (empty? set) false
        (= (first set) x) true
        :else (elements-of-set? x (rest set))))

(elements-of-set? 4 '(1 2 3 4))

(defn adjoin-set [x set]
  (if (elements-of-set? x set)
    set
    (cons x set)))
    
(defn intersection-set [s1 s2]
  (cond (or (empty? s1) (empty? s2)) '()
        (elements-of-set? (first s1) s2) (cons (first s1) (intersection-set (rest s1) s2))
        :else (intersection-set (rest s1) s2)))
 
(intersection-set '(1 2 3) '(2 3 4))

(defn elements-of-ordered-set? [x set]
  (cond (empty? set) false
        (= (first set) x) true
        (> (first set) x) false
        :else (elements-of-ordered-set? x (rest set))))

(elements-of-ordered-set? 3 '(1 2 3))
(elements-of-ordered-set? 4 '(1 2 5 6))


(defn intersection-ordered-set [s1 s2]
  (if (or (empty? s1) (empty? s2))
    '()
    (let [[x1 & xs1] s1
          [x2 & xs2] s2]
      (cond (= x1 x2) (cons x1 (intersection-ordered-set xs1 xs2))
            (< x1 x2) (intersection-ordered-set xs1 s2)
            (< x2 x1) (intersection-ordered-set s1 xs2)))))

(intersection-ordered-set '(1 2 3) '(3 4 5))

;; Sets as binary trees
(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (last tree))
(defn make-tree [entry left right]
  (list entry left right))

(defn elements-of-tree-set? [x set]
  (cond (empty? set) false
        (= x (entry set)) true
        (< x (entry set)) (elements-of-tree-set? x (left-branch set))
        (> x (entry set)) (elements-of-tree-set? x (right-branch set))))

(defn adjoin-tree-set [x set]
  (cond (empty? set) (make-tree x '() '())
        (= x (entry set)) set
        (< x (entry set)) (make-tree (entry set)
                                     (adjoin-tree-set x (left-branch set))
                                     (right-branch set))
        (> x (entry set)) (make-tree (entry set)
                                     (left-branch set)
                                     (adjoin-tree-set x (right-branch set)))))