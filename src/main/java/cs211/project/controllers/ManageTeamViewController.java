package cs211.project.controllers;

import cs211.project.models.eventHub.Member;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.models.team.TeamStaff;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.TeamDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.io.IOException;

public class ManageTeamViewController {
    @FXML private Label teamNameLabel;
    @FXML private Label eventNameLabel;
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label statusLabel;
    @FXML private Team selectTeam;



    @FXML private TableView<TeamStaff> memberTableView;

    @FXML private TeamList teamList;
    private String account;

    @FXML
    private void initialize() {
        DataSource<TeamList> dataSource = new TeamDataSource("data", "team.csv");
        teamList = dataSource.readData();
        account = ((Pair<String , Team>) FXRouter.getData()).getKey();
        selectTeam = ((Pair<String , Team>) FXRouter.getData()).getValue();
        showTable(selectTeam);
        teamNameLabel.setText(selectTeam.getNameTeam());
        eventNameLabel.setText(selectTeam.getEventID());

        memberTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameLabel.setText(newValue.getName());
                statusLabel.setText(newValue.getRole());
            }
        });

    }

    private void showTable(Team team) {
        TableColumn<TeamStaff, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<TeamStaff, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        memberTableView.getColumns().clear();
        memberTableView.getColumns().add(nameColumn);
        memberTableView.getColumns().add(roleColumn);

        nameColumn.setMinWidth(128);
        roleColumn.setMinWidth(128);


        memberTableView.getItems().clear();

        for (TeamStaff teamStaff: team.getAllTeamStaff() ) {
            memberTableView.getItems().add(teamStaff);
        }


    }


    @FXML
    public void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void gotoChat() {
        try {
            FXRouter.goTo("chat-view",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void gotoEditprofile() {
        try {
            FXRouter.goTo("profile-view",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void gotoBook() {
        try {
            FXRouter.goTo("book-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
