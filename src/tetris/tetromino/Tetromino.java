package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Tetromino {
    private final int BLOCK_SIZE = 4;
    protected final int PIXEL_SIZE = 40;
    protected TetrominoType type;
    protected Color color;
    protected Color borderColor = Color.DARKGRAY;
    protected Group group;
    protected Point2D position;
    protected int[][] orientation = new int[BLOCK_SIZE][BLOCK_SIZE];
    protected Rectangle[] squares = new Rectangle[BLOCK_SIZE];

    abstract protected void initializeSquares();
    abstract protected void update(Point2D newPosition);

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
        position = position.add(0, this.PIXEL_SIZE);
        this.update(position);
    }

    public void moveRight() {
        position = position.add(this.PIXEL_SIZE, 0);
        this.update(position);
    }

    public void moveLeft() {
        position = position.add(-this.PIXEL_SIZE, 0);
        this.update(position);
    }


}
