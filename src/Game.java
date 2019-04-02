import java.util.Collections;
import java.util.List;

public class Game {

    private Board board;
    private int player;
    private int difficulty;

    public Game() {
        board = new Board(4);
        player = 1;
        difficulty = 2;
    }

    public void play(){
        startingMenu();
        board.printBoard();
        Move move;
        List<Move> moves;
        while (!gameOver()) {
            if (player == 1) {
                moves = Move.findMoves(board.getBoard(), 1); // OctoAI.findMove()
                if (moves.isEmpty() || board.over()){
                    System.out.println(
                                    "================================\n" +
                                    "|                              |\n" +
                                    "|                              |\n" +
                                    "|            BLACKS            |\n" +
                                    "|             WON              |\n" +
                                    "|                              |\n" +
                                    "|                              |\n" +
                                    "================================");
                    break;
                }
                if (difficulty == 2){
                    move = OctoAI.BogdanMove(board.getBoard(), 1);
                }else{
                    Collections.shuffle(moves); //
                    move = moves.get(0); //
                }
            } else {
                moves = Move.findMoves(board.getBoard(), 2);
                if (moves.isEmpty() || board.over()){
                    System.out.println(
                                    "================================\n" +
                                    "|                              |\n" +
                                    "|                              |\n" +
                                    "|            WHITES            |\n" +
                                    "|             WON              |\n" +
                                    "|                              |\n" +
                                    "|                              |\n" +
                                    "================================");
                    break;
                }
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

    public void startingMenu(){
        System.out.println(
                "================================\n" +
                "|                              |\n" +
                "|           WELCOME            |\n" +
                "|             TO               |\n" +
                "|           OCTOPAWN           |\n" +
                "|                              |\n" +
                "|                              |\n" +
                "================================");

    }

    private boolean gameOver() {
        return board.over() || Move.findMoves(board.getBoard(), player).isEmpty();
    }

}
