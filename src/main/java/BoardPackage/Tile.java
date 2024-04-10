package BoardPackage;
import Units.Knight;
public class Tile {

    private final int row;
    private final int col;
    private boolean isOccupied;

    private Knight player;

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
    public void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }

    public Knight getPlayer(){
        if(player == null){
            return null;
        }
        else{
            return player;
        }
    }

    public void removePlayerFromTile(){
        player = null;
    }

    private void setPlayer(Knight player){
        this.player = player;
    }

    public void placeUnitOnThisTile(Knight player) {
        setPlayer(player);
        setOccupied(true);
    }
}
