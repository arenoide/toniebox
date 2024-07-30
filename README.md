# Toniebox CLI

## Comments
Even though the task said ~2 hours, I spent really 2:40.
The time spent in this Readme is not counted on that time.

## How it works
In order to place a Tonie in the Toniebox, first you need to buy one. After that you can place the Tonie indicating its index in the list of available Tonies (starting from 0)
With "state" you can see the current state of the Toniebox like if there is a Tonie placed on it, information about the song being played and volume
The state of the songs are also saved so when the app is stopped and restarted, it will keep the state regarding the last second played for the Tonies (if the song is not finished)

## Requirements met
    - You can place and remove a Tonie in the Toniebox
    - Only one Tonie can be placed at a time
    - When the Tonie is placed it starts "playing" the "audio" content
    - Removing the Tonie stops the playback
    - The current playing position of the Tonie is stored when the Tonie is removed (only if the song is not finished)
    - You can increase and decrease the volume of the player
    - You can do a factory reset which will remove the storage.data file.

## Extras
I added an option to manage the Tonies that belong to the user.
It was easier for me in order to test things.This is also stored in a file, and it is not removed in the factory reset

## Things to improve regarding the requirements

### A lot of tests are missing
I started working with TDD for the MediaPlayer, but soon I realized that due to the time restriction I needed to focus more in the logic

### Tests with time duration
There is a test that waits 2 seconds in order to check that the MediaPlayer has finished the play. As a general rule I don't like waits in the tests.

### Song downloader
The song downloader should be completely implemented.
Without the help of other libraries or looking on the internet right now I am not sure if it is possible to implement a
Stream that gets saved when completely downloaded, so probably I would go into having the song being reproduced as a
stream and being download on parallel on the background

### Song downloader Tests
In order to reproduce the behaviour of a server with songs to be downloaded from it, I would use Wiremock.

It would also allow me to replicate server errors for example.

### Syntactic sugar
Although I have refactored a bit, the code is still missing some syntactic sugar in order to make it more readable 

### Handling of bad input data
There should be a better handling of input data, like "placeTonie" without saying which index to place

### CI/CD
I would add a Github workflow to test the code before being able to be merged to main.

### Static code analysis
I would include in the workflow the use of some tool like Sonarcloud to check for possible code smells and make the quality gate to block the PR until solved.

### Media player
The media player is created to replicate more or less the API of a real media player. This should be changed for a real media player.

## Other improvements
With time, I would have implemented some basic GUI instead of a CLI.  