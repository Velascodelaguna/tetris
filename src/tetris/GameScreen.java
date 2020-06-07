package tetris;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import tetris.tetromino.TetrominoHandler;

public class GameScreen {


    private final Scene scene;
    private final PlayFieldView playFieldView;
    private final PlayFieldControl playFieldControl;
    private final SidePanelView sidePanelView;
    private final TetrominoHandler tetrominoHandler;
    private long startTime;

    public GameScreen(TetrisAppControl app, int width, int height) {

        var gameLayout = new HBox();
        scene = new Scene(gameLayout, width, height);
        gameLayout.setAlignment(Pos.CENTER);
        tetrominoHandler = new TetrominoHandler();
        playFieldView = new PlayFieldView(tetrominoHandler);
        sidePanelView = new SidePanelView(app);
        playFieldControl = new PlayFieldControl(new InputHandler(scene), tetrominoHandler);
        gameLayout.getChildren().addAll(playFieldView.getView(), sidePanelView.getView());
        initializeGame();
    }

    public Scene getScene() {
        return this.scene;
    }

    private void initializeGame() {
        tetrominoHandler.initialize();

        // call update on initialize so when the game
        // starts the tetris block is already created
        playFieldView.update();
    }

    public void gameLoopStart() {
        startTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoop(now);
            }
        }.start();
    }

    private void gameLoop(long now) {
        // update every 1 second
        if (now - startTime > 1_000_000_000) {
            startTime = now;
            playFieldControl.update();
            playFieldView.update();

        }
    }


}
