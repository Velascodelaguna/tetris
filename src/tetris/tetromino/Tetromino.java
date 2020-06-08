package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public abstract class Tetromino {
    private final int BLOCK_SIZE = 4;
    public static final int PIXEL_SIZE = 40;
    protected TetrominoType type;
    protected Color color;
    protected Color borderColor = Color.DARKGRAY;
    protected Group group;
    protected Point2D position;
    protected boolean isActive = true;
    protected int[][] orientation = new int[BLOCK_SIZE][BLOCK_SIZE];
    protected Rectangle[] squares = new Rectangle[BLOCK_SIZE];

    abstract protected void initializeSquares();

    public Group getGroup() {
        return this.group;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public Point2D[] getSquarePositions() {
        return Arrays.stream(squares)
                .map(square -> new Point2D(square.getX(), square.getY()))
                .toArray(Point2D[]::new);
    }

    public TetrominoType getType() {
        return this.type;
    }

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
        position = position.add(0, PIXEL_SIZE);
        this.update(0, PIXEL_SIZE);
    }

    public void moveRight() {
        position = position.add(PIXEL_SIZE, 0);
        this.update(PIXEL_SIZE, 0);
    }

    public void moveLeft() {
        position = position.add(-1* PIXEL_SIZE, 0);
        this.update(-1* PIXEL_SIZE, 0);
    }

    public void update(double dx, double dy) {
        for (Rectangle square: squares) {
            square.setX(square.getX() + dx);
            square.setY(square.getY() + dy);
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean state) {
        this.isActive = state;
    }
}
