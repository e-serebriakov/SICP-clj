(ns chapter-2.exercise-2-12 
  (:require
   [chapter-2.exercise-2-7 :refer [lower-bound make-interval upper-bound]]))

;; Define a constructor make-center-percent
;; that takes a center and a percentage tolerance and produces
;; the desired interval. You must also define a selector
;; percent that produces the percentage tolerance for a given
;; interval. The center selector is the same as the one shown above.
(defn make-center-percent [c p]
  (let [dc (-> p (/ 100) (* c))]
    (make-interval (- c dc) (+ c dc))))

(defn percent [interval]
  (let [dc (/ (- (upper-bound interval)
                 (lower-bound interval))
              2)
        center (+ (lower-bound interval) dc)]
    (* (/ dc center) 100)))

(defn percent [interval]
  (-> interval
      ((juxt upper-bound lower-bound))
      ((fn [[u l]]
         (let [dc (/ (- u l) 2)]
           (-> dc
               (/ (+ l dc))
               (* 100)))))))

(def interval (make-center-percent 120 10))
(percent interval)