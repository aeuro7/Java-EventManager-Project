package cs211.project.controllers;

import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class ManageTeamController {

    @FXML
    private void initialize() {

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



}

