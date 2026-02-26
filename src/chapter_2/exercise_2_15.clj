(ns chapter-2.exercise-2-15)

;; Eva Lu Ator, another user, has also noticed
;; the diﬀerent intervals computed by diﬀerent but algebraically
;; equivalent expressions. She says that a formula to compute
;; with intervals using Alyssa’s system will produce tighter
;; error bounds if it can be written in such a form that no vari-
;; able that represents an uncertain number is repeated. Thus,
;; she says, par2 is a “better” program for parallel resistances
;; than par1. Is she right? Why?

;; When working with interval arithmetic,
;; each occurrence of a variable is treated independently, even when
;; it represents the same value. It's called "dependency problem"
;; When there are no multiple occurrences,
;; the interval expression gives the exact result
