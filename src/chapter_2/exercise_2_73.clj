(ns chapter-2.exercise-2-73)

;;  Section 2.3.2 described a program that performs symbolic diﬀerentiation:
;; 
;; (define (deriv exp var)
;;   (cond ((number? exp) 0)
;;     ((variable? exp)
;;      (if (same-variable? exp var) 1 0))
;;     ((sum? exp)
;;      (make-sum (deriv (addend exp) var)
;;                (deriv (augend exp) var)))
;;     ((product? exp)
;;      (make-sum (make-product
;;                 (multiplier exp)
;;                 (deriv (multiplicand exp) var))
;;                (make-product
;;                 (deriv (multiplier exp) var)
;;                 (multiplicand exp))))
;;     ⟨more rules can be added here⟩
;;     (else (error "unknown expression type:
;;                  DERIV" exp))))
;;
;; We can regard this program as performing a dispatch on the type
;; of the expression to be diﬀerentiated. In this situation the “type tag”
;; of the datum is the algebraic operator symbol (such as +) and the operation 
;; being performed is deriv. We can transform this program into data-directed
;; style by rewriting the basic derivative procedure as
;; (define (deriv exp var)
;;   (cond ((number? exp) 0)
;;    ((variable? exp) (if (same-variable? exp var) 1 0))
;;    (else ((get 'deriv (operator exp))
;;           (operands exp) var))))
;; 
;; (define (operator exp) (car exp))
;; (define (operands exp) (cdr exp))
;;
;; a. Explain what was done above. Why can’t we assimilate
;;    the predicates number? and variable? into the data-directed dispatch?
;; deriv was rewritten in data-directed style. It means that instead of
;; explicit `cond` statements we use a table to access an appropriate
;; differentiation procedure based on the operator

;; In the data-directed version of deriv,
;; the system expects to handle expressions by looking at
;; their operator (first element) and operands (rest of the elements).
;; We can't split number and variable into (operator operands)


;; b. Write the procedures for derivatives of sums and products, 
;;    and the auxiliary code required to install them in the table 
;;    used by the program above.

;; (define (install-sum-package)
;; (define (deriv-sum exp var)
;;  (make-sum (deriv (addend exp) var)
;;            (deriv (augend exp) var))
;; (put 'deriv + deriv-sum)
;; 'done)

;; (define (install-product-package)
;; (define (deriv-product exp var)
;;  (make-sum (make-product
;;             (multiplier exp)
;;             (deriv (multiplicand exp) var))
;;            (make-product
;;             (deriv (multiplier exp) var)
;;             (multiplicand exp)))
;; (put 'deriv * deriv-product)
;; 'done)