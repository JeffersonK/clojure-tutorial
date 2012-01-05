(ns clojure-tutorial.sets)

(def stooges (hash-set "Moe" "Larry" "Curly")) ; not sorted
(def stooges #{"Moe" "Larry" "Curly"}) ; same as previous
(def sorted-stooges (sorted-set "Moe" "Larry" "Curly"))

(println stooges)
(println sorted-stooges)

; conj

(def more-sorted-stooges (conj sorted-stooges "Shemp"))
(println more-sorted-stooges)

(println (contains? stooges "Moe"))
(println (if (stooges "person") "stooge" "regular person"))
(println (if (stooges "Moe") "stooge" "regular person"))

(println (into stooges sorted-stooges))
(println (into sorted-stooges stooges))
(println (into sorted-stooges #{"zebra"}))
(println (into sorted-stooges #{"jeff"}))
(println (into #{"jeff"} sorted-stooges))

; when combining sets of different types the first set passed to into is the output set
(println (instance? (class (sorted-set "jeff")) (into #{"jeff"} sorted-stooges)))

(println (instance? (class (sorted-set "jeff")) (into sorted-stooges #{"jeff"})))

; disj
(def less-stooges (disj more-sorted-stooges "Curly"))
(println less-stooges)

(println (sort less-stooges))

; difference

; index
(require 'clojure.set)

(def weights #{ {:name "betsy" :weight 1000}
     	        {:name "jake"  :weight 756}
		{:name "shyq"  :weight 1000} })

(println weights)
(def by-weight (clojure.set/index weights [:weight]))
(println by-weight)

(def weights2 {"betsy" 1000 "jake" 756 "shyq" 1000})
(println weights2)
(def by-weight2 (clojure.set/index weights2 [:weight]))
(println by-weight2)

; Also consider the functions in the clojure.set namespace which include: difference, index, intersection, join, map-invert, project, rename, rename-keys, select and union. Some of these functions operate on maps instead of sets.

