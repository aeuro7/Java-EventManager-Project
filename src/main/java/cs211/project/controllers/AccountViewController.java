package cs211.project.controllers;

import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.models.users.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.TeamDataSource;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.io.IOException;

public class AccountViewController {


    @FXML private Label nameLabel;
    @FXML private Label statusLabel;
    @FXML private Label teamNameLabel;
    @FXML private TableView<Team> teamTableView;

    private Team selectTeam;
    private TeamList teamList;
    private String account;
    @FXML
    private void initialize() {
        account = ((Pair<String , Team>) FXRouter.getData()).getKey();
        selectTeam = ((Pair<String , Team>) FXRouter.getData()).getValue();
        teamList = (new TeamDataSource("data", "team.csv")).readData();
        teamNameLabel.setText(selectTeam.getNameTeam());
        nameLabel.setText("TEST");
    }

    @FXML private void banMember() {

    }
    @FXML private void goOwner() {

    }
    @FXML private void assignTeamButton() {

    }
    @FXML private void confirmAssignTeamButton() {

    }
    @FXML private void goCalendar() {

    }
    @FXML private void goChat() {

    }
    @FXML private void promoteHandleButton() {

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
    @FXML private void goManageTeam() {
        try {;
            FXRouter.goTo("manage-team-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}

