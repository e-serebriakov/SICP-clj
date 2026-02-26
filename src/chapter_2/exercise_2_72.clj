(ns chapter-2.exercise-2-72)

;; Consider the encoding procedure that you
;; designed in Exercise 2.68. What is the order of growth in
;; the number of steps needed to encode a symbol? Be sure
;; to include the number of steps needed to search the symbol
;; list at each node encountered. To answer this question
;; in general is diﬃcult. Consider the special case where the
;; relative frequencies of the n symbols are as described in Exercise 2.71,
;; and give the order of growth (as a function of n)
;; of the number of steps needed to encode the most frequent
;; and least frequent symbols in the alphabet.

;; For the most frequent symbol we need to check 1 node
;; For the least frequent symbol we need to check n - 1 nodes
;; Each non-leaf node has:
;; - left branch
;; - right branch
;; - a list of all symbols that can be reached through that node
;; At each node, search through a list of symbols to determine which branch to take
;; At each node, we need to search through a decreasing number of symbols
;; First node: n symbols
;; Second node: n-1 symbols
;; Third node: n-2 symbols
;; etc
;; This gives us: n + (n-1) + (n-2) + ... + 2 + 1 => n(n - 1)/2
;; For the most frequent symbol: O(n)
;; For the least frequent: O(n^2)

;;                     31
;;                    /  \
;;                  15    16 (Symbol 5)
;;                 /  \
;;                7    8 (Symbol 4)
;;               / \
;;              3   4 (Symbol 3)
;;             / \
;;            1   2 (Symbol 2)
;; (Symbol 1)