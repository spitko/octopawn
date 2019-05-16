import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Octopawn extends Application {

    //Piiripaani loomine
    private Pane getMainPane() {
        //Tugielementide ja nuppude lisamine paanile
        BorderPane pane = new BorderPane();
        FlowPane top = new FlowPane();
        top.setPadding(new Insets(3));
        top.setHgap(7);
        top.setStyle("-fx-border-color : black; -fx-border-width : 0 0 1 0; ");
        Label status = new Label();
        Label sizeLabel = new Label("Board Size:");
        Spinner<Integer> spinner = new Spinner<>(2, 10, 4);
        Button startButton = new Button("Start");
        //Stardinupule vajutamise käsk, mis loob ekraanile laua
        startButton.setOnAction(e -> {
            BoardPane board = new BoardPane(spinner.getValue());
            status.textProperty().bind(board.getStatus());
            pane.setCenter(board);
        });
        top.getChildren().addAll(sizeLabel, spinner, startButton, status);
        pane.setTop(top);
        return pane;
    }

    //Pealava loomine, kasutades eeltehtud piiripaani
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(getMainPane(), 420, 420);
        primaryStage.setTitle("Octopawn");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
