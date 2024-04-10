package Game;

import BoardPackage.Board;
import BoardPackage.Tile;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class KnightGameGUI extends Application {

    private final int BOARD_SIZE = 8; // Assuming an 8x8 board for simplicity
    private final int TILE_SIZE = 100; // Size of each tile in pixels

    private Game game;

    private Board board;

    @Override
    public void start(Stage primaryStage) {


        GridPane grid = new GridPane();

        // Create the game
        game = new Game();
        game.Innit();

        board = game.GetBoard();

        // Create the board tiles
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill((row + col) % 2 == 0 ? Color.LIGHTGRAY : Color.WHITE); // Alternating colors for tiles
                tile.setStroke(Color.BLACK); // Border color

                // Add mouse click event handler to handle player moves
                int finalRow = row;
                int finalCol = col;
                tile.setOnMouseClicked(event -> {
                    // Handle player move here, for example:

                    game.tryToMoveKnight(board.getTile(finalRow, finalCol), board, game.currentKnight);
                    System.out.println("Player moved to: (" + finalRow + ", " + finalCol + ")");

                    // Update the GUI
                    updateGUI(grid,board);
                });

                grid.add(tile, col, row);
            }
        }
        updateGUI(grid,board);
        Scene scene = new Scene(grid, BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Knight Game");
        primaryStage.show();
    }

    public void updateGUI(GridPane VisualBoard,Board board)
    {
        for (Node node : VisualBoard.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle visualTile = (Rectangle) node;
                Tile tile = board.getTile(GridPane.getRowIndex(visualTile), GridPane.getColumnIndex(visualTile));

                if(tile.isOccupied())
                {
                    visualTile.setFill(Color.RED);
                }else{
                    visualTile.setFill((GridPane.getRowIndex(visualTile) + GridPane.getColumnIndex(visualTile)) % 2 == 0 ? Color.LIGHTGRAY : Color.WHITE);
                }

                ArrayList<Tile> availableMoves = game.currentKnight.getAvailableMoves(board);
                if(availableMoves.contains(tile))
                {
                    visualTile.setFill(Color.LIGHTGREEN);
                }

                game.displayCurrentPlayerPossibleMoves(board);

            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
