(ns chapter-2.exercise-2-52 
  (:require
   [chapter-2.chapter-2 :refer [below beside flip-vert square-of-four]]
   [chapter-2.exercise-2-45 :refer [right-split up-split]]
   [chapter-2.exrcise-2-50 :refer [flip-horiz rotate180]]))

;; Make changes to the square limit of wave
;; shown in Figure 2.9 by working at each of the levels described above. 
;; In particular:
;; a. Add some segments to the primitive wave painter of
;;    Exercise 2.49 (to add a smile, for example).
;; I don't have it

;; b. Change the pattern constructed by corner-split (for
;;    example, by using only one copy of the up-split and
;;    right-split images instead of two).
(defn corner-split-original [painter n]
  (if (zero? n)
    painter
    (let [up (up-split painter (dec n))
          right (right-split painter (dec n))
          top-left (beside up up)
          bottom-right (below right right)
          corner (corner-split-original painter (dec n))]
      (beside (below painter top-left)
              (below bottom-right corner)))))

(defn corner-split [painter n]
  (if (zero? n)
    painter
    (let [up (up-split painter (dec n))
          right (right-split painter (dec n))
          top-left (beside up painter)
          bottom-right (below right painter)
          corner (corner-split painter (dec n))]
      (beside (below painter top-left)
              (below bottom-right corner)))))

;; c. Modify the version of square-limit that uses square-of-four
;;    so as to assemble the corners in a diﬀerent
;;    pattern. (For example, you might make the big Mr.
;;    Rogers look outward from each corner of the square.)
(defn square-limit-original [painter n]
  (let [combine4 (square-of-four flip-horiz identity
                                 rotate180 flip-vert)]
    (combine4 (corner-split painter n))))

(defn square-limit [painter n]
  (let [combine4 (square-of-four identity flip-horiz
                                flip-vert rotate180)]
    (combine4 (corner-split painter n))))