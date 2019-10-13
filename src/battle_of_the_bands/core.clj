(ns battle-of-the-bands.core
  (:gen-class))

(def bands [
  "Iron Maiden"
  "Metallica"
  "Foo Fighters"
  "Queens of the stone age"
  "Rage Against the Machine"
  "Audioslave"
  "Nirvana"
  "The Offspring"
  "Led Zeppelin"
  "Queen"
  "Arctic Monkeys"
  "Deep Purple"
  "ACDC"
  "Ramones"
  "Kiss"
  "The Doors"
])

(defn clashes-generator
  [bands]
  (let [
        bands-shuffled (partition (/ (count bands) 2) (shuffle bands))
        first-bands (first bands-shuffled)
        last-bands (last bands-shuffled)
        ]
        (zipmap first-bands last-bands)
  ))

(defn select-band
  [bands-selected band-fight]
    (let [band-one (first band-fight)
          band-two (last band-fight)
          ]
      (println "Which one is better?")
    (println (str "1 - " band-one))
    (println (str "2 - " band-two))
    (def option (read-line))
    (if (= (str option) "1") (cons band-one bands-selected) (cons band-two bands-selected))))

(defn fight-generator
  [b]
  (if (= (count b) 1)
    (println (str "THE CHAMPION IS: " (first b)))
    (do
      (println "---- NEW ROUND ---")
      (recur (reduce select-band [] (clashes-generator b)))
      )
  ))

      (defn -main
  [& args]
  (println "The fights will start. Are you ready?")
  (println "Go!")
  (fight-generator bands)
  )
