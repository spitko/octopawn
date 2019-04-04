import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://www.daniweb.com/programming/software-development/code/423640/unicode-chessboard-in-a-terminal
public class Board {

    private static final String[] pieces = {"   ", " \u25CF ", " \u25CB "};
    private static final char[] box = {9474, 9472, 9484, 9488, 9492, 9496, 9516, 9524, 9500, 9508, 9532};
    private static final String h3 = String.valueOf(box[1]).repeat(3);
    private static final String VLINE = String.valueOf(box[0]);
    private final String TOPLINE, MIDLINE, BOTLINE;
    private final int[][] board;

    public Board(int size) {
        TOPLINE = box[2] + (h3 + box[6]).repeat(size - 1) + h3 + box[3];
        MIDLINE = box[8] + (h3 + box[10]).repeat(size - 1) + h3 + box[9];
        BOTLINE = box[4] + (h3 + box[7]).repeat(size - 1) + h3 + box[5];
        board = new int[size][size];
        reset();
    }

    public static void printBoard(int[][] board) {
        Arrays.stream(board).map(Arrays::toString).forEach(System.out::println);
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

    public int[][] copyBoard() {
        return Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
    }

    public int[][] getBoard() {
        return board;
    }

    public void move(Move move) {
        Move.makeMove(board, move);
    }

    public void printMoves(List<Move> moves) {
        String[][] boardLines = moves.stream().map(this::getMove)
                .map(s -> s.split(System.lineSeparator())).toArray(String[][]::new);
        StringBuilder result = new StringBuilder();
        IntStream.range(0, boardLines.length).forEach(i -> result.append(i + 1).append(" ".repeat(4 * board.length)));
        result.append(System.lineSeparator());
        for (int i = 0; i < boardLines[0].length; i++) {
            for (String[] lines : boardLines) {
                result.append(lines[i]);
            }
            result.append(System.lineSeparator());
        }
        System.out.println(result.toString());

    }

    public String getMove(Move move) {
        Direction direction = Direction.getDirection(move);
        StringBuilder string = getBoardString(copyBoard());
        int index;
        if (direction.isUp()) index = (8 * board.length + 4) * move.y1;
        else index = (8 * board.length + 4) * (move.y1 + 1);
        index += direction.getOffset() + move.x1 * 4;
        string.setCharAt(index, direction.getArrow());
        return string.toString();
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

    private StringBuilder getBoardString(int[][] board) {
        StringBuilder stringBuilder = new StringBuilder(170);
        stringBuilder.append(TOPLINE).append("\n");
        stringBuilder.append(getRow(board[0])).append("\n");
        for (int i = 1; i < board.length; i++) {
            stringBuilder.append(MIDLINE).append("\n");
            stringBuilder.append(getRow(board[i])).append("\n");
        }
        stringBuilder.append(BOTLINE).append("\n");
        return stringBuilder;
    }

    public int getState() {
        if (Arrays.stream(board[0]).anyMatch(i -> i == 2)) return 2;
        if (Arrays.stream(board[board.length - 1]).anyMatch(i -> i == 1)) return 1;
        return 0;
    }

    private String getRow(int[] ints) {
        return Arrays.stream(ints).mapToObj(i -> pieces[i]).collect(Collectors.joining(VLINE, VLINE, VLINE));
    }


}
