(ns chapter-2.exercise-2-6)


;; In case representing pairs as procedures wasn’t
;; mind-boggling enough, consider that, in a language that
;; can manipulate procedures, we can get by without numbers
;; (at least insofar as nonnegative integers are concerned) by
;; implementing 0 and the operation of adding 1 as
(def zero (fn [f] (fn [x] x)))
(defn add-1 [n]
  (fn [f] (fn [x] (f ((n f) x)))))

(add-1 zero)
(fn [f]
  (fn [x]
    (f (((fn [f]
           (fn [x] x))
         f)
        x))))
(fn [f]
  (fn [x]
    (f ((fn [x] x)
        x))))

(fn [f] (fn [x] (f x)))
(def one (fn [f] (fn [x] (f x)))) ;; this is one

(add-1 one)
(fn [f] 
  (fn [x] 
    (f (((fn [f]
           (fn [x]
             (f x))) f)
        x))))

(fn [f] 
  (fn [x] 
    (f ((fn [x] (f x)) x))))

(fn [f] (fn [x] (f (f x))))
(def two (fn [f] (fn [x] (f (f x))))) ;; this is two