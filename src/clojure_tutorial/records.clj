(ns clojure-tutorial.records)

;; from Stu's examples:

(defrecord Person [fname lname address]) ;-> user.Person

(defrecord Address [street city state zip]) ;-> user.Address

(def stu (Person. "Stu" "Halloway"
                  (Address. "200 N Mangum"
                            "Durham"
                            "NC"
                            27701))) ;-> #'user/stu
(println stu)

(println (:lname stu)) ;-> "Halloway"

(println (-> stu :address :city)) ;-> "Durham"

(println (assoc stu :fname "Stuart")) ;-> #:user.Person{:fname "Stuart", :lname "Halloway", :address #:user.Address{:street "200 N Mangum", :city "Durham", :state "NC", :zip 27701}}

(println (update-in stu [:address :zip] inc)) ;-> #:user.Person{:fname "Stu", :lname "Halloway", :address #:user.Address{:street "200 N Mangum", :city "Durham", :state "NC", :zip 27702}}

; for more look at http://clojuredocs.org/clojure_core/clojure.core/defrecord