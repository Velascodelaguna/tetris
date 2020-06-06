package tetris;

import javafx.scene.layout.AnchorPane;

public class PlayFieldView {

    private final AnchorPane pane;

    public PlayFieldView() {
        pane = new AnchorPane();
    }

    public AnchorPane getView() {
        return pane;
    }
}
