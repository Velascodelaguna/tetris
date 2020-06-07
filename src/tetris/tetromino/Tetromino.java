package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Tetromino {
    private final int BLOCK_SIZE = 4;
    protected final int PIXEL_SIZE = 40;
    protected TetrominoType type;
    protected Color color;
    protected int[][] orientation = new int[BLOCK_SIZE][BLOCK_SIZE];
    protected Point2D position;

    public int[][] rotateClockwise() {
        if (this.type == TetrominoType.O) return this.orientation;

        int[][] rotation = new int[BLOCK_SIZE][BLOCK_SIZE];
        for (int r = 0; r < BLOCK_SIZE; r++) {
            for (int c = 0; c < BLOCK_SIZE; c++) {
                rotation[c][BLOCK_SIZE-r-1] = orientation[r][c];
            }
        }
        return rotation;
    }

    public int[][] rotateCounterClockwise() {
        if (this.type == TetrominoType.O) return this.orientation;

        int[][] rotation = new int[BLOCK_SIZE][BLOCK_SIZE];
        for (int r = 0; r < BLOCK_SIZE; r++) {
            for (int c = 0; c < BLOCK_SIZE; c++) {
                rotation[BLOCK_SIZE-c-1][r] = orientation[r][c];
            }
        }
        return rotation;
    }

    public void moveDown() {
        position.add(0, this.PIXEL_SIZE);
    }

    public void moveRight() {
        position.add(this.PIXEL_SIZE, 0);
    }

    public void moveLeft() {
        position.add(-this.PIXEL_SIZE, 0);
    }
}
