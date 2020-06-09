package tetris;

import javafx.scene.input.KeyCode;
import tetris.tetromino.Tetromino;
import tetris.tetromino.TetrominoHandler;

import java.time.Duration;
import java.time.Instant;

public class PlayFieldControl {

    private final InputHandler inputHandler;
    private final TetrominoHandler tetrominoHandler;
    private final Grid grid;
    private Duration deltaTime = Duration.ZERO;

    public PlayFieldControl(InputHandler inputHandler, TetrominoHandler tetrominoHandler) {
        this.inputHandler = inputHandler;
        this.tetrominoHandler = tetrominoHandler;
        this.grid = new Grid();
    }

    public void update() {
        Tetromino currentActive = this.tetrominoHandler.getActiveTetromino();
        KeyCode keyPressed = inputHandler.getKeyPressed();
        switch (keyPressed) {
            case LEFT -> this.moveLeft(currentActive);
            case RIGHT -> this.moveRight(currentActive);
            case DOWN -> this.moveDown(currentActive);
        }

        inputHandler.resetKeyPressed();
    }

    public void updateGrid(Duration delta) {
        if (delta.toMillis() > 700) {
            deltaTime = Duration.ZERO;
            this.grid.update(this.tetrominoHandler.getActiveTetromino());
            if (this.grid.hasStopped()) {
                tetrominoHandler.addNewBlock();
            }
        }
    }

    public void moveActiveTetrominoDown() {
        Tetromino currentActive = this.tetrominoHandler.getActiveTetromino();
        moveDown(currentActive);
    }

    private void moveLeft(Tetromino tetromino) {
        if (this.grid.canMoveLeft(tetromino)) {
            tetromino.moveLeft();
        }
    }

    private void moveRight(Tetromino tetromino) {
        if (this.grid.canMoveRight(tetromino)) {
            tetromino.moveRight();
        }
    }

    private void moveDown(Tetromino tetromino) {
        if (this.grid.canMoveDown(tetromino)) {
            tetromino.moveDown();
        }
    }
}
