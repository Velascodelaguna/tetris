package tetris;

import javafx.application.Platform;
import javafx.stage.Stage;

public class TetrisApp {

    public final int HEIGHT = 900;
    public final int WIDTH = 700;
    private final Stage stage;
    private final TetrisAppControl control;

    public TetrisApp(Stage stage) {
        this.stage = stage;
        this.control = new TetrisAppControl(this);
    }

    public void start() {
        setStartScreen();
        stage.show();
    }

    public void setStartScreen() {
        StartScreen start = new StartScreen(this.control, WIDTH, HEIGHT);
        stage.setScene(start.getScene());
    }

    public void setGameScreen() {
        GameScreen game = new GameScreen(this.control, WIDTH, HEIGHT);
        stage.setScene(game.getScene());
        game.gameLoopStart();
    }

    public void endGame() {
        this.stage.close();
        Platform.exit();
    }
}
