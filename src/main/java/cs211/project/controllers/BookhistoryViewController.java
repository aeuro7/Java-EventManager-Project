package cs211.project.controllers;

import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataHardCode;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class BookhistoryViewController {

    @FXML private Group nowOn;
    @FXML private Group nowCom;



    @FXML
    private void initialize() {
        nowCom.setVisible(false);
        nowOn.setVisible(false);

    }

    @FXML
    private void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML private void gotoEditprofile() {
        try {
            FXRouter.goTo("profile-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML private void showOngoing() {
            nowOn.setVisible(true);
            nowCom.setVisible(false);


    }

    @FXML private void showCompleted() {
        nowCom.setVisible(true);
        nowOn.setVisible(false);


    }


}

