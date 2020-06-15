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
    private int level = 1;
    private int linesCleared = 0;

    public GameScreen(TetrisAppControl app, int width, int height) {
        deltaTime = Instant.now();
        var gameLayout = new HBox();
        scene = new Scene(gameLayout, width, height);
        gameLayout.setAlignment(Pos.CENTER);
        tetrominoHandler = new TetrominoHandler();
        Grid grid = new Grid();
        playFieldView = new PlayFieldView(tetrominoHandler, grid);
        playFieldControl = new PlayFieldControl(new InputHandler(scene), tetrominoHandler, grid);
        SidePanelControl sidePanelControl = new SidePanelControl(app, tetrominoHandler);
        sidePanelView = new SidePanelView(sidePanelControl);
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
        sidePanelView.initialize();
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
        long MILLISEC_IN_SEC = 750 - (this.level * 50);
        if (delta.toMillis() > MILLISEC_IN_SEC && !playFieldControl.hasLinesToClear()) {
            playFieldControl.moveActiveTetrominoDown();
            boolean isAnimatingLines = playFieldView.isAnimating();
            playFieldControl.updateGrid(isAnimatingLines);
            playFieldView.update();
            if (playFieldControl.tetrominoHasStopped()) {
                sidePanelView.updateNextBlockPreview();
            }
            deltaTime = Instant.now();
        }

        if (playFieldControl.hasLinesToClear()) {
            long animationTime = 50;
            if (delta.toMillis() > animationTime) {
                playFieldView.animateLines();
                deltaTime = Instant.now();
            }
            if (playFieldView.isDoneAnimatingLineClear()) {
                this.linesCleared += playFieldControl.numberOfLinesCleared();
                this.sidePanelView.updateLineCount(this.linesCleared);
                if (this.linesCleared / 10 >= this.level) {
                    this.level++;
                    this.sidePanelView.updateLevel(this.level);
                }
                playFieldView.clearLines();
                playFieldControl.clearLines();
            }
        }

    }


}
