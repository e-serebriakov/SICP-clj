(ns chapter-2.exercise-2-74)

;; Insatiable Enterprises, Inc., is a highly decentralized conglomerate company
;; consisting of a large number of independent divisions 
;; located all over the world. The company’s computer facilities have just been 
;; interconnected by means of a clever network-interfacing scheme that makes
;; the entire network appear to any user to be a single computer. 
;; Insatiable’s president, in her first attempt to exploit
;; the ability of the network to extract administrative information
;; from division files, is dismayed to discover that, although all the division
;; files have been implemented as data structures in Scheme, the particular data
;; structure used varies from division to division. 
;; A meeting of division managers is hastily called to search for a strategy 
;; to integrate the files that will satisfy headquarters’ needs 
;; while preserving the existing autonomy of the divisions.
;; Show how such a strategy can be implemented with data-directed programming.
;; As an example, suppose that each division’s personnel records
;; consist of a single file, which contains a set of records keyed
;; on employees’ names. The structure of the set varies from division to
;; division. Furthermore, each employee’s record is itself a set (structured
;; diﬀerently from division to division) that contains information keyed
;; under identifiers such as `address` and `salary`.
;; In particular:
(def division-methods (atom {}))
(defn register-division-method
  [division-type operation method]
  (swap! division-methods assoc-in [division-type operation] method))

;; a. Implement for headquarters a get-record procedure
;; that retrieves a specified employee’s record from a
;; specified personnel file. The procedure should be applicable to any division’s
;; file. Explain how the individual divisions’ files should be structured. 
;; In particular, what type information must be supplied?
(defn get-record
  [employee-name personnel-file]
  (let [division-type (:division-type personnel-file)
        get-record-method (get-in @division-methods [division-type :get-record])]
    (if get-record-method
      (get-record-method employee-name personnel-file)
      (throw (ex-info "Unknown division type: " {:division-type division-type})))))

;; Division A (stores records in a map)
(register-division-method 
  :division-a 
  :get-record 
  (fn [name file] 
    (get-in file [:records name])))

;; Division B (stores records in a vector of [name record] pairs)
(register-division-method 
  :division-b 
  :get-record 
  (fn [name file]
    (->> (:records file)
         (filter #(= (first %) name))
         first
         second)))

;; Division A's personnel file might look like:
(def division-a-file
  {:division-type :division-a
   :records {"John" {:name "John" :salary 50000}
            "Mary" {:name "Mary" :salary 60000}}})

;; Division B's personnel file might look like:
(def division-b-file
  {:division-type :division-b
   :records [["Tom" {:name "Tom" :salary 55000}]
            ["Sue" {:name "Sue" :salary 65000}]]})

;; Division's file must have division-type information

;; b. Implement for headquarters a get-salary procedure that returns 
;; the salary information from a given employee’s record from any division’s 
;; personnel file. How should the record be structured in order to make this
;; operation work?
(defn get-salary
  [employee-name personnel-file]
  (let [division-type (:division-type personnel-file)
        get-salary-method (get-in @division-methods [division-type :get-salary])]
    (if get-salary-method
      (get-salary-method employee-name personnel-file)
      (throw (ex-info "Unknown division type: " {:division-type division-type})))))

;; Division A
(register-division-method 
  :division-a 
  :get-salary 
  (fn [name file] 
    (get-in file [:records name :salary])))

;; Division B
(register-division-method 
  :division-b 
  :get-salary 
  (fn [name file]
    (->> (:records file)
         (filter #(= (first %) name))
         first
         second
         :salary)))

;; The key requirements for this to work are:
;; Each division must register its get-salary method
;; All divisions must store salary information under a :salary key in their employee records
;; Each personnel file must include its :division-type

;; c. Implement for headquarters a find-employee-record
;; procedure. This should search all the divisions’ files
;; for the record of a given employee and return the record.
;; Assume that this procedure takes as arguments an
;; employee’s name and a list of all the divisions’ files.
(defn find-employee-record
  [employee-name division-files]
  (some #(try
           (get-record employee-name %)
           (catch Exception _ nil))
        division-files))

(def all-divisions [division-a-file division-b-file])

(find-employee-record "John" all-divisions)  
(find-employee-record "Tom" all-divisions)   
(find-employee-record "NotFound" all-divisions) 

;; d. When Insatiable takes over a new company, what changes
;; must be made in order to incorporate the new personnel
;; information into the central system?

;; 1. Add division type (e.g. division-c)
;;    All personnel files need to include this identifier
;; 2. Register methods (get-record, get-salary)