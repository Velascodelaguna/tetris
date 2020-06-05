package tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start (Stage stage) {

        var root = new StackPane();
        var scene = new Scene(root, 300, 250);

        var lbl = new Label("Simple JAVAFX app");

        root.getChildren().add(lbl);

        stage.setTitle("SIMPLE APP");
        stage.setScene(scene);
        stage.show();
    }


    public static void main (String[] args) {
        launch(args);
    }
}
