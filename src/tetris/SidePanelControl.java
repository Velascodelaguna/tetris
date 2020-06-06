package tetris;

public class SidePanelControl {

    private final TetrisAppControl appControl;

    public SidePanelControl(TetrisAppControl appControl) {
        this.appControl = appControl;
    }

    public void restart() {
        appControl.setStartScreen();
    }

    public void quit() {
        appControl.endGame();
    }

}
