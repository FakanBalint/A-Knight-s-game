package BoardPackage;

import Units.Knight;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void test_board_initializes_with_two_knights() {
        Board board = new Board();
        Tile player1Tile = board.getTile(0, 0);
        Tile player2Tile = board.getTile(7, 7);
        assertTrue(player1Tile.isOccupied());
        assertTrue(player2Tile.isOccupied());
        assertEquals(board.getPlayer1(), player1Tile.getPlayer());
        assertEquals(board.getPlayer2(), player2Tile.getPlayer());
    }

    @Test
    public void test_move_knight_outside_boundaries() {
        Board board = new Board();
        Knight player1 = board.getPlayer1();
        Tile invalidTile = new Tile(-1, -1);
        boolean moveResult = player1.move(invalidTile, board);
        assertFalse(moveResult);
    }

    @Test
    public void test_knight_set_position_updates_position_correctly() {
        // Arrange
        Board board = new Board();
        Knight knight = new Knight(3, 3);
        Tile newPosition = board.getTile(4, 4);

        // Act
        knight.setPosition(newPosition);

        // Assert
        assertEquals(newPosition, knight.getPosition());
    }


    @Test
    public void test_knights_move_updates_board_state() {
        Board board = new Board();
        Knight knight = board.getPlayer1();
        Tile initialPosition = knight.getPosition();
        Tile destinationTile = board.getTile(2, 1);

        boolean moveResult = knight.move(destinationTile, board);

        assertTrue(moveResult);
        assertEquals(destinationTile, knight.getPosition());
        assertTrue(destinationTile.isOccupied());
        assertEquals(knight, destinationTile.getPlayer());
    }


    @Test
    public void test_knights_get_available_moves() {
        Board board = new Board();
        Knight knight = board.getPlayer1();
        ArrayList<Tile> expectedMoves = new ArrayList<>();
        expectedMoves.add(board.getTile(1, 2));
        expectedMoves.add(board.getTile(2, 1));
        ArrayList<Tile> actualMoves = knight.getAvailableMoves(board);
        assertEquals(expectedMoves.size(), actualMoves.size());
        assertTrue(expectedMoves.containsAll(actualMoves));
    }

    @Test
    public void test_knight_moves_to_valid_positions() {
        Board board = new Board();
        Knight knight = board.getPlayer1();
        Tile initialPosition = knight.getPosition();
        ArrayList<Tile> availableMoves = knight.getAvailableMoves(board);
        assertTrue(availableMoves.size() > 0);

        // Move the knight to a valid position
        Tile destinationTile = availableMoves.get(0);
        boolean moveSuccessful = knight.move(destinationTile, board);
        assertTrue(moveSuccessful);

        // Check if the knight's position has changed
        assertNotEquals(initialPosition, knight.getPosition());
    }


    @Test
    public void test_knights_cannot_move_to_invalid_positions() {
        Board board = new Board();
        Knight knight = board.getPlayer1();
        Tile initialPosition = knight.getPosition();
        Tile destinationTile = board.getTile(2, 2);

        boolean moveResult = knight.move(destinationTile, board);

        assertFalse(moveResult);
        assertEquals(initialPosition, knight.getPosition());
    }

}