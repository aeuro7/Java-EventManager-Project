package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataHardCode;
import cs211.project.services.UserDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    @FXML private TextField accountnameTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField conPasswordTextField;
    @FXML private Label accountnameErrorLabel;
    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private Label cPasswordErrorLabel;

    private UserList userList;
    DataSource<UserList> dataSource;
    @FXML
    public void initialize() {
        // Hide error labels when the controller is initialized
        accountnameErrorLabel.setVisible(false);
        usernameErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);
        cPasswordErrorLabel.setVisible(false);
        dataSource = new UserDataSource("data", "login.csv");
        userList = dataSource.readData();
    }

    @FXML
    public void signUpButtonClick() {
        // Get user input from text fields
        String accountname = accountnameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = conPasswordTextField.getText();

        // Perform validation checks
        boolean isValid = false;
        if(!accountname.isEmpty()) {
            accountnameErrorLabel.setVisible(false);
        } else{
            accountnameErrorLabel.setVisible(true);
        }

        if (!username.isEmpty()) {
            // check if usernames is already existed?
            if(!userList.isUserNameExists(username)) {
                usernameErrorLabel.setVisible(false);
                isValid = true;
            } else {
                usernameErrorLabel.setVisible(true);
            }
        } else {
            usernameErrorLabel.setVisible(true);
        }

        if (!password.equals(confirmPassword) || password.isEmpty()) {
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
            User newUser = new User(username, accountname, password);
            userList.addUser(newUser);
            dataSource.writeData(userList);

            try {
                FXRouter.goTo("login-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void checkUsernameClick() {
        String username = usernameTextField.getText();

        if (!username.isEmpty()) {
            if(!userList.isUserNameExists(username)) {
                usernameErrorLabel.setVisible(false);
            } else {
                usernameErrorLabel.setVisible(true);
            }
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