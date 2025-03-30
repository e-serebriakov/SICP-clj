(ns chapter-2.exercise-2-78
  (:require [chapter-2.chapter-2 :as ch2]))

(comment 
  "The internal procedures in the scheme-number
  package are essentially nothing more than calls to the primitive procedures
  + ,-, etc. it was not possible to use the primitives of the language 
  directly because our type-tag system
  requires that each data object have a type attached to it. in
  fact, however, all lisp implementations do have a type system,
  which they use internally. primitive predicates such
  as symbol? and number? determine whether data objects
  have particular types. Modify the definitions of `type-tag`,
  `contents`, and `attach-tag` from section 2.4.2 so that our
  generic system takes advantage of scheme's internal type
  system. that is to say, the system should work as before except
  that ordinary numbers should be represented simply
  as scheme numbers rather than as pairs whose car is the
  symbol scheme-number."
)
(defn type-tag [datum]
  (cond
    (number? datum) 'lisp-number
    (and (seq? datum) 
         (= (count datum) 2))
      (first datum)
    :else (throw (ex-info "Bad tagged datum: TYPE-TAG" {:datum datum}))))

(defn contents [datum] 
  (cond 
    (number? datum) datum
    (and (seq? datum) (= (count datum) 2))
     (second datum)
    :else (throw (ex-info "Bad tagged datum: CONTENTS" {:datum datum}))))

(defn attach-tag [type-tag contents] 
    (if (and (= type-tag 'lisp-number)
             (number? contents))
      contents
      (list type-tag contents)))

(defn apply-generic [op & args]
  (let [type-tags (map type-tag args)
        proc (ch2/get-op op type-tags)]
    (if proc
      (apply proc (map contents args))
      (throw (ex-info "No method for these types: APPLY-GENERIC"
                     {:op op :type-tags type-tags})))))

(defn add [x y] (apply-generic 'add x y))
(defn sub [x y] (apply-generic 'sub x y))
(defn mul [x y] (apply-generic 'mul x y))
(defn div [x y] (apply-generic 'div x y))
(defn install-lisp-number-package []
  (letfn [(tag [x] (attach-tag 'lisp-number x))]
    (ch2/put-op 'add '(lisp-number lisp-number) (fn [x y] (tag (+ x y))))
    (ch2/put-op 'sub '(lisp-number lisp-number) (fn [x y] (tag (- x y))))
    (ch2/put-op 'mul '(lisp-number lisp-number) (fn [x y] (tag (* x y))))
    (ch2/put-op 'div '(lisp-number lisp-number) (fn [x y] (tag (/ x y))))
    (ch2/put-op 'make 'lisp-number (fn [x] (tag x)))
    'done))

(comment
  (install-lisp-number-package)

  (add 3 4)  ;; Should return 7
  (sub 10 3) ;; Should return 7
  (mul 4 5)  ;; Should return 20
  (div 10 2) ;; Should return 5
  
  (type-tag 42)     ;; Should return 'lisp-number
  (contents 42)     ;; Should return 42
  (attach-tag 'lisp-number 42)  ;; Should return 42 directly
  
  (add (mul 3 4) (div 10 2))  ;; Should return 17
  )
