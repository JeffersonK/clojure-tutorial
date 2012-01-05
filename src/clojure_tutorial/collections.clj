(ns clojure-tutorial.collections)

; count

(def c (count [19 "yellow" true])) ; -> 3
(println c)

; reverse

(def r (reverse [2 4 7])) ; -> (7 4 2)
(println r)

; map

(def m (map #(+ % 3) [2 4 7])) ; -> (5 7 10)
(println m)

(def m (map #(+ %1 %2) [2 4 7] [2 4])) ; -> (4 8)
(println m)

(map inc [1 2 3 4 5])
;(2 3 4 5 6)


;; map can be used with multiple collections. Collections will be consumed
;; and passed to the mapping function in parallel:
(map + [1 2 3] [4 5 6]) ; (5 7 9)


;; When map is passed more than one collection, the mapping function will
;; be applied until one of the collections runs out:
(println (map + [1 2 3] (iterate inc 1))) ; -> (2 4 6)

;; map is often used in conjunction with the # reader macro:
(println (map #(str "" % "") ["the" "quick" "brown" "fox"])) ; -> ("the" "quick" "brown" "fox")

;; A useful idiom to pull "columns" out of a collection of collections:
(println (apply map vector [[:a :b :c] [:d :e :f] [:g :h :i]]))  ; -> ([:a :d :g] [:b :e :h] [:c :f :i]))


;; From http://clojure-examples.appspot.com/clojure.core/map


; apply

(def a (apply / [1 2 3]))
(println a)

(def stooges ["Moe" "Larry" "Curly" "Shemp"])
(println (first stooges))
(println (second stooges))
(println (last stooges))
(println (drop-last 2 stooges))
(println (let [c (count stooges)]
      (drop-last (- c 1) stooges)))


(println (next stooges))
(println (butlast stooges))
(println (filter #(> (count %) 3) stooges))

(println (nthnext stooges 2))
(println (nthnext stooges 1))

(println (every? #(instance? String %) stooges))

(println (not-every? #(instance? String %) stooges))

(println (some #(instance? String %) stooges))

(println (not-any? #(instance? String %) stooges))