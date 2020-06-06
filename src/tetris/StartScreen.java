package tetris;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class StartScreen {

    private final Scene scene;

    public StartScreen(TetrisAppControl app, int width, int height) {
        Button startButton = new Button("Start!");
        startButton.setMinSize(180, 70);
        startButton.setStyle("-fx-font-size:30");
        startButton.setOnAction((event) -> app.setGameScreen());
        StackPane stack = new StackPane();
        stack.getChildren().add(startButton);
        scene = new Scene(stack, width, height);
    }

    public Scene getScene() {
        return this.scene;
    }
}
