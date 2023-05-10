package BoardPackage;
import Units.Knight;

public class Board {

    private Tile[][] board;

    public Board() {
        board = new Tile[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile(i, j);
            }
        }
    }

}
