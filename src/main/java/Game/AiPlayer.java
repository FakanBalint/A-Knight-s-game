package Game;
import BoardPackage.*;
import Units.Knight;

import java.util.ArrayList;

public class AiPlayer {

    private AiPlayerType type;
    public AiPlayer(AiPlayerType type) {
        this.type = type;
    }

    public Tile calculateNextMove(Board board, Knight knight) {
        switch (type) {
            case BestFirst:
                return getBestFirstMove(board, knight);

            default:
                throw new IllegalStateException("Not Implemented AiPlayerType" + type);
        }

    }

    private Tile getBestFirstMove(Board board, Knight knight) {
        ArrayList<Tile> moves = knight.getAvailableMoves(board);

        // If no available moves, return null
        if (moves.isEmpty()) {
            return null;
        }

        return moves.get(0);
    }



}


