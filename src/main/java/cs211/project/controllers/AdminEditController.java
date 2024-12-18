package cs211.project.controllers;

import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.TextFilter;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class AdminEditController {
    @FXML private Label usernameLabel;
    @FXML private TextField accountnameTextField;
    @FXML private TextField newpasswordField;
    @FXML private Label roleLabel;
    @FXML private Label changeLabel;
    @FXML private Label nameEmptyLabel;
    @FXML private Circle profilepicCircle;
    private DataSource<UserList> datasource = new UserDataSource("data", "login.csv");;
    private UserList userList = datasource.readData();
    String username = (String) FXRouter.getData();
    User selectedUser = userList.findUserByUserName(username);
    @FXML public void initialize() {
        showUserInfo(selectedUser);
        nameEmptyLabel.setVisible(false);
        changeLabel.setVisible(false);

        TextFilter.safeForCSV(accountnameTextField);
        TextFilter.safeForCSV(newpasswordField);
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
    @FXML private void changeRole() {
        userList.changeRole(selectedUser.getUserName());
        updateToCSV();
        showUserInfo(selectedUser);
        changeLabel.setVisible(true);
    }
    @FXML private void banAcount() {
        selectedUser.banThisUser();
        updateToCSV();
        goAdminview();
    }
    @FXML private void changeInfoButton() {
        String newname = accountnameTextField.getText();
        String newpassword = newpasswordField.getText();

        if(!newname.isEmpty()) {
            nameEmptyLabel.setVisible(false);
            userList.changeInfo(selectedUser.getUserName(), newname, selectedUser.getPassWord());
            if(!newpassword.isEmpty()) {
                userList.changeInfo(selectedUser.getUserName(), selectedUser.getAccountName(), newpassword);
            }
            updateToCSV();
            showUserInfo(selectedUser);
            changeLabel.setVisible(true);
        } else{
            nameEmptyLabel.setVisible(true);
        }
    }

    void showUserInfo(User user) {
        usernameLabel.setText(user.getUserName());
        accountnameTextField.setText(user.getAccountName());
        newpasswordField.clear();
        roleLabel.setText(user.getRole());
        profilepicCircle.setFill(new ImagePattern(new Image("file:" + user.getProfilePicture())));
    }
    void updateToCSV() {
        datasource.writeData(userList);
    }
}
