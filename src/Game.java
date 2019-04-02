import java.util.Collections;
import java.util.List;

public class Game {

    private Board board;
    private int player;

    public Game() {
        board = new Board(4);
        player = 1;
        board.printBoard();
    }

    public void play(){
        Move move;
        List<Move> moves;
        while (!gameOver()) {
            if (player == 1) {
                moves = Move.findMoves(board.getBoard(), 1); // OctoAI.findMove()
                //Collections.shuffle(moves); //
                //move = moves.get(0); //
                move = OctoAI.BogdanMove(board.getBoard(), 1);
            } else {
                moves = Move.findMoves(board.getBoard(), 2);
                Collections.shuffle(moves);
                move = moves.get(0);
            }
            System.out.println(moves);
            System.out.println(move);
            board.move(move);
            player = 3 - player;
            board.printBoard();
            System.out.println(board);
        }
    }

    private boolean gameOver() {
        return board.over() || Move.findMoves(board.getBoard(), player).isEmpty();
    }

}
