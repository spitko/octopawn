import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Octopawn extends Application {

    //Piiripaani loomine
    private Pane getMainPane() {
        //Tugielementide ja nuppude lisamine paanile
        BorderPane pane = new BorderPane();
        FlowPane top = new FlowPane();
        Label status = new Label();
        Button player = new Button("Board Size:");
        Label autorid = new Label("     Autorid: Samuel, Bogdan");
        Spinner<Integer> spinner = new Spinner<>(3, 10, 4);
        Button startButton = new Button("Start");
        //Stardinupule vajutamise käsk, mis loob ekraanile laua
        startButton.setOnAction(e -> {
            BoardPane board = new BoardPane(spinner.getValue());
            status.textProperty().bind(board.getStatus());
            pane.setCenter(board);
        });
        top.getChildren().addAll(player, spinner, startButton, status, autorid);
        pane.setTop(top);
        return pane;
    }

    //Pealava loomine, kasutades eeltehtud piiripaani
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(getMainPane(), 400, 400);
        primaryStage.setTitle("Octopawn");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
