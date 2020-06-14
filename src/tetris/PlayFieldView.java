package tetris;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
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
        clearLines();
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

    private void clearLines() {
        List<Rectangle> linesToClear = grid.getLinesToClear();
        if (linesToClear != null && !linesToClear.isEmpty()) {
            this.pane.getChildren().removeAll(linesToClear);
        }
    }
}
