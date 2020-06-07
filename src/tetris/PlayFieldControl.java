package tetris;

import javafx.scene.input.KeyCode;
import tetris.tetromino.Tetromino;
import tetris.tetromino.TetrominoHandler;

public class PlayFieldControl {

    private final InputHandler inputHandler;
    private final TetrominoHandler tetrominoHandler;

    public PlayFieldControl(InputHandler inputHandler, TetrominoHandler tetrominoHandler) {
        this.inputHandler = inputHandler;
        this.tetrominoHandler = tetrominoHandler;
    }

    public void update() {
        Tetromino currentActive = this.tetrominoHandler.getActiveTetromino();
        KeyCode keyPressed = inputHandler.getKeyPressed();
        switch (keyPressed) {
            case LEFT: currentActive.moveLeft();
                       break;
            case RIGHT: currentActive.moveRight();
                        break;
            case DOWN: currentActive.moveDown();
                       break;
        }

        inputHandler.resetKeyPressed();
    }
}
