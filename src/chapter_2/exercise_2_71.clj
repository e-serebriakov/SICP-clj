(ns chapter-2.exercise-2-71)

;; Suppose we have a Huﬀman tree for an alphabet of n symbols, 
;; and that the relative frequencies of the symbols are 1, 2, 4, . . . , 2n−1.
;; Sketch the tree for n = 5; for n = 10. In such a tree (for general n)
;; how many bits are required to encode the most frequent symbol? The least
;; frequent symbol?

;; For n = 5, the frequencies would be:
;; Symbol 1: 1
;; Symbol 2: 2
;; Symbol 3: 4
;; Symbol 4: 8
;; Symbol 5: 16

;; n = 5
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

;; n = 10
;;                                 1023
;;                                 /  \
;;                               511   512 (Symbol 10)
;;                              /  \
;;                            255   256 (Symbol 9)
;;                           /  \
;;                         127   128 (Symbol 8)
;;                         /  \
;;                       63    64 (Symbol 7)
;;                      /  \
;;                    31    32 (Symbol 6)
;;                   /  \
;;                 15    16 (Symbol 5)
;;                /  \
;;               7    8 (Symbol 4)
;;              / \
;;             3   4 (Symbol 3)
;;            / \
;;           1   2 (Symbol 2)
;; (Symbol 1)

;; For the most frequent symbol we need 1 bit
;; For the least frequen symbol — n - 1