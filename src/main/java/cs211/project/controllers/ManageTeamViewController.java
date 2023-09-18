package cs211.project.controllers;

import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.TeamDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ManageTeamViewController {
    @FXML private Label teamNameLabel;
    @FXML private Label eventNameLabel;
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label statusLabel;
    @FXML private Team selectTeam;

    @FXML private TableView<Team> memberTableView;

    @FXML private TeamList teamList;

    @FXML
    private void initialize() {
        DataSource<TeamList> dataSource = new TeamDataSource("data", "team.csv");
        teamList = dataSource.readData();
        showTable(teamList);
    }

    private void showTable(TeamList teamList) {
        TableColumn<Team, String> nameTeamColumn = new TableColumn<>("Team Name");
        nameTeamColumn.setCellValueFactory(new PropertyValueFactory<>("nameTeam"));

        TableColumn<Team, String> eventIDColumn = new TableColumn<>("Event ID");
        eventIDColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));

        memberTableView.getColumns().clear();
        memberTableView.getColumns().addAll(nameTeamColumn, eventIDColumn);

        memberTableView.getItems().clear();

        for (Team team: teamList.getAllTeams()) {
            memberTableView.getItems().add(team);
        }
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
    public void gotoChat() {
        try {
            FXRouter.goTo("chat-view");
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
    @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void gotoCalendar() {
        try {
            FXRouter.goTo("calendar-view");
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
