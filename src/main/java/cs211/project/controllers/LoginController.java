package cs211.project.controllers;

import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label errorLabel;
    private UserList userList;

    private DataSource<UserList> dataSource;

    @FXML private void initialize() {
        dataSource = new UserDataSource("data", "login.csv");
        userList = dataSource.readData();
        errorLabel.setVisible(false);
        usernameTextField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                onSignInButtonClick();
            }
        });

        passwordTextField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                onSignInButtonClick();
            }
        });
    }

    @FXML
    private void onSignInButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        User loginUser = userList.loginFn(username, password);

        if (isValid(loginUser)) {
            dataSource.writeData(userList);
            if(loginUser.isAdmin()) {
                try {
                    FXRouter.goTo("admin-view", username);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    FXRouter.goTo("main-menu", username);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
    private boolean isValid(User user) {
        return user != null;
    }
}