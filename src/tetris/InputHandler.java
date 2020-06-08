package tetris;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class InputHandler {

    private KeyCode pressedKey;
    private final HashSet<KeyCode> validKeys;
    public InputHandler(Scene scene) {
        this.pressedKey = KeyCode.UNDEFINED;
        this.validKeys = new HashSet<>();
        setValidKeys();
        addKeyListeners(scene);
    }

    private void setValidKeys() {
        validKeys.addAll(Arrays.asList(
            KeyCode.LEFT,
            KeyCode.RIGHT,
            KeyCode.DOWN,
            KeyCode.SPACE,
            KeyCode.Z,
            KeyCode.X
        ));
    }

    public KeyCode getKeyPressed() {
        return Objects.requireNonNullElse(this.pressedKey, KeyCode.UNDEFINED);
    }

    private void addKeyListeners(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (this.validKeys.contains(event.getCode())) {
                this.pressedKey = event.getCode();
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event) -> {
            this.pressedKey = KeyCode.UNDEFINED;
        });
    }

    public void resetKeyPressed() {
        this.pressedKey = KeyCode.UNDEFINED;
    }

}
