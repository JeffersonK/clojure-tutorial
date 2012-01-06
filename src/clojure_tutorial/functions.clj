(ns clojure-tutorial.functions)

(defn parting
  "returns a String parting"
  [name]
  (str "Goodbye, " name))

(println (parting "Mark"))
                                        ; use declare at top of file to forward delcare functions
                                        ; since functions must appear after they are defined

                                        ; defn- defines namespace local functions

(defn power [base & exponents]
  (reduce #(Math/pow %1 %2) base exponents))

(println (power 2 3 4))

(println (power 2 3 4 5))

(defn parting
  "returns a String parting in a given language"
  ([] (parting "World"))
  ([name] (parting name "en"))
  ([name language]
    ; condp is similar to a case statement in other languages.
    ; It is described in more detail later.
    ; It is used here to take different actions based on whether the
    ; parameter "language" is set to "en", "es" or something else.
    (condp = language
      "en" (str "Goodbye, " name)
      "es" (str "Adios, " name)
      (throw (IllegalArgumentException.
        (str "unsupported language " language))))))

(println (parting)) ; -> Goodbye, World
(println (parting "Mark")) ; -> Goodbye, Mark
(println (parting "Mark" "es")) ; -> Adios, Mark
;(println (parting "Mark", "xy")) ; -> java.lang.IllegalArgumentException: unsupported language xy

(def years [1940 1944 1961 1985 1987])
(println (filter (fn [year] (even? year)) years)) ; long way w/ named arguments -> (1940 1944)
(println (filter #(even? %) years)) ; short way where % refers to the argument

(defn pair-test [test-fn n1 n2]
  (if (test-fn n1 n2) "pass" "fail"))

; Use a test-fn that determines whether
; the sum of its two arguments is an even number.
(println (pair-test #(even? (+ %1 %2)) 3 5)) ; -> pass


(defmulti what-am-i class) ; class is the dispatch function
(defmethod what-am-i Number [arg] (println arg "is a Number"))
(defmethod what-am-i String [arg] (println arg "is a String"))
(defmethod what-am-i :default [arg] (println arg "is something else"))
(what-am-i 19) ; -> 19 is a Number
(what-am-i "Hello") ; -> Hello is a String
(what-am-i true) ; -> true is something else

(defmulti am-i-even even?) ;dispatch
(defmethod am-i-even true [arg] (str arg " is even"))
(defmethod am-i-even false [arg] (str arg " is odd"))
(println (am-i-even 1))
(println (am-i-even 2))

(defn callback1 [n1 n2 n3] (+ n1 n2 n3)) ; uses all three arguments
(defn callback2 [n1 _ n3] (+ n1 n3)) ; only uses 1st & 3rd arguments
(defn caller [callback value]
  (callback (+ value 1) (+ value 2) (+ value 3)))
(caller callback1 10) ; 11 + 12 + 13 -> 36
(caller callback2 10) ; 11 + 13 -> 24

(defn teenager? [age] (and (>= age 13) (< age 20)))
(def non-teen? (complement teenager?))
(println (non-teen? 47)) ; -> true

(defn times2 [n] (* n 2))
(defn minus3 [n] (- n 3))
; Note the use of def instead of defn because comp returns
; a function that is then bound to "my-composition".
(def my-composition (comp minus3 times2))
(println (my-composition 4)) ; 4*2 - 3 -> 5

; Note the use of def instead of defn because partial returns
; a function that is then bound to "times2".
(def times2 (partial * 2))
(times2 3 4) ; 2 * 3 * 4 -> 24

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- polynomial
  "computes the value of a polynomial
   with the given coefficients for a given value x"
  [coefs x]
  ; For example, if coefs contains 3 values then exponents is (2 1 0).
  (let [exponents (reverse (range (count coefs)))]
    ; Multiply each coefficient by x raised to the corresponding exponent
    ; and sum those results.
    ; coefs go into %1 and exponents go into %2.
    (apply + (map #(* %1 (Math/pow x %2)) coefs exponents))))

(defn- derivative
  "computes the value of the derivative of a polynomial
   with the given coefficients for a given value x"
  [coefs x]
  ; The coefficients of the derivative function are obtained by
  ; multiplying all but the last coefficient by its corresponding exponent.
  ; The extra exponent will be ignored.
  (let [exponents (reverse (range (count coefs)))
        derivative-coefs (map #(* %1 %2) (butlast coefs) exponents)]
    (println exponents)
    (println derivative-coefs)
    (polynomial derivative-coefs x)))

(def f (partial polynomial [2 1 3])) ; 2x^2 + x + 3
(def f-prime (partial derivative [2 1 3])) ; 4x + 1

(println "f(2) =" (f 2)) ; -> 13.0
(println "f'(2) =" (f-prime 2)) ; -> 9.0

;%1 = a, %2 = b, result is ax + b
;%1 = ax + b, %2 = c, result is (ax + b)x + c = ax^2 + bx + c 
(defn- polynomial2
  "computes the value of a polynomial
   with the given coefficients for a given value x"
  [coefs x]
  (reduce #(+ (* x %1) %2) coefs))
(println (polynomial2 [1 2 3] 3))

                                        ; memoize

; Note the use of def instead of defn because memoize returns
; a function that is then bound to "memo-f".
(def memo-f (memoize f))

(println "priming call")
(time (f 2))

(println "without memoization")
; Note the use of an underscore for the binding that isn't used.
(dotimes [_ 3] (time (f 2)))

(println "with memoization")
(dotimes [_ 3] (time (memo-f 2)))



