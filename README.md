ReadFromXML
===========

For many web services that get rolled out to a large user base, it helps to leave configuration information stored in editable XML files. This will allow your less-technical users to deploy the app without having to edit any lines of Java and recompiling as a .war.

This repo contains files that handle the first part: reading in local XML data and storing it in newly-created beans. For the sake of the demo, this repo package also prints all bean information to the console. It should look like this:

There are 10 tracks in playlist #MKT376
Track: Escape
Artist: 3LAU
Duration: 4:36
*******
Track: Chicago is So Two Years Ago
Artist: Fall Out Boy
Duration: 3:20
*******
Track: Atmosphere
Artist: Kaskade
Duration: 3:52
*******
Track: El Scorcho
Artist: Weezer
Duration: 4:04
*******
Track: The General
Artist: Dispatch
Duration: 4:06
*******
Track: Go Go Gadget Flow
Artist: Lupe Fiasco
Duration: 4:11
*******
Track: Sundown - Original Mix
Artist: Chris Lake
Duration: 6:34
*******
Track: Levels - Radio Edit
Artist: Avicii
Duration: 3:20
*******
Track: Calling (Lose My Mind)
Artist: Sebastian Ingrosso, Alesso
Duration: 3:26
*******
Track: Make Me Proud
Artist: Drake, Nicki Minaj
Duration: 3:40
*******

Sidenote: if you want to hear the tracks themselves, check out the associated Spotify playlist at http://open.spotify.com/user/drew.depriest/playlist/3bM9Mnhy3eoki2j0jdTd7L.
