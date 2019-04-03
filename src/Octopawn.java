import java.util.Scanner;

public class Octopawn {


    private static final String welcomeMessage =
            "================================\n" +
                    "|                              |\n" +
                    "|           WELCOME            |\n" +
                    "|             TO               |\n" +
                    "|           OCTOPAWN           |\n" +
                    "|                              |\n" +
                    "|                              |\n" +
                    "================================";


    public static void main(String[] args) {
        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        int players, boardSize;
        System.out.println("Please select number of players (0 - 2): ");
        players = scanner.nextInt();
        System.out.println("Please select board size: ");
        boardSize = scanner.nextInt();
        Game game = new Game(players, boardSize, scanner);
        game.play();
    }
}
