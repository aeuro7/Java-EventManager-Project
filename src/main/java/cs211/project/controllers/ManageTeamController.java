package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.Team.Team;
import cs211.project.models.Team.TeamList;
import cs211.project.models.chats.Chat;
import cs211.project.models.chats.ChatList;
import cs211.project.models.users.UserList;
import cs211.project.services.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ManageTeamController {

    private Team selectTeam;
    private TableView<Team> teamTableView;

    private TeamList teamList;

    @FXML
    private void initialize() {
        TeamHardCode dataSource = new TeamHardCode();
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

        teamTableView.getItems().addAll(teamList.getAllTeams());
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

