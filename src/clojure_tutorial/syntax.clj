(ns clojure-tutorial.syntax)

; this is a comment

; character literals
(println (char 88))
(println (char \u01D2))
(println \n)
(println (str "line1" \newline "line2"))
(println \c\tab\c)
(println \c\space\h)

; string literals
(println "txt")
(println (str "c" "h" "e" "w"))

; keywords

(println :keyword) 
(println (keyword "name"))

; keyword resolved in current namespace

(println ::keyword) ; :clojure-tutorial.syntax/keyword

; regular expression

(println #"(.|A)")
(println (re-pattern "(.|A)"))

; commas are treated as whitespace

(println (str "A","B","C"))
(println (str "A" "B" "C"))

; list - linked list
(println '("A" "B" "C"))
(println '(:A :B :C))
(println '(::A ::B ::C))
(println (list :A :B :C))
(println (list :A :B "C"))

; vectors

(println [:A :B :C])
(println (vector :A :B :C))
(println (vector :A :B "C"))

; hash sets

(def s #{:A "B" :C "D"})
(println s)
(println (get s :A))
(def s1 (hash-set :A "B" :C "D"))
(println s1)

; sorted sets

(def s2 (sorted-set "A" "B" "C" "D"))
(println s2)
(println (get s2 "A"))
(println (first s2))
(println (rest s2))

; hash maps

(def m {:A "A" :B "B"})
(def m (hash-map :A "A" :B "B"))
(println m)
(println (get m :A))
(println (instance? String (get m :A)))

; sorted maps

(def m (sorted-map :B "B" :A "A"))
(println m)
(println (get m :A))
(println (instance? String (get m :A)))

; adding metadata to a symbol or collection

(def x {"x" 1, "y" 2})
(with-meta x {"meta" 21})
(println x)
(println (meta x))

; create an anonymous function

(def f #(* %1 %2))
(println (f 1 2))
(def x (map #(* %1 %1) [1 2 3]))
(println x)

