(ns battle-of-the-bands.core
  (:require [battle-of-the-bands.bands :refer [all-bands]])
  (:gen-class)
)

(defn knockout-generator
  [bands]
  (let [
    bands-shuffled (partition (/ (count bands) 2) (shuffle bands))
    first-group (first bands-shuffled)
    last-group (last bands-shuffled)
  ]
    (zipmap first-group last-group)
  )
)

(defn fight-bands
  [bands-selected bands-to-fight]
  (let [
    band-one (first bands-to-fight)
    band-two (last bands-to-fight)
  ]
    (println "Which one is better?")
    (println (str "1 - " band-one))
    (println (str "2 - " band-two))

    (def option (read-line))
    (if (= (str option) "1")
      (cons band-one bands-selected)
      (cons band-two bands-selected)
    )
  )
)

(defn fight-generator
  [bands]
  (if (= (count bands) 1)
    (println (str "THE CHAMPION IS: " (first bands)))
    (do
      (println "---- NEW ROUND ----")
      (recur (reduce fight-bands [] (knockout-generator bands)))
    )
  )
)

(defn -main
  [& args]
  (println "The fights will start. Are you ready?")
  (println "Go!")
  (fight-generator all-bands)
)
