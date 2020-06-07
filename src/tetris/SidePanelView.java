package tetris;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SidePanelView {

    private final VBox vbox;
    private final SidePanelControl sidePanelControl;

    public SidePanelView(TetrisAppControl appControl) {
        sidePanelControl = new SidePanelControl(appControl);
        vbox = new VBox();
        vbox.setFocusTraversable(false);
        addLabels();
        addButtons();

        vbox.setPadding(new Insets(90,100,0,20));
        vbox.setSpacing(20);
    }

    private void addLabels() {
        Label nextBlock = new Label("Next Block");
        nextBlock.setFont(new Font(20));
        Label level = new Label("Level");
        Label lines = new Label("Lines");
        Label score = new Label("Score");
        vbox.getChildren().addAll(nextBlock, level, lines, score);
    }

    private void addButtons() {
        Button restart = this.getRestartButton();
        Button quit = this.getQuitButton();
        vbox.getChildren().addAll(restart, quit);
    }

    private Button getRestartButton() {
        Button button = new Button("Restart");
        button.setOnAction((event) -> this.sidePanelControl.restart());
        button.setFocusTraversable(false);
        return button;
    }

    private Button getQuitButton() {
        Button button = new Button("Quit");
        button.setOnAction((event) -> this.sidePanelControl.quit());
        button.setFocusTraversable(false);
        return button;
    }

    public VBox getView() {
        return vbox;
    }
}
