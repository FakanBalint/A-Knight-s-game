package GuiPackage;

import BoardPackage.Board;
import BoardPackage.Tile;
import Game.Game;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
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
        game.Innit();

        board = game.getBoard();
            // Size of the board in tiles
        int BOARD_SIZE = 8;

            // Size of each tile in pixels
        int TILE_SIZE = 100;

        // Create the board tiles
        if(!GenerateBoard(TILE_SIZE,BOARD_SIZE,grid)){
            return;
        }
        updateGUI(grid,board);
        Scene scene = new Scene(grid, BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Knight Game");
        primaryStage.show();
    }


    private Boolean GenerateBoard(int TILE_SIZE,int BOARD_SIZE ,GridPane grid){
        try {
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    Tile tile = board.getTile(row, col);
                    VisualTile visualTile = new VisualTile(row, col, TILE_SIZE, tile);
                    // Add mouse click event handler to handle player moves
                    int finalRow = row;
                    int finalCol = col;
                    visualTile.setOnMouseClicked(event -> {
                        if(game.tryToMoveKnight(board.getTile(finalRow, finalCol), board, game.getCurrentKnight())){
                            System.out.println("Player moved to: (" + finalRow + ", " + finalCol + ")");
                            game.switchCurrentPlayer();
                        }else{
                            System.out.println("Player Tried to moved to: (" + finalRow + ", " + finalCol + ") and failed");
                        }

                        // Update the GUI
                        updateGUI(grid,board);
                    });

                    grid.add(visualTile, col, row);
                }
            }
        } catch (java.lang.Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void updateGUI(GridPane VisualBoard,Board board)
    {
        ArrayList<Tile> availableMoves = game.getCurrentKnight().getAvailableMoves(board);
        for (Node node : VisualBoard.getChildren()) {
            if (node instanceof VisualTile visualTile) {

                Tile tile = board.getTile(GridPane.getRowIndex(visualTile), GridPane.getColumnIndex(visualTile));

                visualTile.DisPlayTileProperties();

                if(availableMoves.contains(tile))
                {
                    visualTile.UpdateAsAvailable();
                }

            }
        }

        DisplayPossibleMovesToConsole(board);

    }

    private void DisplayPossibleMovesToConsole(Board board){
        game.displayCurrentPlayerPossibleMovesOnConsole(board);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
