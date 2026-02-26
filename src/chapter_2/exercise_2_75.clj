(ns chapter-2.exercise-2-75 
  (:require
   [clojure.math :as math]))

;; Implement the constructor make-from-magang in message-passing style.
;; this procedure should be analogous to the make-from-real-imag procedure 
;; given above.
(defn make-from-magang [r a]
  (letfn [(dispatch
           [op]
           (cond (= op 'real-part) (* r (math/cos a))
                 (= op 'imag-part) (* r (math/sin a))
                 (= op 'magnitude) r
                 (= op 'angle) a
                 :else (throw (ex-info "Unknown op: MAKE-FROM-MAGANG" {:op op}))))]
    dispatch))