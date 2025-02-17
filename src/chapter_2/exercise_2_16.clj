(ns chapter-2.exercise-2-16)

;; Explain, in general, why equivalent algebraic 
;; expressions may lead to diﬀerent answers. Can you
;; devise an interval-arithmetic package that does not have
;; this shortcoming, or is this task impossible? (Warning: This
;; problem is very diﬃcult.)a

;; The reason why equivalent algebraic expressions may lead to different answers
;; in interval arithmetic is due to the "dependency problem". When a variable
;; appears multiple times in an expression, interval arithmetic treats each
;; occurrence as potentially independent, even when they represent the same value.
(defn make-interval [a b]
  [a b])

(defn div-interval [x y]
  (let [x1 (first x)
        x2 (second x)
        y1 (first y)
        y2 (second y)]
    (make-interval (* x1 (/ 1 y2))
                   (* x2 (/ 1 y1)))))

(def a (make-interval 2 3))
(def result1 (div-interval a a))  ;; Should be [1 1] but isn't
(def result2 (make-interval 1 1)) ;; Direct representation of 1