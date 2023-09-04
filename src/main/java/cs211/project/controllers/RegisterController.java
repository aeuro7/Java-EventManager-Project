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
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class RegisterController {
    @FXML private TextField accountnameTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField conPasswordTextField;
    @FXML private Label accountnameErrorLabel;
    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private Label cPasswordErrorLabel;
    @FXML private ImageView imageView;

    private UserList userList;
    DataSource<UserList> dataSource;
    String picturePath;
    private File selectedImageFile;
    @FXML
    public void initialize() {
        picturePath = "data/UserProfilePicture/default.png";
        imageView.setImage(new Image("file:" + picturePath));
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
        if (!accountname.isEmpty()) {
            accountnameErrorLabel.setVisible(false);
        } else {
            accountnameErrorLabel.setVisible(true);
        }

        if (!username.isEmpty()) {
            // check if usernames already exist?
            if (!userList.isUserNameExists(username)) {
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

        String profilePicturePath;
        if (selectedImageFile != null) {
            profilePicturePath = copyFile(username, selectedImageFile);
        } else {
            Path path = Path.of(picturePath);
            selectedImageFile = path.toFile();
            profilePicturePath = copyFile(username, selectedImageFile);;
        }

        if (isValid) {
            User newUser = new User(username, accountname, password);
            newUser.setProfilePicture(profilePicturePath);
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

    @FXML public void browseButtonClick(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (PNG, JPG)", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        selectedImageFile = chooser.showOpenDialog(source.getScene().getWindow());

        if (selectedImageFile != null) {
            Image selectedImage = new Image(selectedImageFile.toURI().toString());
            imageView.setImage(selectedImage);
        }
    }


    @FXML
    public void goLoginButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String copyFile(String username, File source) {
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
        finally {
            return profilePicturePath;
        }
    }
}