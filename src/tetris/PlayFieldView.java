package tetris;

import javafx.scene.layout.*;

public class PlayFieldView {

    private final AnchorPane pane;

    public PlayFieldView() {
        pane = new AnchorPane();

        pane.setMaxHeight(800);
        pane.setMinWidth(400);

        pane.setStyle("-fx-border-color: blue");
    }

    public AnchorPane getView() {
        return pane;
    }
}
