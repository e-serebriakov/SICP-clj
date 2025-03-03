(ns chapter-2.exercise-2-53 
  (:require
   [chapter-2.chapter-2 :refer [memq]]))

;; What would the interpreter print in response
;; to evaluating each of the following expressions?
(list 'a 'b 'c) ; => a b c
(list (list 'george)) ; => ((george))
(rest '((x1 x2) (y1 y2))) ; => ((y1 y2))
(second '((x1 x2) (y1 y2))) ; => (y1 y2)
(seq? (first '(a short list))) ; => false
(memq 'red '((red shoes) (blue socks))) ; => false
(memq 'red '(red shoes blue socks)) ; => (red shoes blue socks)