package tetris;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputHandler {

    private final Scene scene;
    private KeyCode currentKeyPressed;

    public InputHandler(Scene scene) {
        this.scene = scene;
        this.currentKeyPressed = KeyCode.UNDEFINED;
        addKeyListeners(scene);
    }

    public KeyCode getKeyPressed() {
        return currentKeyPressed;
    }

    private void addKeyListeners(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            System.out.println(event.getCode().toString());
            if (event.getCode() == KeyCode.LEFT) {
                currentKeyPressed = KeyCode.LEFT;
            } else if (event.getCode() == KeyCode.RIGHT) {
                currentKeyPressed = KeyCode.RIGHT;
            } else if (event.getCode() == KeyCode.DOWN) {
                currentKeyPressed = KeyCode.DOWN;
            } else if (event.getCode() == KeyCode.SPACE) {
                currentKeyPressed = KeyCode.SPACE;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event) -> {
            this.currentKeyPressed = KeyCode.UNDEFINED;
        });
    }


}
