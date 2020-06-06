package tetris;

public class TetrisAppControl {
    private TetrisApp app;

    public TetrisAppControl(TetrisApp app) {
        this.app = app;
    }

    public void setStartScene() {
        this.app.setStartScreen();
    }

    public void setGameScreen() {
        this.app.setGameScreen();
    }
}
