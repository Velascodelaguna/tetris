package tetris;

import javafx.stage.Stage;

public class TetrisApp {

    public final int HEIGHT = 1000;
    public final int WIDTH = 800;
    private Stage stage;
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
    }

    public void endGame() {
        this.stage.close();
    }
}
