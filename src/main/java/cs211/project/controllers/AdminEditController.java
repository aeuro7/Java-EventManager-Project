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
    private DataSource<UserList> datasource = new UserDataSource("data", "login.csv");;
    private UserList userList = datasource.readData();
    String username = (String) FXRouter.getData();
    User selectedUser = userList.findUserByUserName(username);
    @FXML public void initialize() {
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
        userList.changeRole(selectedUser.getUserName());
        updateToCSV();
        showUserInfo(selectedUser);
    }
    @FXML private void deleteAcount() {
        userList.deleteThisAccount(selectedUser.getUserName());
        updateToCSV();
        goAdminview();
    }
    @FXML private void changeInfoButton() {
        String newname = accountnameTextField.getText();
        String newpassword = newpasswordField.getText();

        if(!newname.isEmpty() && !newpassword.isEmpty()) {
            userList.changeInfo(selectedUser.getUserName(), newname, newpassword);
        } else {
            //show error label
        }
        updateToCSV();
        showUserInfo(selectedUser);
    }

    void showUserInfo(User user) {
        usernameLabel.setText(user.getUserName());
        accountnameTextField.setText(user.getAccountName());
        newpasswordField.clear();
        roleLabel.setText(user.getRole());
    }
    void updateToCSV() {
        datasource.writeData(userList);
    }
}
