(ns chapter-2.exercise-2-27)

;; Modify your reverse procedure of Exercise
;; 2.18 to produce a deep-reverse procedure that takes a list
;; as argument and returns as its value the list with its elements reversed and with all sublists deep-reversed as well.
;; For example,
(defn deep-reverse [l]
  (if (empty? l)
    l
    (concat (deep-reverse (rest l))
            (if (list? (first l)) 
               (list (deep-reverse (first l)))
               (list (first l))))))

(defn deep-reverse [l]
  (reduce (fn [acc x]
            (cons (if (list? x)
                    (deep-reverse x)
                    x)
                  acc)) () l))

(defn deep-reverse [l]
  (loop [remaining l
         result ()]
    (if (empty? remaining)
      result
      (recur (rest remaining)
             (cons (let [x (first remaining)]
                     (if (list? x)
                       (deep-reverse x)
                       x))
                   result)))))

(defn deep-reverse [l]
  (->> l
       reverse
       (map (fn [x]
             (if (list? x)
               (deep-reverse x)
               x)))))

(defn deep-reverse [l]
  (into () (map (fn [x]
                  (if (list? x)
                    (deep-reverse x)
                    x)) l)))

(deep-reverse (list 1 2 3))
(deep-reverse (list 1 (list 2 3) 4))