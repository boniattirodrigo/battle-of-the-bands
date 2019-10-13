# Battle of the bands

Between your top Spotify artists you need to decide which is the best one.

## Installation
```
git clone git@github.com:boniattirodrigo/battle-of-the-bands.git
cd battle-of-the-bands
lein deps
```

## Run
You need to have a Spotify account and a token that enables the app to fetch your top artists. Follow these steps:
1. Go to https://developer.spotify.com/console/get-current-user-top-artists-and-tracks
2. Click on Get Token
3. Check user-top-read
4. Click on Request token
5. Copy your token

```
lein run
```
