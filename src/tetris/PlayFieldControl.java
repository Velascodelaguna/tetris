package tetris;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import tetris.tetromino.Tetromino;
import tetris.tetromino.TetrominoHandler;
import tetris.tetromino.TetrominoType;

import java.util.List;

public class PlayFieldControl {

    private final InputHandler inputHandler;
    private final TetrominoHandler tetrominoHandler;
    private final Grid grid;

    public PlayFieldControl(InputHandler inputHandler, TetrominoHandler tetrominoHandler, Grid grid) {
        this.inputHandler = inputHandler;
        this.tetrominoHandler = tetrominoHandler;
        this.grid = grid;
    }

    public void update() {
        Tetromino currentActive = this.tetrominoHandler.getActiveTetromino();
        KeyCode keyPressed = inputHandler.getKeyPressed();
        switch (keyPressed) {
            case LEFT, J -> this.moveLeft(currentActive);
            case RIGHT, L -> this.moveRight(currentActive);
            case DOWN, K -> this.moveDown(currentActive);
            case X -> this.rotateClockwise(currentActive);
            case Z -> this.rotateCounterClockwise(currentActive);
            case SPACE -> this.hardDrop(currentActive);
        }

        inputHandler.resetKeyPressed();
    }

    public boolean tetrominoHasStopped() {
        return this.grid.hasStopped();
    }

    public void updateGrid(boolean isAnimating) {
        if (isAnimating) return;

        this.grid.update(this.tetrominoHandler.getActiveTetromino());
        if (this.grid.hasStopped()) {
            grid.findLinesToClear();
            this.tetrominoHandler.addNewBlock();
        }
    }

    public void clearLines() {
        this.grid.clearLines();
    }

    public boolean hasLinesToClear() {
        List<Rectangle> squaresToClear = this.grid.getSquaresToClear();
        return this.grid.hasStopped() && squaresToClear != null && !squaresToClear.isEmpty();
    }

    public int numberOfLinesCleared() {
        return this.grid.getNumberOfLinesCleared();
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

    private void rotateClockwise(Tetromino tetromino) {
        if (tetromino.getType() == TetrominoType.O) return;

        if (this.grid.canRotateClockwise(tetromino)) {
            tetromino.rotate(Tetromino.Rotation.CLOCKWISE);
        }
    }

    private void rotateCounterClockwise(Tetromino tetromino) {
        if (tetromino.getType() == TetrominoType.O) return;

        if (this.grid.canRotateCounterClockwise(tetromino)) {
            tetromino.rotate(Tetromino.Rotation.COUNTERCLOCKWISE);
        }
    }

    private void hardDrop(Tetromino tetromino) {
        Point2D[] hardDropCoordinates = grid.getHardDropCoordinates(tetromino);
        tetromino.hardDrop(hardDropCoordinates);
    }
}
