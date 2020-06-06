package tetris;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SidePanelView {

    private final VBox vbox;
    private final SidePanelControl sidePanelControl;

    public SidePanelView(TetrisAppControl appControl) {
        sidePanelControl = new SidePanelControl(appControl);
        vbox = new VBox();

        Label nextBlock = new Label("Next Block");
        Label level = new Label("Level");
        Label score = new Label("Score");
        Button restart = this.getRestartButton();
        Button quit = this.getQuitButton();
        vbox.getChildren().addAll(nextBlock, level, score, restart, quit);

    }

    private Button getRestartButton() {
        Button button = new Button("Restart");
        button.setOnAction((event) -> this.sidePanelControl.restart());
        return button;
    }

    private Button getQuitButton() {
        Button button = new Button("Quit");
        button.setOnAction((event) -> this.sidePanelControl.quit());
        return button;
    }

    public VBox getView() {
        return vbox;
    }
}
