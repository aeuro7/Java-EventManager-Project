package cs211.project.controllers;

import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AdminEditProfileController {
    @FXML private Label usernameLabel;
    @FXML private TextField accountnameTextField;
    @FXML private TextField passwordField;
    @FXML private TextField newpasswordField;
    @FXML private TextField connewpasswordField;
    @FXML private Label changeLabel;
    @FXML private Label errorLabel;
    @FXML private Circle profilepicCircle;

    private DataSource<UserList> datasource;
    private UserList userList;
    private User account;
    private File selectedImageFile;
    void showUserInfo(User user) {
        Image profileImage = new Image("file:" + user.getProfilePicture());
        profilepicCircle.setFill(new ImagePattern(profileImage));
        usernameLabel.setText(user.getUserName());
        accountnameTextField.setText(user.getAccountName());
        passwordField.clear();
        newpasswordField.clear();
        connewpasswordField.clear();
    }

    @FXML public void initialize() {
        datasource = new UserDataSource("data", "login.csv");
        userList = datasource.readData();
        String username = (String) FXRouter.getData();
        account = userList.findUserByUserName(username);
        showUserInfo(account);
        errorLabel.setVisible(false);
        changeLabel.setVisible(false);
    }

    @FXML public void browseButtonClick(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (PNG, JPG)", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        selectedImageFile = chooser.showOpenDialog(source.getScene().getWindow());

        if (selectedImageFile != null) {
            Image profileImage = new Image(selectedImageFile.toURI().toString());
            ImagePattern imagePattern = new ImagePattern(profileImage);
            profilepicCircle.setFill(imagePattern);
        }
        copyFile(account.getUserName(), selectedImageFile);
        changeLabel.setVisible(true);
    }

    @FXML public void changeInfoButton() {
        String newname = accountnameTextField.getText();
        String currentPassword = passwordField.getText();
        String newPassword = newpasswordField.getText();
        String confirmNewPassword = connewpasswordField.getText();

        if(!newname.isEmpty()) {
            errorLabel.setVisible(false);
            changeLabel.setVisible(false);
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
            changeLabel.setVisible(false);
        }
    }
    private void copyFile(String username, File source) {
        String userProfilePictureFolder = "data/UserProfilePicture";
        File userProfilePictureDir = new File(userProfilePictureFolder);
        if (!userProfilePictureDir.exists()) {
            userProfilePictureDir.mkdirs();
        }
        String imageFilename = username + ".png";
        String profilePicturePath = userProfilePictureFolder + File.separator + imageFilename;
        try {
            File destinationFile = new File(profilePicturePath);
            Files.copy(source.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void goAdminview() {
        try {
            FXRouter.goTo("admin-view", account.getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
