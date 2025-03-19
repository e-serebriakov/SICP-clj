(ns chapter-2.exercise-2-70 
  (:require
   [chapter-2.exercise-2-68 :refer [encode]]
   [chapter-2.exercise-2-69 :refer [generate-huffman-tree]]))

;; The following eight-symbol alphabet with
;; associated relative frequencies was designed to eﬃciently
;; encode the lyrics of 1950s rock songs. (Note that the “symbols” of
;; an “alphabet” need not be individual letters.)
;; A 2 GET 2 SHA 3 WAH 1
;; BOOM 1 JOB 2 NA 16 YIP 9
;; Use generate-huffman-tree (Exercise 2.69) to generate a
;; corresponding Huﬀman tree, and use encode (Exercise 2.68)
;; to encode the following message:
;; Get a job
;; Sha na na na na na na na na
;; Get a job
;; Sha na na na na na na na na
;; Wah yip yip yip yip yip yip yip yip yip
;; Sha boom
;; How many bits are required for the encoding? What is the
;; smallest number of bits that would be needed to encode this
;; song if we used a fixed-length code for the eight-symbol alphabet?
(def pairs
  '((a 2)
    (Get 2)
    (Sha 3)
    (Wah 1)
    (boom 1)
    (job 2)
    (na 16)
    (yip 9)))

(def tree (generate-huffman-tree pairs))

(def message '(Get a job
                   Sha na na na na na na na na 
                   Get a job
                   Sha na na na na na na na na
                   Wah yip yip yip yip yip yip yip yip yip
                   Sha boom))

(def encoded (encode message tree))
(count encoded) ; => 102 bits
(count message) ; => 36 symbols
;; Fixed-length encoding
;; There are 8 unqiue symbols -> using 3 bits we can encode all 8 (2^3)
;; There are 36 symbols in the message
;; Total number of bits: 36 * 8 => 108