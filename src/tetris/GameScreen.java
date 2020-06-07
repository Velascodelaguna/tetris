package tetris;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class GameScreen {


    private final Scene scene;
    private PlayFieldView playFieldView;
    private SidePanelView sidePanelView;
    private long startTime;

    public GameScreen(TetrisAppControl app, int width, int height) {

        var gameLayout = new HBox();
        gameLayout.setAlignment(Pos.CENTER);
        playFieldView = new PlayFieldView();
        sidePanelView = new SidePanelView(app);
        gameLayout.getChildren().addAll(playFieldView.getView(), sidePanelView.getView());
        scene = new Scene(gameLayout, width, height);
    }

    public Scene getScene() {
        return this.scene;
    }


    public void gameLoopStart() {
        startTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoop(now);
            }
        }.start();
    }

    private void gameLoop(long now) {
        // update every 1 second
        if (now - startTime > 1000000000) {
            startTime = now;
            playFieldView.update();
        }
    }


}
