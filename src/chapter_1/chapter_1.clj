(ns chapter-1.chapter-1)

486

(+ 137 349)

(- 100 43)

(+ 2.7 10)

(+ 21 35 12 7)

(+ (* 3 5) (- 10 6))

(+ (* 3
      (+ (* 2 4)
         (+ 3 5)))
   (+ (- 10 7)
      6))

(def size 2)

size

(* 5 size)

(defn sqr [x] (* x x))

(sqr 4)

(sqr (sqr 8))

(defn sum-of-squares [x y] (+ (sqr x) (sqr y)))

(sum-of-squares 3 4)



