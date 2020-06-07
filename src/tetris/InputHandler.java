package tetris;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.LinkedList;
import java.util.Queue;

public class InputHandler {

    private Queue<KeyCode> pressedKeys;
    public InputHandler(Scene scene) {
        pressedKeys = new LinkedList<>();
        addKeyListeners(scene);
    }

    public KeyCode getKeyPressed() {
        KeyCode keyPressed = pressedKeys.poll();
        if (keyPressed != null) {
            return keyPressed;
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
            pressedKeys.offer(currentKeyPressed);
        });
    }


}
