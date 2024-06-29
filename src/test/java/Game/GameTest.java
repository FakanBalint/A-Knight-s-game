package Game;

import BoardPackage.Board;
import BoardPackage.Tile;
import Units.Knight;
import game.State;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void test_attempt_illegal_move_and_verify_rejection() {
        Game game = new Game();
        game.init();
        Board board = game.getBoard();
        Knight currentKnight = game.getCurrentKnight();

        Tile illegalMove = board.getTile(0, 1); // An illegal move for the knight at (0, 0)

        assertFalse(game.isLegalMove(illegalMove));
    }

    @Test
    public void test_make_legal_move_and_verify_knights_new_position() {
        Game game = new Game();
        game.init();
        Board board = game.getBoard();
        Knight player1 = game.getCurrentKnight();
        Tile initialPosition = player1.getPosition();
        ArrayList<Tile> availableMoves = player1.getAvailableMoves(board);
        Tile destinationTile = availableMoves.get(0); // Assuming the first available move is legal

        game.makeMove(destinationTile);

        assertEquals(destinationTile, player1.getPosition());
    }


    @Test
    public void test_switch_current_player_after_valid_move() {
        Game game = new Game();
        game.init();
        Knight initialCurrentKnight = game.getCurrentKnight();
        Knight initialOtherKnight = game.getOtherKnight();

        // Get available moves for current player
        ArrayList<Tile> availableMoves = initialCurrentKnight.getAvailableMoves(game.getBoard());
        // Make a valid move
        Tile validMove = availableMoves.get(0);
        game.makeMove(validMove);

        // Check if the current player has switched
        Knight newCurrentKnight = game.getCurrentKnight();
        Knight newOtherKnight = game.getOtherKnight();

        assertEquals(initialCurrentKnight.getIconNameUniCode(), newOtherKnight.getIconNameUniCode());
        assertEquals(initialOtherKnight.getIconNameUniCode(), newCurrentKnight.getIconNameUniCode());
    }

    @Test
    public void test_save_and_load_board_state() {
        Game game = new Game();
        game.init();
        game.saveBoard();
        Board loadedBoard = Board.loadBoardFromFile("board.txt");
        assertNotNull(loadedBoard);
        assertEquals(game.getBoard().getPlayer1().getPosition().getRow(), loadedBoard.getPlayer1().getPosition().getRow());
        assertEquals(game.getBoard().getPlayer1().getPosition().getCol(), loadedBoard.getPlayer1().getPosition().getCol());
        assertEquals(game.getBoard().getPlayer2().getPosition().getRow(), loadedBoard.getPlayer2().getPosition().getRow());
        assertEquals(game.getBoard().getPlayer2().getPosition().getCol(), loadedBoard.getPlayer2().getPosition().getCol());
    }







}