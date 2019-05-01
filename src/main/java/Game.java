import java.util.Scanner;

public class Game {

    private static final String firstPlayerWin = "       G A M E  O V E R\n" +
            "================================\n" +
            "|                              |\n" +
            "|                              |\n" +
            "|          PLAYER 1            |\n" +
            "|            WINS              |\n" +
            "|                              |\n" +
            "|                              |\n" +
            "================================";

    private static final String secondPlayerWin = "       G A M E  O V E R\n" +
            "================================\n" +
            "|                              |\n" +
            "|                              |\n" +
            "|          PLAYER 2            |\n" +
            "|            WINS              |\n" +
            "|                              |\n" +
            "|                              |\n" +
            "================================";

    private final Scanner scanner;
    private Board board;
    private int turn;
    private Player firstPlayer;
    private Player secondPlayer;

    public Game(int playerCount, int boardSize, Scanner scanner) {
        board = new Board(boardSize);
        turn = 1;
        this.scanner = scanner;
        createPlayers(playerCount, scanner);

    }

    private void createPlayers(int count, Scanner scanner) {
        if (count > 0) {
            firstPlayer = new HumanPlayer(1, scanner);
            if (count > 1) {
                secondPlayer = new HumanPlayer(2, scanner);
            } else secondPlayer = new AIPlayer(2);
        } else {
            firstPlayer = new AIPlayer(1);
            secondPlayer = new RandomPlayer(2);
        }
    }

    public void play() {
        board.printBoard();
        Move move;
        int state = getState();
        while (state == 0) {
            if (turn == 1) move = firstPlayer.getMove(board);
            else move = secondPlayer.getMove(board);
            board.move(move);
            turn = 3 - turn;
            board.printBoard();
            state = getState();
        }
        if (state == 1) System.out.println(firstPlayerWin);
        if (state == 2) System.out.println(secondPlayerWin);
        System.out.println("Type q to quit or type anything else to go again");
        if (scanner.next().equals("q")) return;
        board.reset();
        play();


    }

    private int getState() {
        if (Move.findMoves(board.getBoard(), turn).size() == 0) return 3 - turn;
        return board.getState();
    }

}
