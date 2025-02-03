(ns chapter-1.exercise-1-12)

; The following paern of numbers is called Pascal’s triangle.
;     1
;    1 1
;   1 2 1
;  1 3 3 1
; 1 4 6 4 1
; The numbers at the edge of the triangle are all 1, and each
; number inside the triangle is the sum of the two numbers above it.
; Write a procedure that computes elements of
; Pascal’s triangle by means of a recursive process.
(defn pascal [row col]
  (cond
    (< col 0) 0
    (> col row) 0
    (or (= col 0) (= col row)) 1
    :else (+ (pascal (dec row) (dec col))
             (pascal (dec row) col))))
 
(pascal 4 2)