import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements Player {

    private int playerId;
    private Scanner scanner;

    public HumanPlayer(int playerId, Scanner scanner) {
        this.playerId = playerId;
        this.scanner = scanner;
    }

    @Override
    public Move getMove(Board board) {
        List<Move> moves = Move.findMoves(board.getBoard(), playerId);
        System.out.printf("Player %s, please select next move (1 - %d):%n", playerId, moves.size());
        board.printMoves(moves);
        int selected;
        while (true) {
            selected = scanner.nextInt();
            if (selected < 1 || selected > moves.size()) {
                System.out.println("Incorrect move number, please try again");
            } else break;
        }
        System.out.println("Selected move: " + selected);
        return moves.get(selected - 1);
    }
}
