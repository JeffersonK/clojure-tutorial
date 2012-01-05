(ns clojure-tutorial.lists)

(def stooges (list "Moe" "Larry" "Curly"))
(def stooges2 (quote ("Moe" "Larry" "Curly")))
(def stooges3 '("Moe" "Larry" "Curly"))

(println stooges)

(println (some #(= % "Moe") stooges))

(println (some #(= % "Mark") stooges)) ; -> nil

(println (contains? (set stooges) "Moe")) ; -> true

; conj

(def more-stooges (conj stooges "Shemp"))
(println more-stooges)

; remove

(def less-stooges (remove #(= % "Curly") more-stooges)) 
(println less-stooges)

; into

(def kids-of-mike '("Greg" "Peter" "Bobby"))
(def kids-of-carol '("Marcia" "Jan" "Cindy"))
(def brady-bunch (into kids-of-mike kids-of-carol))
(println brady-bunch)

(def x (into [:x] brady-bunch))
(println x)

(def x-string (filter #(instance? String %) x))
(println x-string)
