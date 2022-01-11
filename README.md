# 2-player Checkers
#### Current Version: V0.9.2

![The Game](https://i.imgur.com/IBmU5fH.png)

# Basics

This is a local 1v1 version of Checkers / English Draughts.

The program isn't fancy, it is a console printed version of the game but it pushed me up to and past the limits of my programming ability :)

I come back and add new concepts as I learn them

The game starts and first prompts both players to input their name. Immediately afterward the game starts.

the text prompt can accept several different inputs, but typing "h" or "help" as prompted and pressing enter will show you the help menu

The game has a basic logging system also. Not much exciting there (at least its not as exciting as Log4Shell xD )

##### The following inputs are accepted:

###### Menu Selections
- "h" or "help" - shows the help menu
- "q" or "quit" - exits the program gracefully
- "c" or "cancel" - cancels the current move and allows the player to start again
- "cap" or "captured" - print captured pieces list for both players
These 4 inputs can be entered at any time for the desired effect

###### Selection of Pieces/ Squares 
- "A1", "C3", "H8" etc - A board coordinate. The first selection is of the players piece, and the second is a selection of their desired destination square.

# The Move Itself

The program will get two GameBoard GamePiece 2D array field coordinates (row and column indices) from player

##### Once the player has selected two positions, the program will execute the move itself. It will check all the generic move conditions and eventualities:
- The direction must be appropriate for the player (up for black, down for white) - a king may move up or down
- The source piece MUST NOT be empty
- The destination square MUST be empty
- The total distance of move MUST NOT exceed 2 squares
- If the move is a 2-square move:
    + The piece in the middle MUST BE an enemy piece
    + Otherwise the entire move is invalidated
- The movement vector of the piece must be directly diagonal
- Jump insistence:
    + If a player has an attack they must take it
    + If a player has multiple attacks, they must choose one of them
    + If a player makes an attack, and can then make another attack off the new position, they MUST do so in the same move (chaining)
- If the move is accepted overall and the destination square is the furthest row from their starting row AKA the king-row (top row for black, bottom row for white), the piece
  is upgraded to a king

The program then will do the actual move operation and move the piece, capture if needed, upgrade if needed

#### Win Conditions
There are two win/loss conditions:
- 1 - You lose if you have no pieces on the board
- 2 - You lose if you have no valid moves available

# The files
The program is made up of 4 java classes.

##### Controller.java
Made up of only static methods, this is the home of the main(). Generally administrates the flow of the game. The main game loop lives here and from it all the other methods are invoked.
##### Enums.java
Contains the enumerated types for directional Modifier (s) of the GamePiece 2D array instance field of GameBoard. Also contains the
various BoardSetup types for the GameBoard constructor
##### GamePiece.java
A class used to describe the GamePiece object, the actual game pieces themselves. Here lives various methods for creating and editing GamePiece's and retriving data from the class
##### GameBoard.java
This beefy boy is where a lot of the program's logic lives (reflected in how much larger this file is). The actual business creating and holding a GameBoard oject, moving pieces around the board,
and capturing pieces is done here.
##### Log.java
A basic logging system that outputs a file suffixed with a YYYY-MM-DD HH-MM-SS date stamp from the initialisation of the program. Signifcant events are logged here, along with any errors that arise during game operation

# Still to do
- Unit Testing
- GUI implementation
