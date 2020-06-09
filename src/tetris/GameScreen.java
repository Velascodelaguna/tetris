package tetris;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import tetris.tetromino.TetrominoHandler;

import java.time.Duration;
import java.time.Instant;

public class GameScreen {


    private final Scene scene;
    private final PlayFieldView playFieldView;
    private final PlayFieldControl playFieldControl;
    private final SidePanelView sidePanelView;
    private final TetrominoHandler tetrominoHandler;
    private Instant deltaTime;

    public GameScreen(TetrisAppControl app, int width, int height) {
        deltaTime = Instant.now();
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

        // call on initialize so when the game
        // starts the tetris block is already created
        playFieldView.initialize();
    }

    public void gameLoopStart() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoop();
            }
        }.start();
    }

    private void gameLoop() {
        Duration delta = Duration.between(this.deltaTime, Instant.now());
        playFieldControl.update();

        // update every millisecond
        long MILLISEC_IN_SEC = 700;
        if (delta.toMillis() > MILLISEC_IN_SEC) {
            playFieldControl.moveActiveTetrominoDown();
            playFieldControl.updateGrid();
            playFieldView.update();
            deltaTime = Instant.now();
        }
    }


}
