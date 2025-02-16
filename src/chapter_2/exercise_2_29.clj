(ns chapter-2.exercise-2-29)

;; A binary mobile consists of two branches,
;; a let branch and a right branch. Each branch is a rod of
;; a certain length, from which hangs either a weight or another binary mobile.
;; We can represent a binary mobile using compound data
;; by constructing it from two branches
;; (for example, using list):
(defn make-mobile [left right]
  (list left right))

;; A branch is constructed from a length (which must be a
;; number) together with a structure, which may be either a
;; number (representing a simple weight) or another mobile:
(defn make-branch [length structure]
  (list length structure))

(def simple-mobile
  (make-mobile
   (make-branch 4 6)
   (make-branch 2 5)))

(def complex-mobile
  (make-mobile
   (make-branch 4 6)
   (make-branch 2
                (make-mobile
                 (make-branch 3 2)
                 (make-branch 1 3)))))

;; a. Write the corresponding selectors left-branch and
;; right-branch, which return the branches of a mobile,
;; and branch-length and branch-structure, which return the components of a branch.
(defn left-branch [mobile]
  (first mobile))

(defn right-branch [mobile]
  (last mobile))

(defn branch-length [branch]
  (first branch))

(defn branch-structure [branch]
  (last branch))

;; b. Using your selectors, define a procedure total-weight
;; that returns the total weight of a mobile.
(defn total-weight [mobile]
  (letfn [(branch-weight [branch]
            (let [structure (branch-structure branch)]
              (if (number? structure)
                structure
                (total-weight structure))))]
    (+ (branch-weight (left-branch mobile))
       (branch-weight (right-branch mobile)))))
      
(total-weight simple-mobile)
(total-weight complex-mobile)

;; c. A mobile is said to be balanced if the torque applied by
;; its top-left branch is equal to that applied by its top-right branch
;; (that is, if the length of the left rod multiplied by the weight 
;; hanging from that rod is equal to the corresponding product for the right side)
;; and if each of the submobiles hanging oﬀ its branches is balanced. 
;; Design a predicate that tests whether a binary mobile is balanced.
(defn balanced? [mobile]
  (letfn [(branch-torque [branch]
            (let [length (branch-length branch)
                  structure (branch-structure branch)
                  weight (if (number? structure)
                           structure
                           (total-weight structure))]
              (* length weight)))
          (branch-balanced? [branch]
            (let [structure (branch-structure branch)]
              (if (number? structure)
                true
                (balanced? structure))))]
    (and (= (branch-torque (left-branch mobile))
            (branch-torque (right-branch mobile)))
         (branch-balanced? (left-branch mobile))
         (branch-balanced? (right-branch mobile)))))

(balanced? simple-mobile)
(balanced? complex-mobile)

;; d. Suppose we change the representation of mobiles so
;; that the constructors are
(defn make-mobile [left right]
  [left right])

(defn make-branch [length structure]
  [length structure])

;; No changes required
