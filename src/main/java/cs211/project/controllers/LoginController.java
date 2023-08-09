package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataHardCode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label errorLabel;
    private UserList userList;

    @FXML
    private void initialize() {
        // Initialize the userList using UserDataHardCode
        DataSource<UserList> dataSource = new UserDataHardCode();
        userList = dataSource.readData();
        errorLabel.setVisible(false);
    }

    @FXML
    private void onSignInButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (isValidUser(username, password)) {
            try {
                // Navigate to the main menu upon successful login
                FXRouter.goTo("main-menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void onCreateAcButtonClick() {
        try {
            FXRouter.goTo("reg-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void nebbHyperLink() {
        try {
            FXRouter.goTo("developer-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean isValidUser(String username, String password) {
        for (User user : userList.getAllUser()) {
            if (user.authenticate(username, password)) {
                return true;
            }
        }
        return false;
    }
}
