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

(defn setup-instructions
  []
  (println "Welcome to battle of the bands!")
  (println "You need to have a Spotify account and a token that enables the app to fetch your top artists. Follow these steps:")
  (println "1. Go to https://developer.spotify.com/console/get-current-user-top-artists-and-tracks")
  (println "2. Click on Get Token")
  (println "3. Check user-top-read")
  (println "4. Click on Request token")
  (println "5. Copy your token")
  (println "Now paste it here, please")
)

(defn fetch-bands
  []
  (def spotify-token (read-line))
  (println "How many bands do you want for this battle?")
  (println "8, 16 or 32")
  (def limit (read-line))
  (println "We're fetching your bands...")
  (fetch-spotify-bands (str spotify-token) (str limit))
)

(defn rank
  [bands]
  (println (str "Your top bands are:"))
  (doseq [item (map-indexed vector bands)] (println (+ (first item) 1) "-" (last item)))
)

(defn -main
  [& args]
  (setup-instructions)
  (def bands (fetch-bands))
  (rank bands)
  (fight-generator bands)
)
