package BoardPackage;
import Units.Knight;
import lombok.Getter;

import java.io.Serializable;

public class Tile implements Serializable {

    @Getter
    private final int row;
    @Getter
    private final int col;
    private boolean isOccupied;

    private Knight player;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.isOccupied = false;

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
