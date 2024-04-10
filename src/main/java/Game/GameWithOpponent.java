package Game;

import BoardPackage.Board;
import BoardPackage.Tile;

import java.util.ArrayList;
import java.util.Scanner;

public class GameWithOpponent extends Game {

    protected AiPlayer aiPlayer;
    public GameWithOpponent(AiPlayerType type) {
        super();

        this.aiPlayer = new AiPlayer(type);
    }

    private  int turnCounter;

    @Override
    public void PlayGame() {


        // Data
        Scanner scanner = new Scanner(System.in);
        board = new Board();
        turnCounter = 0;
        currentKnight = board.getPlayer1();
        otherKnight = board.getPlayer2();

        while (true) {

            TakeTurn(scanner);

            if (checkForWinner()) break;

            turnCounter++;
        }

    }

    private void TakeTurn(Scanner scanner) {
        if (turnCounter % 2 == 0) {
           boolean success = PlayerTakeTurn(scanner);
           if (!success) {
               turnCounter--;
           }
        } else {
            AiTakeTurn();
        }
    }


    boolean checkForWinner() {
        if (currentKnight.getAvailableMoves(board).isEmpty()) {
            gameOver(otherKnight, board);
            return true;
        }
        else if (otherKnight.getAvailableMoves(board).isEmpty()) {
            gameOver(currentKnight, board);
            return true;
        }

        return false;
    }



    private boolean PlayerTakeTurn(Scanner scanner) {

        // Print the board
        board.printBoard();
        // Get available moves for the current knight
        ArrayList<Tile> availableMoves = currentKnight.getAvailableMoves(board);

        //Display the current player's available positions
        displayCurrentPlayerPossibleMoves(board);

        // Prompt the player for their move
        Tile move = promptPlayerMoveToTile(scanner,board);

        // Check if the move is valid
        if (!checkTileValidity(availableMoves,move)){
            return false;
        }

        tryToMoveKnight(move, board, currentKnight);



        return true;
    }
    private void AiTakeTurn() {

            Tile aiMove = aiPlayer.calculateNextMove(board, otherKnight);
            tryToMoveKnight(aiMove, board, otherKnight);

    }
}

