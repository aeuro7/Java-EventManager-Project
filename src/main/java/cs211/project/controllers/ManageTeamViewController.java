package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.models.team.TeamStaff;
import cs211.project.models.users.User;
import cs211.project.services.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.io.IOException;

public class ManageTeamViewController {
    @FXML private Label teamNameLabel;
    @FXML private Label eventNameLabel;
    @FXML private Label nameLabel;
    @FXML private Label statusLabel;
    @FXML private TableView<TeamStaff> memberTableView;
    @FXML private Button editButton;
    @FXML private Button banButton;
    @FXML private Circle profilepicCircle;
    private Team selectTeam;
    private TeamList teamList;
    private String account;
    private TeamStaff selectStaff;
    private DataSource<TeamList> dataSource;

    @FXML
    private void initialize() {
        dataSource = new TeamDataSource("data", "team.csv");
        EventList eventList = (new EventDataSource("data", "event.csv").readData());
        teamList = dataSource.readData();
        account = ((Pair<String , Team>) FXRouter.getData()).getKey();
        selectTeam = ((Pair<String , Team>) FXRouter.getData()).getValue();
        Event thisEvent = eventList.findEventByID(selectTeam.getEventID());
        showTable(selectTeam);
        teamNameLabel.setText(selectTeam.getNameTeam());
        eventNameLabel.setText(thisEvent.getEventName());
        hideButton();

        memberTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectStaff = newValue;
                nameLabel.setText(newValue.getName());
                statusLabel.setText(newValue.getRole());
                User user = (new UserDataSource("data", "login.csv")).readData().findUserByUserName(newValue.getName());
                profilepicCircle.setFill(new ImagePattern(new Image("file:" + user.getProfilePicture())));
                if(thisEvent.getEventOwner().equals(account)) {
                    showButton();
                } else if(selectTeam.getLeaderName().equals(account) && !(newValue.getName().equals(account))) {
                    showButton();
                } else {
                    hideButton();
                }
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

    private void showButton() {
        editButton.setVisible(true);
        banButton.setVisible(true);
    }
    private void hideButton() {
        editButton.setVisible(false);
        banButton.setVisible(false);
    }

    @FXML private void banThisMember() {
        if(selectStaff != null) {
            selectStaff.setRole("BAN");
            dataSource.writeData(teamList);
        }
    }
    @FXML private void editHandleButton() {

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
