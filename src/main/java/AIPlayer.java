import java.util.Collections;
import java.util.List;

public class AIPlayer implements Player {

    private int playerId;

    public AIPlayer(int playerId) {
        this.playerId = playerId;
    }

    private Move bogdanMove(int[][] board) {
        List<Move> moves = Move.findMoves(board, playerId);
        if (playerId == 2) {
            for (Move move : moves) {
                if (move.y2 == 0) {
                    return move;
                }
            }
            for (Move move : moves) {
                if ((move.y1 == 3) && (move.x1 != move.x2)) {
                    return move;
                }
            }
            for (Move move : moves) {
                if (move.x1 != move.x2) {
                    return move;
                }
            }
        } else if (playerId == 1) {
            for (Move move : moves) {
                if (move.y2 == 3) {
                    return move;
                }
            }
            for (Move move : moves) {
                if ((move.y1 == 0) && (move.x1 != move.x2)) {
                    return move;
                }
            }
            for (Move move : moves) {
                if (move.x1 != move.x2) {
                    return move;
                }
            }
        }
        Collections.shuffle(moves);
        return moves.get(0);
    }

    @Override
    public Move getMove(Board board) {
        return bogdanMove(board.getBoard());
    }
}
