package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;

import java.io.IOException;

public class ProfileController {

    @FXML public void browsepicBotton() {

    }

    @FXML public void changeInfoButton() {

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
}
