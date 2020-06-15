package tetris;

import javafx.scene.shape.Rectangle;
import tetris.tetromino.Tetromino;

import javafx.geometry.Point2D;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

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
    final int NUM_ROWS = 20;
    final int NUM_COLS = 10;

    public Grid() {
        this.grid = new Rectangle[NUM_ROWS][NUM_COLS];
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

    private boolean isRowFull(Rectangle[] row) {
        boolean isNull = true;
        for (Rectangle currentSquare: row) {
            isNull &= (currentSquare != null);
        }
        return isNull;
    }

    public List<Rectangle> getSquaresToClear() {
        return this.squaresToClear;
    }

    private List<Rectangle> squaresToClear;
    private void findLinesToClear(List<Integer> rowIdsToClear) {
        if (rowIdsToClear == null || rowIdsToClear.isEmpty()) return;

        squaresToClear = new LinkedList<>();
        for (int i: rowIdsToClear) {
            List<Rectangle> currentRow = Arrays.asList(grid[i]);
            squaresToClear.addAll(currentRow);
        }
    }

    public void findLinesToClear() {
        findLinesToClear(findRowIdsToClear());
    }

    public int getNumberOfLinesCleared() {
        return findRowIdsToClear().size();
    }

    private List<Integer> findRowIdsToClear() {
        List<Integer> rowIdsToClear = new LinkedList<>();

        // iterate through the index from the bottom to top (index 19 to index 0)
        IntStream.range(0, NUM_ROWS).forEach(i -> {
            int idx = NUM_ROWS - i - 1;
            Rectangle[] currentRow = grid[idx];

            if (isRowFull(currentRow)) {
                rowIdsToClear.add(idx);
            }
        });

        return rowIdsToClear;
    }

    public void clearLines() {
        List<Integer> rowIdsToClear = findRowIdsToClear();

        if (!rowIdsToClear.isEmpty()) {

            for (int rowId: rowIdsToClear) {
                grid[rowId] = new Rectangle[NUM_COLS];
            }

            HashSet<Integer> rowIdsSetToClear = new HashSet<>(rowIdsToClear);
            int lineCount = 0;
            while (!rowIdsToClear.isEmpty()) {
                int rowId = rowIdsToClear.get(0);

                for (int currentRow = rowId; currentRow >= 0; currentRow--) {
                    if (!rowIdsSetToClear.contains(currentRow)) {
                        int endIdx = rowIdsToClear.isEmpty() ? -1 : rowIdsToClear.iterator().next();
                        moveSquaresDown(currentRow, endIdx, lineCount);
                        break;
                    } else {
                        lineCount++;
                        rowIdsToClear.remove(Integer.valueOf(currentRow));
                    }
                }
            }
        }

        this.squaresToClear = null;
    }

    private void moveSquaresDown(int startRow, int endRow, int numLines) {
        if (numLines <= 0) return;

        int changeY = numLines * Tetromino.PIXEL_SIZE;
        for (int i = startRow; i > endRow; i--) {
            Rectangle[] currentRow = this.grid[i];
            this.grid[i + numLines] = currentRow;
            this.grid[i] = new Rectangle[NUM_COLS];

            for (Rectangle square: currentRow) {
                if (square != null) {
                    square.setY(square.getY() + changeY);
                }
            }
        }
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
        return (row >= 0 && row < NUM_ROWS && col >= 0 && col < NUM_COLS && grid[row][col] == null);
    }

    public Point2D[] getHardDropCoordinates(Tetromino tetromino) {
        GridPosition[] dropPositions = findDropPosition(tetromino);

        return Arrays.stream(dropPositions)
                .map(position -> new Point2D(position.col * Tetromino.PIXEL_SIZE,
                                             position.row * Tetromino.PIXEL_SIZE))
                .toArray(Point2D[]::new);
    }

    private GridPosition[] findDropPosition(Tetromino tetromino) {
        GridPosition[] positions = getGridPositions(tetromino.getSquarePositions());
        final int numRowsToMove = IntStream.range(1, NUM_ROWS).takeWhile(changeY ->
                    Arrays.stream(positions)
                        .allMatch(position -> isMoveable(position.row + changeY, position.col)))
                .max()
                .orElse(0);

        return Arrays.stream(positions)
                .map(square -> {
                    int gridRow = square.row + numRowsToMove;
                    int gridCol = square.col;
                    return new GridPosition(gridRow, gridCol);
                }).toArray(GridPosition[]::new);
    }
}
