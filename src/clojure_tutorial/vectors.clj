(ns clojure-tutorial.vectors)

(def stooges (vector "Moe" "Larry" "Curly"))
(def stooges ["Moe" "Larry" "Curly"])

(def x (get stooges 0 "unkown")) ; -> Moe
(println x)

(def x (get stooges 3 "unkown")) ; -> unknown
(println x)

(def x (assoc stooges 0 "Shemp")) ;  -> ["Shemp" "Larry" "Curly"]
(println x)

(def x (assoc stooges 3 "Shemp")) ;  -> ["Moe" "Larry" "Curly" "Shemp"]
(println x)

