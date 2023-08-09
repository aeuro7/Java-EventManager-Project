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

public class RegisterController {
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField conPasswordTextField;
    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private Label cPasswordErrorLabel;

    private DataSource dataSource;

    public RegisterController() {
        dataSource = new UserDataHardCode(); // Initialize your data source here
    }
    @FXML
    public void initialize() {
        // Hide error labels when the controller is initialized
        usernameErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);
        cPasswordErrorLabel.setVisible(false);
    }

    @FXML
    public void signUpButtonClick() {
        // Get user input from text fields
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = conPasswordTextField.getText();

        // Perform validation checks
        boolean isValid = true;

        if (username.isEmpty()) {
            usernameErrorLabel.setVisible(true);
            isValid = false;
        } else {
            usernameErrorLabel.setVisible(false);
        }

        if (!password.equals(confirmPassword)) {
            passwordErrorLabel.setVisible(true);
            cPasswordErrorLabel.setVisible(true);
            isValid = false;
        } else {
            passwordErrorLabel.setVisible(false);
            cPasswordErrorLabel.setVisible(false);
        }

        if (isValid) {
            // Perform the signup process here
            // For example, create a new User object and save it to a database
            User newUser = new User(username, "AccountName", password);
            dataSource.writeData(newUser); // Save the new user data

            try {
                FXRouter.goTo("main-menu"); // Redirect to a new page after successful sign-up
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void checkUsernameClick() {
        String username = usernameTextField.getText();
        UserList userList = (UserList) dataSource.readData();
        boolean isUsernameAvailable = true;

        for (User user : userList.getAllUser()) {
            if (user.isThisAccout(username)) {
                isUsernameAvailable = false;
                break;
            }
        }

        if (isUsernameAvailable) {
            usernameErrorLabel.setVisible(false);
        } else {
            usernameErrorLabel.setVisible(true);
        }
    }

    @FXML
    public void browseButtonClick() {
        // Handle browse button click
    }

    @FXML
    public void goLoginButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
