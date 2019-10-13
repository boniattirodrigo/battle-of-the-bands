(ns battle-of-the-bands.core
  (:require [battle-of-the-bands.bands :refer [fetch-spotify-bands]])
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
  (println "Welcome to battle of the bands!")
  (println "We need your spotify token to fetch your top artists listened. We don't store it.")
  (println "Go to https://developer.spotify.com/console/get-current-user-top-artists-and-tracks, check user-top-read and click on get token")
  (println "Now paste it here, please")
  (def spotify-token (read-line))
  (println "How many bands do you want for this battle?")
  (println "8, 16 or 32")
  (def limit (read-line))
  (println "We're fetching your bands...")
  (def bands (fetch-spotify-bands (str spotify-token) (str limit)))
  (println (str "Here your top " limit " bands are:"))
  (doseq [item (map-indexed vector bands)] (println (+ (first item) 1) "-" (last item)))
  (println "The battle is going to start:")
  (fight-generator bands)
)
