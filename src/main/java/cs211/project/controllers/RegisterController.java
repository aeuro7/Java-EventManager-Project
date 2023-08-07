package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField conPasswordTextField;

    @FXML
    public void signUpButtonClick() {
        try {
            FXRouter.goTo("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void browseButtonClick() {
        try {
            FXRouter.goTo("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
