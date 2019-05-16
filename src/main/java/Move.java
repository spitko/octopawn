import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Move {

    final int x1, y1, x2, y2;

    //Liikumise konstruktor alg ning lõppkoordinaatidega
    public Move(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return x1 == move.x1 &&
                y1 == move.y1 &&
                x2 == move.x2 &&
                y2 == move.y2;
    }


    public static void makeMove(int[][] board, Move move) {
        board[move.y2][move.x2] = board[move.y1][move.x1];
        board[move.y1][move.x1] = 0;
    }


    //Võimalike käikude otsija abimeetod
    public static List<Move> findMoves(int[][] board, int player) {
        List<Move> moves = new ArrayList<>();
        int fw, value, other;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                value = board[y][x];
                if (value == 0) continue;
                if (value != player) continue;
                fw = value == 1 ? 1 : -1;
                other = 3 - player;

                if (y + fw < board.length && y + fw >= 0) {
                    if (board[y + fw][x] == 0) moves.add(new Move(x, y, x, y + fw));
                    if (x - 1 >= 0 && board[y + fw][x - 1] == other) moves.add(new Move(x, y, x - 1, y + fw));
                    if (x + 1 < board.length && board[y + fw][x + 1] == other) moves.add(new Move(x, y, x + 1, y + fw));
                }
            }
        }
        return moves;
    }

    //Võimalike käikude otsija peameetod
    public static List<Move> findMoves(Tile[][] board, int player) {
        int[][] intBoard = Arrays.stream(board).map(t -> Arrays.stream(t).mapToInt(Tile::getPiece).toArray()).toArray(int[][]::new);
        return findMoves(intBoard, player);
    }


    //Käigu toString
    @Override
    public String toString() {
        return new StringJoiner("", Move.class.getSimpleName() + "[", ")]")
                .add("(" + x1)
                .add(", " + y1)
                .add("), (" + x2)
                .add(", " + y2)
                .toString();
    }
}
