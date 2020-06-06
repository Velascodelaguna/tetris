package tetris;

public class TetrisAppControl {
    private TetrisApp app;

    public TetrisAppControl(TetrisApp app) {
        this.app = app;
    }

    public void setStartScreen() {
        this.app.setStartScreen();
    }

    public void setGameScreen() {
        this.app.setGameScreen();
    }

    public void endGame() {
        this.app.endGame();
    }
}
