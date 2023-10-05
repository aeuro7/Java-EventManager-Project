package cs211.project.services;

import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;


public class TextFilter {
    public static void safeForCSV(TextInputControl inputControl) {
        inputControl.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (event.getCharacter().equals(",") || event.getCharacter().equals("\"")) {
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
