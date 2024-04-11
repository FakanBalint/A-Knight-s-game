package Game;
import BoardPackage.*;
import Units.Knight;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    protected Knight currentKnight,
            otherKnight;

    protected Board board;

    public void Innit(){
        board = new Board();
        currentKnight = board.getPlayer1();
        otherKnight = board.getPlayer2();
    }

    public void PlayOnBoard(Board board){

        this.board = board;
        currentKnight = board.getPlayer1();
        otherKnight = board.getPlayer2();
    }

    public Knight getCurrentKnight(){
        return currentKnight;
    }
    public Knight getOtherKnight(){
        return otherKnight;
    }

    public void PlayGameConsole(){
        Scanner scanner = new Scanner(System.in);
        Innit();
        while (true) {
            // Print the board
            board.printBoard();
            // Get available moves for the current knight
            ArrayList<Tile> availableMoves = currentKnight.getAvailableMoves(board);

            //Check for a winner


            //Display the current player's available positions
            displayCurrentPlayerPossibleMovesOnConsole(board);

            // Prompt the player for their move
            Tile move = promptPlayerMoveToTile(scanner,board);

            // Check if the move is valid
            if (checkTileValidity(availableMoves, move)){
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

    public boolean tryToMoveKnight(Tile move, Board board, Knight knight){
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

    public  Board GetBoard(){
        return board;
    }

    protected boolean checkTileValidity(ArrayList<Tile> availableMoves, Tile move ){
        if (!availableMoves.contains(move)) {
            System.out.println("Invalid move. Please try again.");
            return true;
        }
        return false;
    }
    public void displayCurrentPlayerPossibleMovesOnConsole(Board board)
    {
        System.out.println("Available moves:");
        for (Tile tile : currentKnight.getAvailableMoves(board)) {
            if (!board.getTile(tile.getRow(), tile.getCol()).isOccupied()) {
                System.out.println("Row: " + tile.getRow() + ", Col: " + tile.getCol());
            }
        }
    }






    public void switchCurrentPlayer() {
        Knight tempKnight = currentKnight;
        currentKnight = otherKnight;
        otherKnight = tempKnight;

        if (currentKnight.getAvailableMoves(board).isEmpty()){
            gameOver(otherKnight,board);
        }
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
