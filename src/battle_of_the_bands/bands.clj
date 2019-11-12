(ns battle-of-the-bands.bands
  (:require [clj-http.client :as client])
  (:require [cheshire.core :refer :all])
)

(def stopify-base-url "https://api.spotify.com/v1/me/top/artists?limit=")

(defn fetch-spotify-bands
  [token limit]
  (let [
    response (client/get (str stopify-base-url limit) {:accept :json :headers {"Authorization" (str "Bearer " token) }})
  ]
    (->> (parse-string (:body response) true)
        :items
        (map :name))
  )
)
