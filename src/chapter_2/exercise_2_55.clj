(ns chapter-2.exercise-2-55)

;; Eva Lu Ator types to the interpreter the expression
;; (car ''abracadabra)
;; To her surprise, the interpreter prints back "quote". Explain.
(first ''abracadabra) ; => quote
;; is equivalent to
(first '(quote abracadabra)) ; => quote
; it gets the first element from the quoted list