package tetris;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class GameScreen {


    private final Scene scene;

    public GameScreen(TetrisAppControl app, int width, int height) {

        var gameLayout = new HBox();
        gameLayout.setAlignment(Pos.CENTER);
        PlayFieldView playFieldView = new PlayFieldView();
        SidePanelView sidePanelView = new SidePanelView(app);
        gameLayout.getChildren().addAll(playFieldView.getView(), sidePanelView.getView());
        scene = new Scene(gameLayout, width, height);
    }

    public Scene getScene() {
        return this.scene;
    }


}
