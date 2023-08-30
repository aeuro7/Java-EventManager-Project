package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProfileController {
    @FXML private Label usernameLabel;
    @FXML private TextField accountnameTextField;
    @FXML private TextField passwordField;
    @FXML private TextField newpasswordField;
    @FXML private TextField connewpasswordField;
    @FXML private Label changeLabel;
    @FXML private Label errorLabel;
    private DataSource<UserList> datasource;
    private UserList userList;
    private User account;
    void showUserInfo(User user) {
        usernameLabel.setText(user.getUserName());
        accountnameTextField.setText(user.getAccountName());
        passwordField.clear();
        newpasswordField.clear();
        connewpasswordField.clear();
    }

    @FXML public void initialize() {
        datasource = new UserDataSource("data", "login.csv");
        userList = datasource.readData();
        String username = ((User) FXRouter.getData()).getUserName();
        account = userList.findUserByUserName(username);
        showUserInfo(account);
        errorLabel.setVisible(false);
        changeLabel.setVisible(false);
    }

    @FXML public void browsepicBotton() {

    }

    @FXML public void changeInfoButton() {
        String newname = accountnameTextField.getText();
        String currentPassword = passwordField.getText();
        String newPassword = newpasswordField.getText();
        String confirmNewPassword = connewpasswordField.getText();

        if(!newname.isEmpty()) {
            errorLabel.setVisible(false);
            if(!currentPassword.isEmpty()) {
                errorLabel.setVisible(false);
                if(account.isPasswordCorrect(currentPassword)) {
                    if(newPassword.equals(confirmNewPassword)) {
                        userList.changeInfo(account.getUserName(), newname, newPassword);
                        changeLabel.setVisible(true);
                    } else {
                        errorLabel.setText("The confirm password does not match the new password.");
                        errorLabel.setVisible(true);
                    }
                } else {
                    errorLabel.setText("The current password is not correct.");
                    errorLabel.setVisible(true);
                }
            } else {
                userList.changeInfo(account.getUserName(), newname, account.getPassWord());
                changeLabel.setVisible(true);
            }
            datasource.writeData(userList);
            showUserInfo(account);
        } else{
            errorLabel.setText("Name Cannot Be Empty!");
            errorLabel.setVisible(true);
        }
    }
    @FXML public void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void goProflie() {
        try {
            FXRouter.goTo("profile-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoBooking() {
        try {
            FXRouter.goTo("book-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
