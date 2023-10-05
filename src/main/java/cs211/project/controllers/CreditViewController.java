package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataSource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.io.IOException;

public class CreditViewController {
    private String account = (String) FXRouter.getData();
    private DataSource<UserList> datasourceUser;

    @FXML private ImageView profileImageView;
    @FXML private Label nameLabel;
    @FXML private Label idLabel;
    @FXML private Label githubLabel;

    @FXML public void initialize() {
        clearDisplay();
    }

    @FXML public void dev1ShowInfo() {
        Image image = new Image(getClass().getResourceAsStream("/images/dev1.png"));
        profileImageView.setImage(image);
        nameLabel.setText("Aphisit Prasertvesyakorn");
        idLabel.setText("6510451085");
        githubLabel.setText("Professors001");
    }
    @FXML public void dev2ShowInfo() {
        Image image = new Image(getClass().getResourceAsStream("/images/dev2.png"));
        profileImageView.setImage(image);
        nameLabel.setText("Tibet Charoensripaiboon");
        idLabel.setText("6510450399");
        githubLabel.setText("aeuro7");
    }
    @FXML public void dev3ShowInfo() {
        Image image = new Image(getClass().getResourceAsStream("/images/dev3.png"));
        profileImageView.setImage(image);
        nameLabel.setText("Apinya Limhoub");
        idLabel.setText("6510451077");
        githubLabel.setText("bvnnal");
    }
    @FXML public void dev4ShowInfo() {
        Image image = new Image(getClass().getResourceAsStream("/images/dev4.png"));
        profileImageView.setImage(image);
        nameLabel.setText("Nutpawee Kawee");
        idLabel.setText("6510450534");
        githubLabel.setText("nammeyyy");
    }
    @FXML public void clearDisplay() {
        Image image = new Image(getClass().getResourceAsStream("/images/profile.png"));
        profileImageView.setImage(image);
        nameLabel.setText("");
        idLabel.setText("");
        githubLabel.setText("");
    }

    @FXML
    public void goLogout() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goProflie() {
        try {
            FXRouter.goTo("profile-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
    @FXML
    public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goMain() {
        try {
            FXRouter.goTo("main-menu", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goChat() {
        try {
            FXRouter.goTo("chat-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goCredit() {
        try {
            FXRouter.goTo("credit-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }
