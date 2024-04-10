package Units;
import BoardPackage.*;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

public class Knight {

    private Tile position;
    private String IconNameUniCode;

    public String getIconNameUniCode() {
        return IconNameUniCode;
    }

    public void setIconNameUniCode(String iconNameUniCode) {
        IconNameUniCode = iconNameUniCode;
    }

    public Knight(int row, int col, String IconNameUniCode) {
        this.IconNameUniCode = IconNameUniCode;
        position = new Tile(row, col);
    }
    public Knight(int row, int col) {
        position = new Tile(row, col);
    }

    public Tile getPosition() {
        return position;
    }

    public void setPosition(Tile newPosition) {
        position = newPosition;
        position.placeUnitOnThisTile(this);
    }

    public ArrayList<Tile> getAvailableMoves(Board board) {
        ArrayList<Tile> moves = new ArrayList<Tile>();
        int row = position.getRow();
        int col = position.getCol();
        addIfValidMove(row - 2, col - 1, moves, board);
        addIfValidMove(row - 2, col + 1, moves, board);
        addIfValidMove(row - 1, col - 2, moves, board);
        addIfValidMove(row - 1, col + 2, moves, board);
        addIfValidMove(row + 1, col - 2, moves, board);
        addIfValidMove(row + 1, col + 2, moves, board);
        addIfValidMove(row + 2, col - 1, moves, board);
        addIfValidMove(row + 2, col + 1, moves, board);
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
