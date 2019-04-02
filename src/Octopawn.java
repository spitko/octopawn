import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Octopawn {

    public static void main(String[] args) {
        Board board = new Board(4);
        System.out.println(board);
        board.printBoard();
        for (int i = 0; i < board.getBoard().length; i++) {
            System.out.println(Arrays.toString(board.getBoard()[i]));
        }
        List<int[]> list = new ArrayList<>();


    }

}
