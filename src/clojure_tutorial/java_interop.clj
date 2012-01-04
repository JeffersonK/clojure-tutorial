(ns clojure-tutorial.java_interop)

(import
	'(java.util Calendar GregorianCalendar)
	'(javax.swing JFrame JLabel))


;two ways to access constants in java class

(. java.util.Calendar APRIL) ; -> 3
(. Calendar APRIL) 
(println (. Calendar APRIL))

; two ways to invoke a static method in a java class
(println (. Math pow 2 4)) ; -> 16.0
(println (Math/pow 2 4))

; two ways to invoke a constructor for a java class

(def calendar (new GregorianCalendar 2008 Calendar/APRIL 16)) ; April 16, 2008
(def calendar (GregorianCalendar. 2008 Calendar/APRIL 16))
(println (. calendar toString))

;two ways to invoke an instance method in a java object

(. calendar add Calendar/MONTH 2)
(println (. calendar get Calendar/MONTH)) ; -> 5

(.add calendar Calendar/MONTH 2) ; preferred method
(println (.get calendar Calendar/MONTH)) ; -> 7

; method calls can be chained using the .. macro

(def a (. (. calendar getTimeZone) getDisplayName)) ; long way
(println a)

(def b (.. calendar getTimeZone getDisplayName)) ; -> 'Central Standard Time'
(println b)

; .?. can be used to catch and return nil if and methods in the chain return a null. This avoids getting a "NullPointerException"

;doto function is used to invoke many methods on the same object

(doto calendar
      (.set Calendar/YEAR 1981)
      (.set Calendar/MONTH Calendar/AUGUST)
      (.set Calendar/DATE 1))
(def formatter (java.text.DateFormat/getDateInstance))
(def x (.format formatter (.getTime calendar))) ; -> "Aug 1, 1981"
(println x)

;memfn macro for calling java methods instead of using anonymous functions

(println (map #(.substring %1 %2)
	 ["Moe" "Larry" "Curly"] [1 2 3])) ; -> (oe rry ly)

(println (map (memfn substring beginIndex)
	 ["Moe" "Larry" "Curly"] [1 2 3])) ;