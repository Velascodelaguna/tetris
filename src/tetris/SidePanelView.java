package tetris;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class SidePanelView {

    private final VBox vbox;
    private final SidePanelControl sidePanelControl;
    private final VBox nextBlockSection;
    private final AnchorPane blockPreview;

    public SidePanelView(SidePanelControl sidePanelControl) {
        this.sidePanelControl = sidePanelControl;
        vbox = new VBox();
        blockPreview = new AnchorPane();
        blockPreview.setMinHeight(50);
        blockPreview.setMinWidth(100);
        vbox.setFocusTraversable(false);
        nextBlockSection = new VBox();
        vbox.getChildren().add(nextBlockSection);
        addNextBlock();
        addLabels();
        addButtons();

        vbox.setPadding(new Insets(90,100,0,20));
        vbox.setSpacing(20);
    }

    private void addNextBlock() {
        Label nextBlock = new Label("Next Block");
        nextBlock.setFont(new Font(20));
        nextBlockSection.getChildren().addAll(nextBlock, blockPreview);
    }

    private void addLabels() {

        Label level = new Label("Level");
        Label lines = new Label("Lines");
        Label score = new Label("Score");
        vbox.getChildren().addAll(level, lines, score);
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

    public void initialize() {
        updateNextBlockPreview();
    }

    public void updateNextBlockPreview() {
        Rectangle[] squares = this.sidePanelControl.getNextTetromino();
        ObservableList<Node> children = this.blockPreview.getChildren();
        children.clear();
        children.addAll(squares);
    }
}
