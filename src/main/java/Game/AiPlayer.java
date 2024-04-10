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
        Tile bestMove = null;
        int minOpponentMoves = Integer.MAX_VALUE;

        for (Tile move : moves) {
            // Create a copy of the board
            Board boardCopy = board.copyBoard();

            // Simulate the move on the board copy
            Knight knightCopy = boardCopy.getPlayer1(); // Assuming current knight is player1
            knightCopy.move(move, boardCopy);

            // Count available moves for the opponent on the board copy
            int opponentMoves = boardCopy.getPlayer2().getAvailableMoves(boardCopy).size();

            // Update best move if this move leads to fewer opponent moves
            if (opponentMoves < minOpponentMoves) {
                minOpponentMoves = opponentMoves;
                bestMove = move;
            }
        }

        return bestMove;
    }




}


