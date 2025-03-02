(ns chapter-2.exercise-2-18)

;; Define a procedure reverse that takes a list
;; as argument and returns a list of the same elements in reverse order:
(defn reverse-list [l]
  (reduce conj () l))

(reverse-list '(1 2 3))

(def reverse-list reverse)
(reverse-list '(1 2 3))

(defn reverse-list [l]
  (if (empty? l)
    l
    (concat (reverse-list (rest l))
            (list (first l)))))
(reverse-list '(1 2 3))

(defn reverse-list [l]
  (loop [remaining l
         result ()]
    (if (empty? remaining)
      result
      (recur (rest remaining)
             (cons (first remaining) result)))))
            
(reverse-list '(1 2 3))