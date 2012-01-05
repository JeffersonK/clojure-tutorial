(ns clojure-tutorial.maps)

(def popsicle-map
  (hash-map :red :cherry, :green :apple, :purple :grape))

(def popsicle-map
  {:red :cherry, :green :apple, :purple :grape}) ; same as previous

;sorted map
(def popsicle-map
  (sorted-map :red :cherry, :green :apple, :purple :grape))

(println (get popsicle-map :green))
(println (popsicle-map :green))
(println (:green popsicle-map))

; contains? operates on sets and maps

(println (contains? popsicle-map :green)) ; -> true

(def k (keys popsicle-map))
(println k)

(def v (vals popsicle-map))
(println v)

; assoc func operates on maps and vectors

(def more-popsicles (assoc popsicle-map :green :lime :blue :blueberry))
(println more-popsicles)

; disassoc

(def p (popsicle-map :green :blue))
(println p)

; doseq / name

(println "popsicle map => " popsicle-map)
(doseq [[color flavor] popsicle-map]
       (println (str "The flavor of " (name color)
       " popsicles is " (name flavor) ".")))


; select-keys takes a map and a sequence of keys, returns map where only selected keys are in the map

(println (select-keys popsicle-map [:red :green :blue])) ; -> {:green :apple, :red :cherry}

; conj

(def person {
  :name "Mark Volkmann"
  :address {
    :street "644 Glen Summit"
    :city "St. Charles"
    :state "Missouri"
    :zip 63304}
  :employer {
    :name "Object Computing, Inc."
    :address {
      :street "12140 Woodcrest Executive Drive, Suite 250"
      :city "Creve Coeur"
      :state "Missouri"
      :zip 63141}}})

; get-in OR -> OR reduce

(def a (get-in person [:employer :address :city]))
(println a)

(def b (-> person :employer :address :city))
(println b)

(def c (reduce get person [:employer :address :city]))
(println c)

; what if doesn't exist

(def blah (get-in person [:employer :address :city :house]))
(println blah)

(def blah (get-in person [:poop]))
(println blah)

; the following are equivalent
; (f1 (f2 (f3 x)))
; (-> x f3 f2 f1)

(def person-mod (assoc-in person [:employer :address :city] "Clayton"))

(println person \newline)
(println person-mod \newline)

(def u (update-in person [:employer :address :zip] str "-1234")) ; using the str function
(println u)