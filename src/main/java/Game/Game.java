package Game;

import BoardPackage.*;
import Units.Knight;
import game.BasicState;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Scanner;

public class Game implements BasicState<Tile> {
    @Getter
    private Knight currentKnight, otherKnight;
    @Getter
    private Board board;
    private Status status;

    public Game() {
        this.status = Status.IN_PROGRESS;
    }

    public void init() {
        board = new Board();
        currentKnight = board.getPlayer1();
        otherKnight = board.getPlayer2();
    }

    public void loadBoard(String fileName) {
        board = Board.loadBoardFromFile(fileName);
        playOnBoard(board);
    }

    public void saveBoard() {
        board.saveBoardToFile("board.txt");
    }

    public void playOnBoard(Board board) {
        this.board = board;
        currentKnight = board.getPlayer1();
        otherKnight = board.getPlayer2();
    }

    public void playGameConsole() {
        Scanner scanner = new Scanner(System.in);
        init();
        while (!isGameOver()) {
            board.printBoard();
            displayCurrentPlayerPossibleMovesOnConsole();
            Tile move = promptPlayerMoveToTile(scanner, board);

            if (isLegalMove(move)) {
                makeMove(move);
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
        gameOver();
    }

    @Override
    public boolean isLegalMove(Tile move) {
        ArrayList<Tile> availableMoves = currentKnight.getAvailableMoves(board);
        return availableMoves.contains(move);
    }

    @Override
    public void makeMove(Tile move) {
        if (Status.IN_PROGRESS != status) {
            return;
        }
        currentKnight.move(move, board);
        if (otherKnight.getAvailableMoves(board).isEmpty()) {
            status = otherKnight == board.getPlayer1() ? Status.PLAYER_2_WINS : Status.PLAYER_1_WINS;
            gameOver();
            return;
        }
        switchCurrentPlayer();
    }

    protected Tile promptPlayerMoveToTile(Scanner scanner, Board board) {
        System.out.println("Enter your move (row column): ");
        int moveRow = scanner.nextInt();
        int moveCol = scanner.nextInt();
        return board.getTile(moveRow, moveCol);
    }

    public void displayCurrentPlayerPossibleMovesOnConsole() {
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
    }

    protected void gameOver() {
        System.out.println("Game over!");
        if (status == Status.PLAYER_1_WINS) {
            System.out.println("Player 1 won!");
        } else if (status == Status.PLAYER_2_WINS) {
            System.out.println("Player 2 won!");
        } else {
            System.out.println("It's a draw!");
        }
        board.printBoard();
    }

    @Override
    public Player getNextPlayer() {
        return currentKnight == board.getPlayer1() ? Player.PLAYER_1 : Player.PLAYER_2;
    }

    @Override
    public boolean isGameOver() {
        return status != Status.IN_PROGRESS;
    }

    @Override
    public Status getStatus() {
        return status;
    }
}
