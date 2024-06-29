package GuiPackage;

import BoardPackage.Tile;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import lombok.Getter;


public class VisualTile extends Button {

    @Getter
    private Tile tile;

    int row;
    int col;

    public VisualTile(int row, int col, int size, Tile tile) {
        this.tile = tile;
        this.row = row;
        this.col = col;

        setMinSize(size, size);
        setMaxSize(size, size);
        setPrefSize(size, size);

    }


    public void DisPlayTileProperties()
    {


        if(isOccupied())
        {
            setStyle("-fx-background-color: red;-fx-border-color: black;-fx-border-width: 2px;");
            removePlayerInfo();
        }
        else
        {
            UpdateBaseColor();
        }
        if(tile.getPlayer() != null)
        {
            addPlayerInfo();
        }
    }

    private void addPlayerInfo() {
        setStyle("-fx-background-color: lightblue;-fx-border-color: black;-fx-border-width: 2px;");
        setFont(new Font("Arial", (int)35));
        setText(tile.getPlayer().getIconNameUniCode());
    }

    private void removePlayerInfo() {

        setText("");
    }


    public void UpdateAsAvailable()
    {
        setStyle("-fx-background-color: lightgreen;-fx-border-color: black;-fx-border-width: 2px;");
    }



    private void UpdateBaseColor()
    {
        setStyle((row + col) % 2 == 0 ? "-fx-background-color: lightgray;-fx-border-color: black;-fx-border-width: 2px;" : "-fx-background-color: white;-fx-border-color: black;-fx-border-width: 2px;");
    }


    private boolean isOccupied()
    {
        return tile.isOccupied();
    }

}
