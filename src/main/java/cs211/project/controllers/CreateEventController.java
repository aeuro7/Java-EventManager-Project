package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateEventController {
    @FXML private TextField eventnameTextField;
    @FXML private TextField startDateTextField;
    @FXML private TextField startTimeTextField;
    @FXML private TextField dueDateTextField;
    @FXML private TextField dueTimeTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField audienceTextField;
    @FXML private TextField limitStaffTextField;
    @FXML private TextField descriptionTextField;
    @FXML public void initialize() {

    }
    @FXML
    public void browsepicBotton() {

    }

    @FXML public void createEventButton() {
        try {
            FXRouter.goTo("main-menu");
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
    @FXML private void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}