(ns chapter-2.exercise-2-58 
  (:require
   [chapter-2.chapter-2 :refer [=number? same-variable? variable?]]))

;; Suppose we want to modify the diﬀerentiation program so 
;; that it works with ordinary mathematical notation, in which + and * are infix
;; rather than prefix operators. Since the diﬀerentiation program is defined in
;; terms of abstract data, we can modify it to work with diﬀerent 
;; representations of expressions solely by changing the predicates,
;; selectors, and constructors that define the representation of
;; the algebraic expressions on which the diﬀerentiator is to operate.
;; a. Show how to do this in order to diﬀerentiate algebraic
;;    expressions presented in infix form, such as (x + (3 * (x + (y + 2)))).
;;    To simplify the task, assume that + and * always take two arguments 
;;    and that expressions are fully parenthesized.
(defn make-sum [a1 a2]
  (cond (=number? a1 0) a2
        (=number? a2 0) a1
        (and (number? a1) (number? a2)) (+ a1 a2)
        :else (list a1 '+ a2)))

(defn make-product [m1 m2]
  (cond (or (=number? m1 0) (=number? m2 0)) 0
        (=number? m1 1) m2
        (=number? m2 1) m1
        (and (number? m1) (number? m2)) (* m1 m2)
        :else (list m1 '* m2)))
 
(defn sum? [x]
  (and (seq? x) (= (second x) '+)))

(defn addend [x]
  (first x))

(defn augend [x]
  (last x))

(defn product? [x]
  (and (seq? x) (= (second x) '*)))

(defn multiplier [p]
  (first p))

(defn multiplicand [p]
  (last p))

(defn deriv [exp var]
  (cond 
    (number? exp) 0
    (variable? exp) (if (same-variable? exp var) 1 0)
    (sum? exp) (->> var
                    ((juxt #(deriv (addend exp) %)
                          #(deriv (augend exp) %)))
                    (apply make-sum))
    (product? exp) (->> [(make-product (multiplier exp)
                                      (deriv (multiplicand exp) var))
                        (make-product (deriv (multiplier exp) var)
                                    (multiplicand exp))]
                       (apply make-sum))
    :else (throw (ex-info "unknown expression type: DERIV"
                         {:expression exp}))))

(deriv '(x + (3 * (x + (y + 2)))) 'x)
(deriv '(x * y) 'x)
(deriv '(x + y) 'x)

;; b. The problem becomes substantially harder if we allow
;;    standard algebraic notation, such as (x + 3 * (x + y + 2)), 
;;    which drops unnecessary parentheses and assumes that multiplication is
;;    done before addition. Can you design appropriate predicates, selectors, 
;;    and constructors for this notation such that our derivative
;;    program still works?
(defn find-operator
  "Find the rightmost operator of the specified type in a sequence.
   Returns [left op right] or nil if no operator found."
  [operator exp]
  (when (seq? exp)
    (let [operator-positions (->> (map-indexed vector exp)
                                (filter (fn [[_ item]] (= item operator)))
                                last)]
      (when operator-positions
        (let [pos (first operator-positions)
              op (second operator-positions)]
          [(take pos exp) op (drop (inc pos) exp)])))))

(defn sum? [x]
  (and (seq? x)
       (let [op-info (find-operator '+ x)]
         (and op-info (= (second op-info) '+)))))

(defn product? [x]
  (and (seq? x)
       (let [op-info (find-operator '* x)]
         (and op-info (= (second op-info) '*)))))

(defn split-by-operator [operator exp]
  (let [[left op right] (find-operator operator exp)]
    [(if (= (count left) 1)
       (first left)
       (cons (first left) (rest left)))
     (if (= (count right) 1)
       (first right)
       (cons (first right) (rest right)))]))

(defn addend [x]
  (first (split-by-operator '+ x)))

(defn augend [x]
  (second (split-by-operator '+ x)))

(defn multiplier [x]
  (first (split-by-operator '* x)))

(defn multiplicand [x]
  (second (split-by-operator x '*)))

(deriv '(x + 3 * (x + y + 2)) 'x)