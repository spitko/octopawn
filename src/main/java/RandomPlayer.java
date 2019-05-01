import java.util.Collections;
import java.util.List;

public class RandomPlayer implements Player {

    private int playerId;


    public RandomPlayer(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public Move getMove(Board board) {
        List<Move> moves = Move.findMoves(board.getBoard(), playerId);
        Collections.shuffle(moves);
        return moves.get(0);
    }
}
