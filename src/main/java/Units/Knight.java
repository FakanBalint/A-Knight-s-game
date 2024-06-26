package Units;
import BoardPackage.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
public class Knight implements Serializable {

    private Tile position;
    @Setter
    private String IconNameUniCode;

    public Knight(int row, int col, String IconNameUniCode) {
        this.IconNameUniCode = IconNameUniCode;
        position = new Tile(row, col);
    }
    public Knight(int row, int col) {
        position = new Tile(row, col);
    }

    public void setPosition(Tile newPosition) {
        position = newPosition;
        position.placeUnitOnThisTile(this);
    }

    public ArrayList<Tile> getAvailableMoves(Board board) {
        int[] rows = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] cols = {-1, 1, -2, 2, -2, 2, -1, 1};
        ArrayList<Tile> moves = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();

        for (int i = 0; i < 8; i++) {
            addIfValidMove(row + rows[i], col + cols[i], moves, board);
        }

        return moves;
    }

    private void addIfValidMove(int row, int col, ArrayList<Tile> moves, Board board) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            Tile tile = board.getTile(row, col);
            if (!tile.isOccupied()) {
                moves.add(tile);
            }
        }
    }

    public boolean move(Tile destinationTile, Board board) {
        ArrayList<Tile> availableMoves = getAvailableMoves(board);
        if (availableMoves.contains(destinationTile)) {
            position.removePlayerFromTile();
            position = destinationTile;
            position.placeUnitOnThisTile(this);
            return true;
        }
        return false;
    }

}
