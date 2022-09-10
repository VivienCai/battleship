# Battleship
### _Vivien Cai, Jiaan Li, Sarina Li_

Welcome to the offical repository of our battleship project, glad you're here! Let's get into the Nitty Gritty.

## Features

- Fully Functional AI (Hits using probability density and hunts hit points)
- Player vs AI Mode (Hard and Easy!)
- Graphical User Interface, easy to use and navigate
- Terminal Based Game
- Music and SFX
- File Saving, reload past saved games

> "We wanted to create something that the player
> could enjoy and easily navigate." - VSJ 

## Download + Usage

Here's the *easiest* way to run our program. 

1. Go the top in the green button that says "Code" on it with a dropdown. You can either choose to download a zip file of the code or fork us :D
2. Unzip the zip file or set it up in GitHub Desktop. Whatever works for you!
3. Drag into your IDE.
4. Navigate to ```StartGame.java```. The directory in case you don't know where it is: ```src/StartGame.java```
5. If you want to run the text based terminal game, head over to ```Main.java```. Directory: ```src/Main.java``` 
5. Run the ```main``` and you're good! Enjoy!

If you want an insider peak on the code, it is now open to anyone and is archived! [Public repository][dill]
 on GitHub.

## Logic 

Generalized breakdown of our codebase:

### Hitting

We used a probability density strategy that focuses on every single point that all the ships alive can possibly be in. Sounds brutal but it really isn't as bad as it sounds. 

We used the idea that when a ship is placed, it occupies the number of squares that is its size. Moreover, it will only occupy the squares where it is placed! This means that these certain squares all have 1 possible orientation. Now we can repeat this proccess for every. single. ship. in the vertical and horizontal. Then, we get a grid that looks something like this: 

![alt text](https://github.com/ushering123/ics4u_battleship/blob/main/battleship6.png?raw=true)
This is the probability grid for the very start of the game where all 5 ships are alive. We can see that the middle has an *astounding* 34 possible orientations!


Now the AI has some numerical values to work off of: all misses are marked as 0 and the process continues with all the ships currently alive. This allows for the AI to hit the squares with the highest probability of a ship being in that square. 

### Hunting

Hunting was the hardest part of this project!! We put our brains together and from game strategy we know that you can only hit horizontally or vertically to the original point right? 

That was one consideration. Our main logic looked something like this: 

![alt text](https://github.com/ushering123/ics4u_battleship/blob/main/Screen%20Shot%202022-01-10%20at%2000.07.10.png?raw=true)

In this case, we used the carrier to demonstrate that you can have 5 possible orientations of this ship in those squares. Obviously you would hit there first!

Some of the considerations for this summing technique were quite difficult to get around:
1. If we hit a ship that was unique from the current ship we were hunting, we can to ensure that we didn't accidentally sink a ship we weren't hunting. It would mess everything up!
2. If we missed in a certain direction, we could not sum in that direction. Why would we hit where we missed?
3. If we hit in an axis twice and they both landed, we are guarenteed the direction/orientation of the ship. We should only hunt in this direction.
4. We can't hit the original hit point, we would waste a turn!

All of these considerations were sorted out and were our edge cases. In the end, we got it working. You can read more about it in our code files!

### Placing

We chose to place most of our ships at the sides of the board. Looking at our probability grid made it obviously that the sides of the grid have the least possible chance of being hit. 

Of course some flaws of this strategy is thta if we are playing against a person or an AI that goes for the sides, it would actually work against us. However, we coded our AI in the case were we play against the most optimized hitting algorithm. 

### GUI Features

Oh boy. These were difficult at first but eventually we got them down. We had to use some threading to ensure that music could play in the background, we had some issues where the game would pause to finish a song before continuing the execution. 

We also spend some time figuring out our timer: that was a hassle but in the end after some digging and documentation reading, we found what was right for us! 

### File Saving

This one was done by saving *everything* into files. Literally everything. Separated into ```info.txt``` and ```Grids.txt```, we were able to track everything we needed to load up games between turns. 

This one we are really proud of!

## Screenshots

Some peeks of our game :D

### Home Screen
![alt text](https://github.com/ushering123/ics4u_battleship/blob/main/battleship1.png?raw=true)

### Picking Who Goes First
![alt text](https://github.com/ushering123/ics4u_battleship/blob/main/battleship2.png?raw=true)

### Game Boards
![alt text](https://github.com/ushering123/ics4u_battleship/blob/main/battleship5.png?raw=true)

Play our game to see our endings :D (Awesome SFX with songs like Woman - Doja Cat, Superbass - Nicki Minaj, Our Love - Curtis Harding)


## Feedback

Let us know what you think! Messages and inbox are always open :)



   [dill]: <https://github.com/ushering123/ics4u_battleship>
