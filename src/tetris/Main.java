package tetris;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start (Stage stage) {
        stage.setTitle("Tetris");
        TetrisApp app = new TetrisApp(stage);
        app.start();
    }


    public static void main (String[] args) {
        launch(args);
    }
}
