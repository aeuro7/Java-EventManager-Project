package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataHardCode;
import cs211.project.services.UserDataSource;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminEditController {
    @FXML private Label usernameLabel;
    @FXML private TextField accountnameTextField;
    @FXML private TextField newpasswordField;
    @FXML private Label roleLabel;
    private DataSource<UserList> datasource;
    private UserList userList;
    @FXML public void initialize() {
        datasource = new UserDataSource("data", "login.csv");
        userList = datasource.readData();

        String username = (String) FXRouter.getData();
        User selectedUser = userList.findUserByUserName(username);

        showUserInfo(selectedUser);
    }

    @FXML private void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void goAdminview() {
        try {
            FXRouter.goTo("admin-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void goProflie() {
        try {
            FXRouter.goTo("profile-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void changeRole() {

    }
    @FXML private void deleteAcount() {

    }
    @FXML private void changeInfoButton() {

    }

    void showUserInfo(User user) {
        usernameLabel.setText(user.getUserName());
        accountnameTextField.setText(user.getAccountName());
        newpasswordField.clear();
        roleLabel.setText(user.getRole());
    }
}
