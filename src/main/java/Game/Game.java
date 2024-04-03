package Game;
import BoardPackage.*;
import Units.Knight;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    protected Knight currentKnight,otherKnight;

    protected Board board;

    public void PlayGame(){
        Scanner scanner = new Scanner(System.in);
         board = new Board();

        // Play the game
        currentKnight = board.getPlayer1();
        otherKnight = board.getPlayer2();

        while (true) {
            // Print the board
            board.printBoard();
            // Get available moves for the current knight
            ArrayList<Tile> availableMoves = currentKnight.getAvailableMoves(board);

            //Check for a winner
            if ((long) availableMoves.size() == 0){
                gameOver(otherKnight,board);
                break;
            }

            //Display the current player's available positions
            displayCurrentPlayerPossibleMoves(board);

            // Prompt the player for their move
            Tile move = promptPlayerMoveToTile(scanner,board);

            // Check if the move is valid
            if (!checkTileValidity(availableMoves,move)){
                continue;
            }

            // Move the knight to the new tile
            if (!tryToMoveKnight(move,board,currentKnight)){
                continue;
            }

            // Swap the current player
            switchCurrentPlayer();
        }
    }

    protected boolean tryToMoveKnight(Tile move, Board board, Knight knight){
        if (!knight.move(move, board)) {
            System.out.println("Invalid move. Please try again.");
            return false;
        }
        return true;
    }


    protected Tile promptPlayerMoveToTile(Scanner scanner, Board board){
        System.out.println("Enter your move (row column): ");
        int moveRow = scanner.nextInt();
        int moveCol = scanner.nextInt();
        return board.getTile(moveRow, moveCol);
    }


    protected boolean checkTileValidity(ArrayList<Tile> availableMoves, Tile move ){
        if (!availableMoves.contains(move)) {
            System.out.println("Invalid move. Please try again.");
            return  false;
        }
        return true;
    }
    protected void displayCurrentPlayerPossibleMoves(Board board)
    {
        System.out.println("Available moves:");
        for (Tile tile : currentKnight.getAvailableMoves(board)) {
            if (!board.getTile(tile.getRow(), tile.getCol()).isOccupied()) {
                System.out.println("Row: " + tile.getRow() + ", Col: " + tile.getCol());
            }
        }

    }




    protected void switchCurrentPlayer() {
        Knight tempKnight = currentKnight;
        currentKnight = otherKnight;
        otherKnight = tempKnight;
    }



    protected static void gameOver(Knight winner,Board board)
    {
        System.out.println("Game over!");
        if (winner == board.getPlayer1() ){
            System.out.println("Player1 won");
            board.printBoard();
        }
        else {
            System.out.println("Player2 won");
            board.printBoard();
        }

    }


}
