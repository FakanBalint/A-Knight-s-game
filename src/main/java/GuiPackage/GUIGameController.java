package GuiPackage;

import BoardPackage.Board;
import BoardPackage.Tile;
import Game.Game;
import game.State;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUIGameController extends Application {

    private Game game;
    private Board board;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        // Create the game
        game = new Game();
        game.init();

        board = game.getBoard();

        // Size of the board in tiles
        int BOARD_SIZE = 8;

        // Size of each tile in pixels
        int TILE_SIZE = 100;

        // Create the board tiles
        if (!generateBoard(TILE_SIZE, BOARD_SIZE, grid)) {
            return;
        }

        updateGUI(grid, board);
        Scene scene = new Scene(grid, BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Knight Game");
        primaryStage.show();
    }

    private boolean generateBoard(int TILE_SIZE, int BOARD_SIZE, GridPane grid) {
        try {
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    Tile tile = board.getTile(row, col);
                    VisualTile visualTile = new VisualTile(row, col, TILE_SIZE, tile);

                    // Add mouse click event handler to handle player moves
                    int finalRow = row;
                    int finalCol = col;
                    visualTile.setOnMouseClicked(event -> {
                        Tile targetTile = board.getTile(finalRow, finalCol);
                        if (game.isLegalMove(targetTile)) {
                            game.makeMove(targetTile);
                            System.out.println("Player moved to: (" + finalRow + ", " + finalCol + ")");
                            game.switchCurrentPlayer();
                            updateGUI(grid, board);
                        } else {
                            System.out.println("Player tried to move to: (" + finalRow + ", " + finalCol + ") and failed");
                        }
                        if (game.isGameOver()) {
                            displayGameOverMessage();
                        }


                    });

                    grid.add(visualTile, col, row);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void updateGUI(GridPane visualBoard, Board board) {
        ArrayList<Tile> availableMoves = game.getCurrentKnight().getAvailableMoves(board);
        for (Node node : visualBoard.getChildren()) {
            if (node instanceof VisualTile visualTile) {
                Tile tile = board.getTile(GridPane.getRowIndex(visualTile), GridPane.getColumnIndex(visualTile));
                visualTile.DisPlayTileProperties();

                if (availableMoves.contains(tile)) {
                    visualTile.UpdateAsAvailable();
                }
            }
        }

        displayPossibleMovesToConsole();
    }

    private void displayPossibleMovesToConsole() {
        game.displayCurrentPlayerPossibleMovesOnConsole();
    }

    private void displayGameOverMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");

        String message;
        if (game.getStatus() == State.Status.PLAYER_1_WINS) {
            message = "Player 1 wins!";
        } else if (game.getStatus() == State.Status.PLAYER_2_WINS) {
            message = "Player 2 wins!";
        } else if (game.getStatus() == State.Status.DRAW) {
            message = "It's a draw!";
        } else {
            return; // Game is not over
        }

        alert.setHeaderText(message);
        alert.setContentText("Thank you for playing!");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
