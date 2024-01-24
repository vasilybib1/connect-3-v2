# My Personal Project

## What will the application do?

My plan is to design a simple connect three game. Something similar to candy crush or royal match. The user will be provided a grid with cells in which the user could choose a cell to move. If the moved cell ends up making a three in a row then the users score will increase. If it doesn't then the cell is returned to its original state. The goal of the game is to reach the highest score.

## Who will use it?

Anyone can use this app as its just a connect 3 type game.

## Why is this project of interest to me?

This project is of interest to me because I enjoyed playing candy crush and varieties of it however I always thought they were polluted with way too many features (power ups, different special items on the grid and so on) and also have way to many ads in them. So I want to create a simplified version of candy crush without the power ups and ads. It also seem challenging enough but not too far out of my reach to create.

## User Stories

- As a user, I want to be able to start a game and have a randomly generate grid appear (generate a grid, generate cells inside the grid)
- As a user, I want to be able to move a selected cells to make a three in a row match (check valid move and if so add points to score and add new cells from the top)
- As a user, I want to be able to see my current score in game (be able to see a live view of the score of the current game)
- As a user, I want to be able to step through all matches that happen from one single move (if there are two matches that happen update the board in step to let the user know there were multiple matches)

- As a user, I want to be able to quit and save the current game state (if i wish to)
- As a user, I want to be able to have a choice to load previous game or start a new one when the app starts up
- As a user, I want to have a preview of the score of the saved game


## Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by when launching the app you are asked if you would like load the previous save. If you press no it will generate one Grid (the Y) and fill it in with multiple cells (the X). It then process and removes and matches that might have happened when the grid was made so that you get a clean grid with no matches ready to play
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by playing the game and creating a match (3 or more of the same color in a row) this will remove those cells from the grid and shift the cells above down. After which new cells (the X) are added to the current grid (the Y)
- You can locate my visual component by starting the app and when being asked to load or not there is question icon that is also located in the data/image folder
- You can save the state of my application by playing the game and after pressing the close button you will be prompted to save the current game state
- You can reload the state of my application by starting the app and answering if you want to load or not the old saved state

## Phase 4 Task 3

Reflecting on the design of my application I could've had a few big improvements. Since i never worked with guis before I didn't know some of the quirks that came with it. For example I wanted and tried really hard to implement the pausing between consecutive matches. To do so I had to pause the canvas rendering loop between each consecutive match. However when I implemented a pause it would pause the entire canvas and wouldn't render the new consecutive matches. Even though the console would print out the new matches and even the command line interface would print them out. From further research I found out that there are some weird things with pausing threads. To implement this feature I would have to rewrite my entire main class to have a main thread which would then be paused if a consecutive match would be found.

Another design flaw that appeared was again to do with the graphical user interface. Since I haven't worked with it before it was hard to plan ahead of what design decisions would be best. This caused some cluttered function and work arounds to make things work. If I had more time I would rewrite my gui classes with this new knowledge and I could organize it better. I also could have better extensions. The Rectangle and the SelectionCell classes are almost identical so I could've wrote a class that could have the basic blocks and have those two extend it however I didn't notice this in the process and I only found this connection by reviewing my code when making the UML diagram. 

## Phase 4 Task 2

Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New grid made from scratchTue Nov 28 14:25:10 PST 2023
removed all matches on the boardTue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:10 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:12 PST 2023
removed one matchTue Nov 28 14:25:12 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:12 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:12 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:14 PST 2023
removed one matchTue Nov 28 14:25:14 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:14 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:14 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:14 PST 2023
removed one matchTue Nov 28 14:25:14 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:14 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:14 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:16 PST 2023
removed one matchTue Nov 28 14:25:16 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:16 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:16 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:16 PST 2023
removed one matchTue Nov 28 14:25:16 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:16 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:16 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:18 PST 2023
removed one matchTue Nov 28 14:25:18 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:18 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:18 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:18 PST 2023
removed one matchTue Nov 28 14:25:18 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:18 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:18 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:18 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:24 PST 2023
removed one matchTue Nov 28 14:25:24 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:24 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:24 PST 2023
New cell made with colors 1.0 0.0 1.0Tue Nov 28 14:25:24 PST 2023
New cell made with colors 0.0 0.0 1.0Tue Nov 28 14:25:29 PST 2023
removed one matchTue Nov 28 14:25:29 PST 2023
New cell made with colors 0.0 1.0 0.0Tue Nov 28 14:25:29 PST 2023
New cell made with colors 1.0 0.0 0.0Tue Nov 28 14:25:29 PST 2023
New cell made with colors 0.0 1.0 0.0
