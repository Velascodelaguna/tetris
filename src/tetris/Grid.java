package tetris;

import tetris.tetromino.Tetromino;

import javafx.geometry.Point2D;
import tetris.tetromino.TetrominoType;
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

    int[][] grid;
    private GridPosition[] previousPosition;
    private boolean hasActiveTetrominoStopped = false;
    public Grid() {
        this.grid = new int[20][10];
    }

    public void update(Tetromino tetromino) {
        if (this.previousPosition == null) {
            this.previousPosition = getGridPositions(tetromino.getSquarePositions());
            return;
        }

        GridPosition[] positions = getGridPositions(tetromino.getSquarePositions());

        if (hasStopped(positions)) {
            updateGridValues(positions, tetromino.getType());
            this.hasActiveTetrominoStopped = true;
        } else {
            this.previousPosition = positions;
            this.hasActiveTetrominoStopped = false;
        }
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

    private void updateGridValues(GridPosition[] positions, TetrominoType tetrominoType) {
        for (GridPosition position: positions) {
            grid[position.row][position.col] = tetrominoType.symbol;
        }
    }

    private GridPosition[] getGridPositions(Point2D[] squarePositions) {
        return Arrays.stream(squarePositions)
                .map(square -> {
                    int gridRow = (int) (square.getY() / Tetromino.PIXEL_SIZE);
                    int gridCol = (int) (square.getX() / Tetromino.PIXEL_SIZE);
                    return new GridPosition(gridRow, gridCol);
                }).toArray(GridPosition[]::new);
    }

    public boolean canMoveLeft(Tetromino tetromino) {
        GridPosition[] gridPositions = getGridPositions(tetromino.getSquarePositions());
        for (GridPosition position: gridPositions) {
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
        return (row >= 0 && row < 20 && col >= 0 && col < 10 && grid[row][col] == 0);
    }
}
