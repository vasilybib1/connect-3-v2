# Connect 3 V2

## Description

Decided to revisit the connect 3 game (https://github.com/vasilybib1/connect-3) and fix the unfinished issues with it. This version is hopefully going to have a visual interface and mouse support. With many of missing features hopefully implemented such as animations, storing high scores, preventing illegal moves.

The design of this app will be done in three parts. The logic part (handling the logic of the game), the cli part (implementing the game with the most basic visuals), the gui part (after the game has been finished using the cli interface, port it to some gui. Since the logic part is seperate from the cli visualisation it should be simple to port it)

## To Run

`git clone https://github.com/vasilybib1/connect-3-v2`

`cd connect-3-v2`

`mvn package`

`java -cp target/connect-v2-1.0-SNAPSHOT.jar main.Main`
