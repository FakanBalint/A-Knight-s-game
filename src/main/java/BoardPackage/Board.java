package BoardPackage;
import Units.Knight;

public class Board {

   final private Tile[][] board;

   final private Knight player1;
   final private Knight player2;
    public Board() {
        board = new Tile[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile(i, j);
            }
        }
        player1 = placeKnight(0,0);
        player2 = placeKnight(7,7);
    }

    private Knight placeKnight( int row, int col) {
        Knight knight = new Knight(row,col);
        Tile tile = board[row][col];
        tile.placeUnitOnThisTile(knight);
        knight.setPosition(tile);
        return knight;
    }

    public Tile getTile(int row, int col) {
        return board[row][col];
    }


    public void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                Tile currentTile = board[i][j];
                if (currentTile.isOccupied() && currentTile.getPlayer() == null) {
                    System.out.print("X ");
                    continue;
                }
                if (currentTile.getPlayer() == player1) {
                    System.out.print("P1");
                } else if (currentTile.getPlayer() == player2) {
                    System.out.print("P2");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }



    public Knight getPlayer1() {
        return player1;
    }

    public Knight getPlayer2() {
        return player2;
    }

}
