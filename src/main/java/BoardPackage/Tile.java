package BoardPackage;
import Units.Knight;
public class Tile {

    private final int row;
    private final int col;
    private boolean isOccupied;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.isOccupied = false;

    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public boolean isOccupied() {
        return isOccupied;
    }
    private void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }
}
