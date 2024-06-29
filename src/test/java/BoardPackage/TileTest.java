package BoardPackage;

import Units.Knight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    public void test_remove_player_from_occupied_tile() {
        Tile tile = new Tile(0, 0);
        Knight knight = new Knight(1, 1);
        tile.placeUnitOnThisTile(knight);
        assertTrue(tile.isOccupied());
        assertNotNull(tile.getPlayer());
        tile.removePlayerFromTile();
        assertNull(tile.getPlayer());
        assertTrue(tile.isOccupied());
    }

    @Test
    public void test_correctly_assigns_knight_to_tile() {
        Board board = new Board();
        Tile tile = new Tile(3, 3);
        Knight knight = new Knight(3, 3);
        tile.placeUnitOnThisTile(knight);
        assertTrue(tile.isOccupied());
        assertEquals(knight, tile.getPlayer());
    }
}