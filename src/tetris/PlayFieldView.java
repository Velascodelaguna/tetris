package tetris;

import javafx.scene.layout.*;
import tetris.tetromino.OTetromino;

public class PlayFieldView {

    private final AnchorPane pane;
    private OTetromino ot;
    public PlayFieldView() {
        pane = new AnchorPane();

        pane.setMaxHeight(800);
        pane.setMinWidth(400);

        pane.setStyle("-fx-border-color: blue");
        ot = new OTetromino();
        pane.getChildren().add(ot.getSquares());
    }

    public AnchorPane getView() {
        return pane;
    }

    public void update() {
        ot.moveDown();

    }
}
