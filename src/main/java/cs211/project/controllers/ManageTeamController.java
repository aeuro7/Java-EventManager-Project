package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.io.IOException;

public class ManageTeamController {

    private Team selectTeam;

    @FXML
    private TableView<Team> teamTableView;

    private TeamList teamList;
    private String account = (String) FXRouter.getData();

    @FXML
    private void initialize() {
        DataSource<TeamList> dataSource = new TeamDataSource("data", "team.csv");
        teamList = dataSource.readData();
        showTable(teamList);
    }

    private void showTable(TeamList teamList) {

        TableColumn<Team, String> eventIDColumn = new TableColumn<>("Event ID");
        eventIDColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));

        TableColumn<Team, String> nameTeamColumn = new TableColumn<>("Team Name");
        nameTeamColumn.setCellValueFactory(new PropertyValueFactory<>("nameTeam"));

        TableColumn<Team, String> leaderNameColumn = new TableColumn<>("leader Name");
        leaderNameColumn.setCellValueFactory(new PropertyValueFactory<>("leaderName"));


        teamTableView.getColumns().clear();
        teamTableView.getColumns().addAll(nameTeamColumn, eventIDColumn,leaderNameColumn);

        teamTableView.getItems().clear();

        for (Team team: teamList.getAllTeams()) {
            teamTableView.getItems().add(team);
        }

        teamTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue observable, Team oldValue, Team newValue) {
                if (newValue != null) {
                    try {
                        Pair<String , Team> sender = new Pair<String, Team>(account, newValue);
                        FXRouter.goTo("manage-team-view", sender);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });





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
            FXRouter.goTo("main-menu", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoEditprofile() {
        try {
            FXRouter.goTo("profile-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}

