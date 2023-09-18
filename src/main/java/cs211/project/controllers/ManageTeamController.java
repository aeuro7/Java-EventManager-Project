package cs211.project.controllers;

import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ManageTeamController {

    private Team selectTeam;

    @FXML
    private TableView<Team> teamTableView;

    private TeamList teamList;

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

        teamTableView.getColumns().clear();
        teamTableView.getColumns().addAll(nameTeamColumn, eventIDColumn);

        teamTableView.getItems().clear();

        for (Team team: teamList.getAllTeams()) {
            teamTableView.getItems().add(team);
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

