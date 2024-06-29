package Units;

import BoardPackage.Board;
import BoardPackage.Tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

                //get position tests
    @Test
    public void test_returns_correct_initial_position_after_instantiation() {
        Knight knight = new Knight(0, 1);
        Tile initialPosition = knight.getPosition();
        assertEquals(0, initialPosition.getRow());
        assertEquals(1, initialPosition.getCol());
    }

    @Test
    public void test_returns_initial_position_when_not_moved() {
        Knight knight = new Knight(3, 3);
        Tile initialPosition = knight.getPosition();
        assertEquals(3, initialPosition.getRow());
        assertEquals(3, initialPosition.getCol());
    }

                //set position tests
    @Test
    public void test_set_position_to_new_tile() {
        Knight knight = new Knight(0, 0, "♞");
        Tile newTile = new Tile(2, 1);
        knight.setPosition(newTile);
        assertEquals(newTile, knight.getPosition());
        assertTrue(newTile.isOccupied());
        assertEquals(knight, newTile.getPlayer());
    }

    @Test
    public void test_set_position_to_null() {
        Knight knight = new Knight(0, 0, "♞");
        try {
            knight.setPosition(null);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Test passes
        }
    }

                //getAvailableMoves tests
    @Test
    public void test_knight_center_all_moves_available() {
        Board board = new Board();
        Knight knight = new Knight(4, 4);
        board.getTile(4, 4).placeUnitOnThisTile(knight);
        ArrayList<Tile> moves = knight.getAvailableMoves(board);
        assertEquals(8, moves.size());
    }

    @Test
    public void test_knight_corner_limited_moves() {
        Board board = new Board();
        Knight knight = new Knight(0, 0);
        board.getTile(0, 0).placeUnitOnThisTile(knight);
        ArrayList<Tile> moves = knight.getAvailableMoves(board);
        assertEquals(2, moves.size());
    }


                    //move tests
    @Test
    public void test_knight_moves_to_valid_empty_tile() {
        Board board = new Board();
        Knight knight = board.getPlayer1();
        Tile destinationTile = board.getTile(2, 1);
        boolean result = knight.move(destinationTile, board);
        assertTrue(result);
        assertEquals(destinationTile, knight.getPosition());
    }

    @Test
    public void test_knight_moves_outside_board_boundaries() {
        Board board = new Board();
        Knight knight = board.getPlayer1();
        Tile destinationTile = new Tile(-1, -1); // Invalid tile outside the board
        boolean result = knight.move(destinationTile, board);
        assertFalse(result);
        assertNotEquals(destinationTile, knight.getPosition());
    }

    @Test
    public void test_knight_updates_position_correctly() {
        Board board = new Board();
        Knight knight = board.getPlayer1();
        Tile initialPosition = knight.getPosition();
        Tile destinationTile = board.getTile(2, 1);

        boolean moveResult = knight.move(destinationTile, board);

        assertTrue(moveResult);
        assertEquals(destinationTile, knight.getPosition());
    }
}