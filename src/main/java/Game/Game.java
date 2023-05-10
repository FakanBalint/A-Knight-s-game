package Game;
import BoardPackage.*;
import Units.Knight;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Knight currentKnight,otherKnight;

    public void PlayGame(){
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

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
            if (!tryToMoveKnight(move,board)){
                continue;
            }

            // Swap the current player
            switchCurrentPlayer();
        }
    }

    private boolean tryToMoveKnight(Tile move, Board board){
        if (!currentKnight.move(move, board)) {
            System.out.println("Invalid move. Please try again.");
            return false;
        }
        return true;
    }


    private Tile promptPlayerMoveToTile(Scanner scanner, Board board){
        System.out.println("Enter your move (row column): ");
        int moveRow = scanner.nextInt();
        int moveCol = scanner.nextInt();
        Tile move = board.getTile(moveRow, moveCol);

        return move;
    }


    private boolean checkTileValidity(ArrayList<Tile> availableMoves, Tile move ){
        if (!availableMoves.contains(move)) {
            System.out.println("Invalid move. Please try again.");
            return  false;
        }
        return true;
    }
    private void displayCurrentPlayerPossibleMoves(Board board)
    {
        System.out.println("Available moves:");
        for (Tile tile : currentKnight.getAvailableMoves(board)) {
            if (!board.getTile(tile.getRow(), tile.getCol()).isOccupied()) {
                System.out.println("Row: " + tile.getRow() + ", Col: " + tile.getCol());
            }
        }

    }


    private void switchCurrentPlayer() {
        Knight tempKnight = currentKnight;
        currentKnight = otherKnight;
        otherKnight = tempKnight;
    }

    private static void gameOver(Knight winner,Board board)
    {
        System.out.println("Game over!");
        if (winner == board.getPlayer1() ){
            System.out.println("Player1 won");
        }
        else {
            System.out.println("Player2 won");
        }



    }
}
