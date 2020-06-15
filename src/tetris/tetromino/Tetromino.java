package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public abstract class Tetromino {
    private final int BLOCK_SIZE = 4;
    public static final int PIXEL_SIZE = 40;
    protected TetrominoType type;
    protected Color color;
    protected Color borderColor = Color.DARKGRAY;
    protected Point2D position;
    protected int orientation = 0;
    protected Rectangle[] squares = new Rectangle[BLOCK_SIZE];
    protected LinkedList<Point2D[]> rotationCoordinates;

    abstract protected void initializeSquares();
    abstract protected void initializeRotationCoordinates();

    public Rectangle[] getSquares() {
        return this.squares;
    }

    public Point2D[] getSquarePositions() {
        return Arrays.stream(squares)
                .map(square -> new Point2D(square.getX(), square.getY()))
                .toArray(Point2D[]::new);
    }

    public TetrominoType getType() {
        return this.type;
    }

    public Color getColor() {
        return this.color;
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

    protected ArrayList<BlockMover[]> rotationList;

    protected int getNextOrientation() {
        return (this.orientation + 1) % 4;
    }

    protected int getPreviousOrientation() {
        int previous = this.orientation - 1;
        return (previous < 0) ? 3 : previous;
    }

    private BlockMover[] getBlockMoversClockwise() {
        int nextOrientation = this.getNextOrientation();
        return this.rotationList.get(nextOrientation);
    }

    private BlockMover[] getBlockMoversCounterClockwise() {
        int rotationIdx = (this.orientation + 2) % 4;
        return this.rotationList.get(rotationIdx);
    }

    private Point2D[] getRotationPositions(BlockMover[] movers) {
        return IntStream.range(0, 4).mapToObj(i -> {
            BlockMover mover = movers[i];
            Rectangle square = squares[i];
            return mover.move(square);
        }).toArray(Point2D[]::new);
    }

    public Point2D[] getCoordinatesRotateClockwise() {
        BlockMover[] movers = getBlockMoversClockwise();
        return this.getRotationPositions(movers);
    }

    public Point2D[] getCoordinatesRotateCounterClockwise() {
        BlockMover[] movers = getBlockMoversCounterClockwise();
        return this.getRotationPositions(movers);
    }

    private void rotate(Point2D[] newRotationPositions) {
        IntStream.range(0, 4).forEach(i -> {
            Point2D rotationCoord = newRotationPositions[i];
            Rectangle square = this.squares[i];
            square.setX(rotationCoord.getX());
            square.setY(rotationCoord.getY());
        });
    }

    public enum Rotation {CLOCKWISE, COUNTERCLOCKWISE};

    public void rotate(Rotation rotation) {
        if (getType() == TetrominoType.O) return;

        if (rotation == Rotation.CLOCKWISE) {
            this.rotate(this.getCoordinatesRotateClockwise());
            this.orientation = this.getNextOrientation();
        } else if (rotation == Rotation.COUNTERCLOCKWISE) {
            this.rotate(this.getCoordinatesRotateCounterClockwise());
            this.orientation = this.getPreviousOrientation();
        }
    }

    public void hardDrop(Point2D[] dropPositions) {
        for (int i = 0; i < dropPositions.length; i++) {
            Point2D newPosition = dropPositions[i];
            Rectangle square = this.squares[i];
            square.setX(newPosition.getX());
            square.setY(newPosition.getY());
        }
    }
}
