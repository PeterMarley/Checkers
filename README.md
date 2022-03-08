# 2-player Checkers
#### Current Version: V0.3

![Intro Screen](https://i.imgur.com/zZtaK3t.png)
![Player Name Entry Screen](https://i.imgur.com/KxykazR.png)
![Checkers Board Screen](https://i.imgur.com/FLgaUEn.png)

# Basics

This is a local 1v1 version of Checkers / English Draughts.

The program isn't fancy, it was a console printed version of the game (now upgraded to have a GUI). It pushed me up to and past the limits of my programming ability :)

It's my first GUI... so it's ... well, you'll see. It's also done in Java Swing which is dated. Will move it over to JavaFX presently.

I come back and add new concepts as I learn them.


# The Move Itself

The player selects a square (their own piece), then a destination square. The program will then execute the move if legal, providing feedback to the user. There is a silly winner declaration screen at the end.

##### What the move operation checks

- The direction must be appropriate for the player (up for black, down for white) - a king may move up or down
- The source piece MUST NOT be empty, NOR an opponents piece.
- The destination square MUST be empty
- The total distance of move MUST NOT exceed 2 squares
- If the move is a 2-square move:
    + The piece in the middle MUST BE an enemy piece
    + Otherwise the entire move is invalidated
- The movement of the piece must be directly diagonal
- Jump insistence:
    + If a player has an attack they must take it
    + If a player has multiple attacks, they must choose one of them
    + If a player makes an capturing move, and can then make another attack off the new position, they MUST do so in the same move (chaining)
- If the move is accepted overall and the destination square is the furthest row from their starting row AKA the king-row (top row for black, bottom row for white), the piece
  is upgraded to a king

The program then will do the actual move operation and move the piece, capture if needed, upgrade if needed

#### Win Conditions
There are two win/loss conditions:
- 1 - You lose if you have no pieces on the board
- 2 - You lose if you have no valid moves available

# The files
The program is made up of 2 Java packages.

## logic

##### Checkers.java
A static class, this is the home of the main(). Generally administrates the flow of the game and the GUI.
##### Enums.java
Contains the enumerated type constants for the program (color palette, test/ normal board setups, Icons, window dimensions, and directional Modifiers of the GamePiece 2D array instance field of GameBoard. 
##### GameBoard.java
A non-static class that represents the checker board. A lot of the program's logic lives here (reflected in how much larger this file is). The actual business creating and holding a GameBoard object, moving pieces around the board, and capturing pieces is done here.
##### GamePiece.java
A non-static class used to describe the GamePiece object; the actual checkers pieces. Here lives various methods for creating and editing GamePiece's and retrieving data from the GamePiece objects
##### Log.java (currently disabled while GUI is implemented)
A basic logging system that outputs a file suffixed with a YYYY-MM-DD HH-MM-SS date stamp from the initialisation of the program. Signifcant events are logged here, along with any errors that arise during game operation

## GUIs (all non-static)

##### JFrame_GUI.java
A child class of the JFrame Swing class, configured for this program.
##### JPanel_Intro.java, JPanel_PlayerNames.java, JPanel_Game.java and JPanel_Game_Squares.java
Children of the JPanel Swing class, configured for this program.
##### JButton_aSquare.java
A child class of the JButton Swing class, configured to represent a single square on the checker board.

# Credit for images used

### Checkers Pieces images
adapted by me using gimp from https://woodexpressions.com/wp-content/uploads/2016/07/258015Main2DB.jpg

### Checker board graphic from intro screen
https://content.mycutegraphics.com/graphics/play/checker-game-clip-art.png

### Winner GIF
https://giphy.com/gifs/barstoolsports-gambling-barstool-beadvised-ZcUGu59vhBGgbBhh0n

# Still to do
- Unit Testing
- GUI implementation in JavaFX
