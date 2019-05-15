import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Scanner;

public class Octopawn extends Application {


    /*private static final String welcomeMessage =
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
    }*/

    private static Pane getMainPane(){
        BorderPane pane = new BorderPane();
        BorderPane toppane = new BorderPane();
        Button startButton = new Button("Start");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add("1 Player");
        choiceBox.getItems().add("2 Players");
        Spinner<Integer> spinner = new Spinner<>(4, 8, 4);
        toppane.setLeft(choiceBox);
        toppane.setRight(startButton);
        toppane.setCenter(spinner);
        pane.setTop(toppane);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (choiceBox.getValue().equals("1 Player")){

                }else if (choiceBox.getValue().equals("2 Players")){

                }
            }
        });

        return pane;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene stseen1 = new Scene(getMainPane(), 400, 400, Color.SNOW);

        primaryStage.setTitle("Octopawn");
        primaryStage.setScene(stseen1);
        primaryStage.show();
    }
}
