(ns chapter-2.exercise-2-37 
  (:require
   [chapter-2.chapter-2 :refer [accumulate]]
   [chapter-2.exercise-2-36 :refer [accumulate-n]]))

;; Suppose we represent vectors v = (v_i) as
;; sequences of numbers, and matrices m = (m_ij) as sequences
;; of vectors (the rows of the matrix). For example, the matrix
;; ┌ 1 2 3 4 ┐
;; │ 4 5 6 6 │
;; └ 6 7 8 9 ┘
;; is represented as the sequence ((1 2 3 4) (4 5 6 6) (6 7 8 9)). 
;; With this representation, we can use sequence
;; operations to concisely express the basic matrix and vector
;; operations. These operations (which are described in any
;; book on matrix algebra) are the following:
;; (dot-product v w) returns the sum Σ_i(v_i*w_i) ;
;; (matrix-*-vector m v) returns the vector t, where t_i = Σ_j(m_ij*v_j);
;; (matrix-*-matrix m n) returns the matrix p, where p_ij = Σ_k(m_ik*n_kj);
;; (transpose m) returns the matrix n, where n_ij = m_ji.
;; We can define the dot product as
;; (define (dot-product v w)
;;   (accumulate + 0 (map * v w)))
;; Fill in the missing expressions in the following procedures
;; for computing the other matrix operations. (The procedure
;; accumulate-n is defined in Exercise 2.36.)
;; (define (matrix-*-vector m v)
;;   (map ⟨??⟩ m))
;; (define (transpose mat)
;;   (accumulate-n ⟨??⟩ ⟨??⟩ mat))
;; (define (matrix-*-matrix m n)
;;   (let ((cols (transpose n)))
;;     (map ⟨??⟩ m)))

(defn matrix-*-vector [m v]
  (map (fn [row] (accumulate + 0 (map * row v))) m))

(def m (list (list 1 2)
             (list 4 5)))
(def v (list 3 4))

(matrix-*-vector m v) ; => (((1 * 3) + (2 * 4)) ((4 * 3) + (5 * 4))) => (11 32)

;; ┌ 1 2 3 4 ┐    ┌ 1 4 6 ┐
;; │ 4 5 6 6 │ -> │ 2 5 7 │
;; └ 6 7 8 9 ┘    │ 3 6 8 │
;;                └ 4 6 9 ┘
(defn transpose [m]
  (accumulate-n cons '() m))

(def m-1 (list (list 1 2 3 4)
               (list 4 5 6 6)
               (list 6 7 8 9)))
(transpose m-1)

(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map (fn [row] (matrix-*-vector cols row)) m)))


(def matrix-a (list (list 1 2)
                    (list 3 4)))
(def matrix-b (list (list 5 6)
                    (list 7 8)))

(matrix-*-matrix matrix-a matrix-b)
;; ((1*5 + 2*7) (1*6 + 2*8))   ┌ 19 22 ┐
;; ((3*5 + 4*7) (3*6 + 4*8))   └ 43 50 ┘