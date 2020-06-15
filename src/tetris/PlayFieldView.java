package tetris;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.tetromino.Tetromino;
import tetris.tetromino.TetrominoHandler;

import java.util.List;

public class PlayFieldView {

    private final AnchorPane pane;
    private final TetrominoHandler tetrominoHandler;
    private final Grid grid;

    public PlayFieldView(TetrominoHandler tetrominoHandler, Grid grid) {
        pane = new AnchorPane();
        pane.requestFocus();
        pane.setMaxHeight(800);
        pane.setMinWidth(400);
        pane.setMaxWidth(400);

        pane.setStyle("-fx-border-color: brown");
        this.tetrominoHandler = tetrominoHandler;
        this.grid = grid;
    }

    public AnchorPane getView() {
        return pane;
    }

    public void initialize() {
        addTetrominoToPane(tetrominoHandler.getActiveTetromino());
    }

    public void update() {
        Tetromino tetromino = tetrominoHandler.getActiveTetromino();
        if (tetromino != null && this.pane != null) {
            addTetrominoToPane(tetromino);
        }
    }

    private void addTetrominoToPane(Tetromino tetromino) {
        ObservableList<Node> paneChildren = this.pane.getChildren();
        Rectangle[] squares = tetromino.getSquares();
        for (Rectangle square: squares) {
            if (!paneChildren.contains(square)) {
                paneChildren.add(square);
            }
        }
    }

    public void clearLines() {
        List<Rectangle> linesToClear = grid.getSquaresToClear();
        if (linesToClear != null && !linesToClear.isEmpty()) {
            this.pane.getChildren().removeAll(linesToClear);
        }

        // reset the values used for animation
        this.originalColors = null;
        this.animateDelta = 0;
        this.isAnimating = false;
    }

    private Color[] originalColors;
    private int animateDelta = 0;
    private boolean isAnimating = false;
    public void animateLines() {
        isAnimating = true;
        List<Rectangle> squaresToClear = grid.getSquaresToClear();
        if (originalColors == null) {
            originalColors = squaresToClear.stream()
                    .map(square1 -> (Color) square1.getFill())
                    .toArray(Color[]::new);
        }

        for (int i = 0; i < squaresToClear.size(); i++) {
            Rectangle square =  squaresToClear.get(i);
            if (animateDelta % 2 == 0) {
                square.setFill(Color.AZURE);
            } else {
                square.setFill(originalColors[i]);
            }
        }
        animateDelta++;
    }

    public boolean isDoneAnimatingLineClear() {
        return this.animateDelta > 4;
    }

    public boolean isAnimating() {
        return this.isAnimating;
    }
}
