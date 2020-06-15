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
    private AnchorPane blockPreview;
    private VBox levelSection;
    private Label levelCount;
    private VBox lineSection;
    private Label lineCount;

    public SidePanelView(SidePanelControl sidePanelControl) {
        this.sidePanelControl = sidePanelControl;
        vbox = new VBox();
        vbox.setFocusTraversable(false);

        nextBlockSection = new VBox();
        vbox.getChildren().add(nextBlockSection);
        addNextBlock();

        levelSection = new VBox();
        vbox.getChildren().add(levelSection);
        addLevelCount();

        lineSection = new VBox();
        vbox.getChildren().add(lineSection);
        addLineCount();
        addLabels();
        addButtons();

        vbox.setPadding(new Insets(90,20,0,20));
        vbox.setSpacing(20);
    }

    private void addNextBlock() {
        Label nextBlock = new Label("Next Block");
        nextBlock.setFont(new Font(20));
        this.blockPreview = new AnchorPane();
        this.blockPreview.setMinHeight(50);
        this.blockPreview.setMinWidth(100);
        this.nextBlockSection.getChildren().addAll(nextBlock, blockPreview);
    }

    private void addLevelCount() {
        Label level = new Label("Level");
        this.levelCount = new Label("1");
        this.levelCount.setFont(new Font(20));
        this.levelSection.getChildren().addAll(level, levelCount);
    }

    private void addLineCount() {
        Label lines = new Label("Lines");
        this.lineCount = new Label("0");
        this.lineCount.setFont(new Font(20));
        this.lineSection.getChildren().addAll(lines, lineCount);
    }

    private void addLabels() {
        Label score = new Label("Score");
        vbox.getChildren().addAll(score);
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

    public void updateLevel(int newLevel) {
        this.levelCount.setText(String.valueOf(newLevel));
    }

    public void updateLineCount(int lineCount) {
        this.lineCount.setText(String.valueOf(lineCount));
    }
}
