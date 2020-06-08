package tetris;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import tetris.tetromino.Tetromino;
import tetris.tetromino.TetrominoHandler;

public class PlayFieldView {

    private final AnchorPane pane;
    private final TetrominoHandler tetrominoHandler;

    public PlayFieldView(TetrominoHandler tetrominoHandler) {
        pane = new AnchorPane();
        pane.requestFocus();
        pane.setMaxHeight(800);
        pane.setMinWidth(400);
        pane.setMaxWidth(400);

        pane.setStyle("-fx-border-color: brown");
        this.tetrominoHandler = tetrominoHandler;
    }

    public AnchorPane getView() {
        return pane;
    }

    public void initialize() {
        addTetrominoToPane(tetrominoHandler.getActiveTetromino());
    }

    public void update() {
        for (Tetromino tetromino: tetrominoHandler.getTetrominoes()) {
            if (tetromino != null && this.pane != null) {
                addTetrominoToPane(tetromino);
            }
        }
    }

    private void addTetrominoToPane(Tetromino tetromino) {
        ObservableList<Node> paneChildren = this.pane.getChildren();
        Group squares = tetromino.getGroup();
        if (!paneChildren.contains(squares)) {
            paneChildren.add(tetromino.getGroup());
        }
    }
}
