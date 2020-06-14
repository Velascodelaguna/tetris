package tetris;

import javafx.scene.shape.Rectangle;
import tetris.tetromino.Tetromino;

import javafx.geometry.Point2D;

import java.util.Arrays;

// each position occupies a place in the grid
// ex: the first 40 pixels in the x and y of the
// play field is index [0][0] on the grid array
public class Grid {

    private class GridPosition {
        final int row;
        final int col;

        public GridPosition(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean equals(GridPosition other) {
            return other != null && this.row == other.row && this.col == other.col;
        }

        public String toString() {
            return "Row: " + row + " Col: " + col;
        }
    }

    private class GridSquare extends GridPosition {
        public final Rectangle square;

        public GridSquare(int row, int col, Rectangle square) {
            super(row, col);
            this.square = square;
        }
    }

    Rectangle[][] grid;
    private GridSquare[] previousPosition;
    private boolean hasActiveTetrominoStopped = false;

    public Grid() {
        this.grid = new Rectangle[20][10];
    }

    public void update(Tetromino tetromino) {
        if (this.previousPosition == null) {
            this.previousPosition = getGridSquares(tetromino.getSquares());
            return;
        }

        GridSquare[] positions = getGridSquares(tetromino.getSquares());

        if (hasStopped(positions)) {
            updateGridValues(positions);
            this.hasActiveTetrominoStopped = true;
        } else {
            this.previousPosition = positions;
            this.hasActiveTetrominoStopped = false;
        }

        clearLines();
    }

    public boolean hasStopped() {
        return this.hasActiveTetrominoStopped;
    }

    private boolean hasStopped(GridPosition[] currentPosition) {
        boolean hasStopped = true;
        for (int i = 0; i < currentPosition.length; i++) {
            GridPosition current = currentPosition[i];
            GridPosition previous = this.previousPosition[i];
            hasStopped &= current.equals(previous);
        }
        return hasStopped;
    }

    private void updateGridValues(GridSquare[] squares) {
        for (GridSquare square: squares) {
            grid[square.row][square.col] = square.square;
        }
    }

    private void clearLines() {

    }

    private GridSquare[] getGridSquares(Rectangle[] squares) {
        return Arrays.stream(squares)
                .map(square -> {
                    int gridRow = (int) (square.getY() / Tetromino.PIXEL_SIZE);
                    int gridCol = (int) (square.getX() / Tetromino.PIXEL_SIZE);
                    return new GridSquare(gridRow, gridCol, square);
                }).toArray(GridSquare[]::new);
    }

    private GridPosition[] getGridPositions(Point2D[] positions) {
        return Arrays.stream(positions)
                .map(square -> {
                    int gridRow = (int) (square.getY() / Tetromino.PIXEL_SIZE);
                    int gridCol = (int) (square.getX() / Tetromino.PIXEL_SIZE);
                    return new GridPosition(gridRow, gridCol);
                }).toArray(GridPosition[]::new);
    }

    public boolean canMoveLeft(Tetromino tetromino) {
        GridPosition[] gridSquares = getGridPositions(tetromino.getSquarePositions());
        for (GridPosition position: gridSquares) {
            // get the positions if this tetromino moves to the left
            int newCol = position.col - 1;
            if (!isMoveable(position.row, newCol)) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveRight(Tetromino tetromino) {
        for (GridPosition position: getGridPositions(tetromino.getSquarePositions())) {
            int newCol = position.col + 1;
            if (!isMoveable(position.row, newCol)) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveDown(Tetromino tetromino) {
        for (GridPosition position: getGridPositions(tetromino.getSquarePositions())) {
            int newRow = position.row + 1;
            if (!isMoveable(newRow, position.col)) {
                return false;
            }
        }
        return true;
    }

    private boolean canRotate(Point2D[] rotationCoord) {
        for (GridPosition position: getGridPositions(rotationCoord)) {
            if (!isMoveable(position.row, position.col)) {
                return false;
            }
        }
        return true;
    }

    public boolean canRotateClockwise(Tetromino tetromino) {
        Point2D[] rotationCoord = tetromino.getCoordinatesRotateClockwise();
        return canRotate(rotationCoord);
    }

    public boolean canRotateCounterClockwise(Tetromino tetromino) {
        Point2D[] rotationCoord = tetromino.getCoordinatesRotateCounterClockwise();
        return canRotate(rotationCoord);
    }

    // check the position to see if it is within the boundaries of the game and it is not already occupied
    private boolean isMoveable(int row, int col) {
        return (row >= 0 && row < 20 && col >= 0 && col < 10 && grid[row][col] == null);
    }
}
