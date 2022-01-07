edit

# 2-player Checkers
### Video Demo: https://www.youtube.com/watch?v=p2JHpilPolw
### Description:

#### Current Version: V0.9

#### Basics

This is a local co-op version of Checkers (English Draughts).

The program isn't fancy graphically but it pushed me up to and past the limits of my programming ability :)

The game starts and first prompts both players to input their name. Immediately afterward the game starts.

the text prompt can accept several different inputs, put typing "h" or "help" as prompted and pressing enter will show you the help menu

##### The following inputs are accepted:

- "h" or "help" - shows the help menu
- "q" or "quit" - exits the program gracefully
- "c" or "cancel" - cancels the current move and allows the player to start again
These 3 inputs can be entered at any time for the desired effect

- "A1", "C3", "H8" etc - A board coordinate. The first selection is of the players piece, and the second is a selection of their desired destination square.

#### The Move Itself

The program will validate that both coordinates are on the board, that the first coordinate is the current players piece, and that the second coordinate is empty.

Furthermore the second coordinate is checked that it is in the correct direction for the player (up for black, down for white), that is an acceptable distance from the source square
(meaning it is 2 away with an opponents piece in the middle; or that is is directly adjacent). Together the two coordinates are used to check if the move is directly diagonal.

##### Once both coordinates are accepted and validated, the program will execute the move itself. It will check all the generic move conditions and eventualities:
- The direction must be appropriate for the player (up for black, down for white) - a king may move up or down
- The source piece MUST NOT be empty
- The destination square MUST be empty
- The total distance of move MUST NOT exceed 2 squares
- The movement vector of the piece must be directly diagonal
- If the move is a 2-square move:
    + The piece in the middle MUST BE an enemy piece
    + Otherwise the entire move is invalidated
- Jump insistence:
    + If a player has an attack they must take it
    + If a player has multiple attacks, they must choose one of them
    + If a player makes an attack, and can then make another attack off the new position, they may do so in the same move (chaining)
- If the move is accepted overall and the destination square is the furthest row from their starting row AKA the king-row (top row for black, bottom row for white), the piece
  is upgraded to a king

The program then will do the actual move operation and move the piece, capture if needed, upgrade if needed

#### Win Conditions
There are two win/loss conditions:
- 1 - You lose if you have no pieces on the board
- 2 - You lose if you have no valid moves available

#### The files
The program is made up of 4 java classes.

##### Controller.java
Made up of only static methods, this is the home of the main(). Generally administrates the flow of the game. The main game loop lives here and from it all the other methods are invoked.
##### GamePiece.java
A class used to describe the GamePiece object, the actual game pieces themselves. Here lives various methods for creating and editing GamePiece's and retriving data from the class
##### GameBoard.java
This beefy boy is where a lot of the program's logic lives (reflected in how much larger this file is). The actual business creating and holding a GameBoard oject, moving pieces around the board,
and capturing pieces is done here.
##### Log.java
A basic logging system that outputs a file suffixed with a YYYY-MM-DD HH-MM-SS date stamp from the initialisation of the program. Signifcant events are logged here, along with any errors that arise during game operation

# Still to do
- Finish implementation of GameBoard.checkPlayerHasValidMoves()
- Unit Testing
- GUI implementation
