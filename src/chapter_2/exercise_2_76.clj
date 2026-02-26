(ns chapter-2.exercise-2-76)

;; As a large system with generic operations
;; evolves, new types of data objects or new operations may
;; be needed. For each of the three strategies—generic operations
;; with explicit dispatch, data-directed style, 
;; and message-passing-style—describe the changes that must be made to a
;; system in order to add new types or new operations. Which
;; organization would be most appropriate for a system in
;; which new types must often be added? Which would be
;; most appropriate for a system in which new operations
;; must often be added?

;; Let's suppose we need to add another complex number implementation
;; (apart of rectangular and polar implementations)

;; Explicit dispatch
;; We have to implement
;; - new-implementation? (the same as polar? and rectangular?)
;; - real-part-new-implementation
;; - imag-part-new-implementation
;; - magnitude-new-implementation
;; - angle-new-implementation
;; - make-from-new-implementation
;; Handle new-implementation in
;; - real-part
;; - imag-part
;; - magnitude
;; - angle
;; - implementation specific selectors

;; Data-directed style
;; We have to implement
;; - real-part-new-implementation
;; - imag-part-new-implementation
;; - magnitude-new-implementation
;; - angle-new-implementation
;; - make-from-new-implementation
;; Add 'new-implementation' type to the table

;; Message passing
;; We have to implement
;; - make-from-implementation
;; Handle new implementation in EVERY existing type
;; - make-from-real-imag
;; - make-from-magang 

;; Which organization would be most appropriate for a system in
;; which new types must often be added? 
;; Message-passing or data-directed styles would be most appropriate,
;; as they require fewer changes and have better encapsulation
;; explicit dispatch -> modify EVERY existing operation to handle the new type
;; data-directed style -> add operation and register them
;; message passing -> create a new constructor

;; Which would be most appropriate for a system in which new operations
;; must often be added?
;; Explicit dispatch and data-directed styles are more sutable.
;; - explicit dispatch -> add a new operation and handle types
;; - data-directed -> add a new operation and put records to the table
;; - message passing -> update EVERY existing type to handle
;; a new operation