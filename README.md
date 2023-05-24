# hover-bird
This project that is essentially an extension to the game flappy bird. We will be adding several other modes/themes to the game that will change the character, background, pipes, and music that is being played. 

-   It will have the same structure of the actual game, meaning the bird will still go through the pipes to get a higher score. A main difference about the functionality of the game is how high the speed is at the current of the level is at, high score, and time alive.

-   Another significant difference is the main menu, if we are able to get to that in the time we have been given. The main menu will consist of multiple themes that the user will be able to pick from, a default mode, jungle mode, futuristic mode, and a rainbow mode. Each mode will change the background/scenery, the character, the obstacles, and the music that is being played.

## Things to change/work on
-   Refactor characters class, to use the point class instead of the x and y coordinates. size for width & height
-   Figure out how to implement the music into the code correctly, and if seperrate classes for each need to be used.
-   For the pathing of the images, consider creating seperate folders inside images to have it more organized. Also create a variable that will be able to keep track of the pathing. Could approach it in a similar way as north/south pipes, which is done in the GameRunner class.

## How to run the code
```zsh
# initially you need to ensure that the run script is executable
chmod +x run

# run the program (1st it compiles, then runs java pointed at the main class aka Window)
./run
```


![view](./images/READMELoadingScreen.png)
![view](./images/READMESelection.png)
![view](./images/READMEFlappyBird.png)


