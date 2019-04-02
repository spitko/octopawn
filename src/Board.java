import java.util.Arrays;
import java.util.stream.Collectors;

// https://www.daniweb.com/programming/software-development/code/423640/unicode-chessboard-in-a-terminal
public class Board {

    private static final String[] pieces = {"   ", " \u25CF ", " \u25CB "};
    private static final char[] box = {9474, 9472, 9484, 9488, 9492, 9496, 9516, 9524, 9500, 9508, 9532};
    private static final String h3 = String.valueOf(box[1]).repeat(3);
    private static final String VLINE = String.valueOf(box[0]);
    private final String TOPLINE, MIDLINE, BOTLINE;
    private int[][] board;

    public Board(int size) {
        TOPLINE = box[2] + (h3 + box[6]).repeat(size - 1) + h3 + box[3];
        MIDLINE = box[8] + (h3 + box[10]).repeat(size - 1) + h3 + box[9];
        BOTLINE = box[4] + (h3 + box[7]).repeat(size - 1) + h3 + box[5];
        board = new int[size][size];
        reset();
    }

    public void reset() {
        Arrays.fill(board[0], 1);
        for (int i = 1; i < board.length - 1; i++) {
            Arrays.fill(board[i], 0);
        }
        Arrays.fill(board[board.length - 1], 2);
    }

    @Override
    public String toString() {
        return Arrays.stream(board).map(row -> Arrays.stream(row).mapToObj(String::valueOf)
                .collect(Collectors.joining())).collect(Collectors.joining(System.lineSeparator()));
    }

    public void printBoard() {
        System.out.println(TOPLINE);
        System.out.println(getRow(board[0]));
        for (int i = 1; i < board.length; i++) {
            System.out.println(MIDLINE);
            System.out.println(getRow(board[i]));
        }
        System.out.println(BOTLINE);
    }
    private String getRow(int[] ints) {
        return Arrays.stream(ints).mapToObj(i -> pieces[ints[i]]).collect(Collectors.joining(VLINE, VLINE, VLINE));
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] newboard){
        board = newboard;
    }
}
