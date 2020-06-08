package tetris;

import tetris.tetromino.Tetromino;

import javafx.geometry.Point2D;

import java.time.Duration;
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
    }

    int[][] grid;
    private Duration delta;
    public Grid() {
        this.grid = new int[20][10];
        this.delta = Duration.ZERO;
    }

    public void update(Duration delta, Tetromino tetromino) {

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

    // check the position to see if it is within the boundaries of the game and it is not already occupied
    private boolean isMoveable(int row, int col) {
        return (row >= 0 && row < 20 && col >= 0 && col < 10 && grid[row][col] == 0);
    }
}
