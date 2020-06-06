package tetris;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameScreen {


    private final Scene scene;

    public GameScreen(TetrisAppControl app, int width, int height) {

        var pane = new BorderPane();
        var lbl = new Label("TEST GAME");
        pane.setLeft(lbl);
        scene = new Scene(pane, width, height);
    }

    public Scene getScene() {
        return this.scene;
    }
}
