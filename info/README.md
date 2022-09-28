# How to run the program

Open up the project and press the run button for the test.java.

## Things to look out for:

-Project written in intellij.First player's turn is assigned randomly.All the events that have to do with the players throwing the dice to get a number have been automated. 
 You have to pick a number of months(rounds to be played) in the beginning of the game for the board to be able to load
in the background. There is no action limiter so play wisely! DON'T GRIEF :).When there is more 
than one rounds right after the payday the pawn disappears and re-appears where you land with the next 
die roll.Player position is on the start icon, so pos==0. When events occur, they happen before the update of the board, so it is only logical to observe 
a weird gap between moves when the board buffers. At the final month when the game ends  players info at the last move (the move where both months left are 0 for both players)
are not updated but the way the game calculates the outcome is based on the updated ones so there is a small confusion(TL;DR they are not updated visually because game ends and shuts down).
If you try and play with a pawn that has finished the game you get a NullPointerException.
There is no limit on the amount of mail cards you can draw so please follow the number given by the game:). For some reason all junit tests fail to run the game.It just starts and ends the moment you choose a month.
Move to specified spot doesn't fully work as desired.Buyer positions automatically sell the most worth-selling card.Have fun playing:)!