package cs211.project.services;

import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;

import java.security.Key;

public class TextFilter {
    public static void allowAlphanumericOnly(TextInputControl inputControl) {
        inputControl.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            char inputChar = event.getCharacter().charAt(0);
            if (!Character.isLetterOrDigit(inputChar)) {
                event.consume();
            }
        });
    }

    public static void preventSeperateOnly(TextInputControl inputControl) {
        inputControl.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (event.getCharacter().equals(",")) {
                event.consume();
            }
        });
    }

    public static void allowOnlyNumber(TextInputControl inputControl) {
        inputControl.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            char inputChar = event.getCharacter().charAt(0);
            if(!Character.isDigit(inputChar)) {
                event.consume();
            }
        });
    }
}
