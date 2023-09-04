package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ProfileController {
    @FXML private Label usernameLabel;
    @FXML private TextField accountnameTextField;
    @FXML private TextField passwordField;
    @FXML private TextField newpasswordField;
    @FXML private TextField connewpasswordField;
    @FXML private Label changeLabel;
    @FXML private Label errorLabel;
    @FXML private ImageView profilePic;
    private DataSource<UserList> datasource;
    private UserList userList;
    private User account;
    private File selectedImageFile;
    void showUserInfo(User user) {
        selectedImageFile = new File(user.getProfilePicture());
        profilePic.setImage(new Image(selectedImageFile.toURI().toString()));
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

    @FXML public void browseButtonClick(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (PNG, JPG)", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        selectedImageFile = chooser.showOpenDialog(source.getScene().getWindow());

        if (selectedImageFile != null) {
            Image selectedImage = new Image(selectedImageFile.toURI().toString());
            profilePic.setImage(selectedImage);
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
    } @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event");
        } catch (IOException e) {
            throw new RuntimeException(e);
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
}
