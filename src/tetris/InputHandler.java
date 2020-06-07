package tetris;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputHandler {

    private KeyCode pressedKey;
    public InputHandler(Scene scene) {
        this.pressedKey = KeyCode.UNDEFINED;
        addKeyListeners(scene);
    }

    public KeyCode getKeyPressed() {
        if (this.pressedKey != null) {
            return this.pressedKey;
        } else {
            return KeyCode.UNDEFINED;
        }
    }

    private void addKeyListeners(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            KeyCode currentKeyPressed = KeyCode.UNDEFINED;
            if (event.getCode() == KeyCode.LEFT) {
                currentKeyPressed = KeyCode.LEFT;
            } else if (event.getCode() == KeyCode.RIGHT) {
                currentKeyPressed = KeyCode.RIGHT;
            } else if (event.getCode() == KeyCode.DOWN) {
                currentKeyPressed = KeyCode.DOWN;
            } else if (event.getCode() == KeyCode.SPACE) {
                currentKeyPressed = KeyCode.SPACE;
            }
            this.pressedKey = currentKeyPressed;
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event) -> {
            this.pressedKey = KeyCode.UNDEFINED;
        });
    }

    public void resetKeyPressed() {
        this.pressedKey = KeyCode.UNDEFINED;
    }

}
