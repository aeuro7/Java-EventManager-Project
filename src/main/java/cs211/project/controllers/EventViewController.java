package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.chats.Chat;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.models.users.User;
import cs211.project.services.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventViewController {
    @FXML private Label eventNameLabel;
    @FXML private Label detailLabel;
    @FXML private Label seatLeftLabel;
    @FXML private Label dueTimeLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label locationLabel;
    @FXML private Label maxSeatLabel;
    @FXML private Label teamNameLabel;
    @FXML private Label teamLeaderLabel;
    @FXML private Label teamSeatleftLabel;
    @FXML private Label teamMaxSeatLabel;
    @FXML private Circle eventPicCircle;
    @FXML private AnchorPane teamPickerPopup;
    @FXML private TableView<Team> listTeamTableView;
    @FXML private Button joinButton;
    @FXML private Button joinMemberButton;
    @FXML private Label textLabel;
    private DataSource<EventList> eventDatasource;
    private DataSource<TeamList> teamListDataSource;
    private TeamList teamlist;
    private EventList eventList;
    private String userName;
    private Event selectedEvent;
    private Team selectedTeam;

    private TeamList teamList = (new TeamDataSource("data", "team.csv").readData());
    private MemberList memberList = (new MemberDataSource("data", "member.csv").readData());

    @FXML public void initialize() {
        eventDatasource = new EventDataSource("data", "event.csv");
        teamListDataSource = new TeamDataSource("data", "team.csv");
        eventList = eventDatasource.readData();

        String eventname = ((Pair<String, String>) FXRouter.getData()).getKey();
        userName = ((Pair<String, String>) FXRouter.getData()).getValue();
        selectedEvent = eventList.findEventByEventName(eventname);
        showEventInfo(selectedEvent);
        closePopup();
        teamMaxSeatLabel.setText("00");
        showJoinButton();

        if(selectedEvent.getLeftSeat() == 0) {
            hideJoinButton("Already Full");
        } else {
            for(Member member: memberList.getMemberList()) {
                if(member.getUsername().equals(userName) && member.getEventID().equals(selectedEvent.getEventID())) {
                    hideJoinButton("Already Join");
                }
            }
            for(Team team: teamList.getAllTeams()) {
                if(team.isInTeam(userName) && team.getEventID().equals(selectedEvent.getEventID())) {
                    hideJoinButton("Already Join");
                }
            }
        }
    }

    private void showJoinButton() {
        joinButton.setVisible(true);
        joinMemberButton.setVisible(true);
        textLabel.setText("");
    }
    private void hideJoinButton(String text) {
        joinButton.setVisible(false);
        joinMemberButton.setVisible(false);
        textLabel.setText(text);
    }

    private void showEventInfo(Event event) {
        eventNameLabel.setText(event.getEventName());
        startTimeLabel.setText(formatTimestamp(event.getStartTime()));
        dueTimeLabel.setText(formatTimestamp(event.getDueTime()));
        locationLabel.setText(event.getLocation());
        detailLabel.setText(event.getInfo());
        seatLeftLabel.setText(String.valueOf(event.getLeftSeat()));
        maxSeatLabel.setText(String.valueOf(event.getMaxSeat()));
        eventPicCircle.setFill(new ImagePattern(new Image("file:" + event.getEventPicture())));
        teamPickerPopup.setVisible(false);
    }
    @FXML private void joinEvent() {
        DataSource<MemberList> memberListDataSource = new MemberDataSource("data", "member.csv");
        MemberList memberList = memberListDataSource.readData();
        memberList.addMember(userName, selectedEvent.getEventID());
        memberListDataSource.writeData(memberList);
        selectedEvent.boooking();
        eventDatasource.writeData(eventList);
        memberListDataSource.writeData(memberList);
        showEventInfo(selectedEvent);
        goMainMenu();
    }
    private void clearInfo() {
        teamNameLabel.setText("");
        teamLeaderLabel.setText("");
        teamSeatleftLabel.setText("00");
        teamMaxSeatLabel.setText("00");
    }
    private void showTeamInfo(Team team) {
        clearInfo();
        teamNameLabel.setText(team.getNameTeam());
        teamLeaderLabel.setText(team.getLeaderName());
        teamSeatleftLabel.setText(String.valueOf(team.getSeatLeft()));
        teamMaxSeatLabel.setText(String.valueOf(team.getMaxStaff()));
    }
    @FXML private void goMainMenu() {
        try {
            FXRouter.goTo("main-menu", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void goChat() {
        try {
            FXRouter.goTo("chat-view", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void goProflie() {
        try {
            FXRouter.goTo("profile-view", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void joinTeambutton() {
        if(selectedTeam.getSeatLeft() > 0) {
            selectedTeam.addTeamStaff(userName);
            showTeamInfo(selectedTeam);
            teamListDataSource.writeData(teamlist);
            goMainMenu();
        }
    }
    @FXML private void joinStaff() {
        teamPickerPopup.setVisible(true);
        teamlist = teamListDataSource.readData();

        TableColumn<Team, String> nameColumn = new TableColumn<>("Team Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameTeam"));

        listTeamTableView.getColumns().clear();

        setCenterAlignment(nameColumn);
        nameColumn.setMinWidth(200);

        listTeamTableView.getColumns().add(nameColumn);

        listTeamTableView.getItems().clear();

        for (Team team: teamlist.getAllTeams()) {
            if(team.getEventID().equals(selectedEvent.getEventID()) && team.getSeatLeft() > 0) {
                listTeamTableView.getItems().add(team);
            }
        }
        listTeamTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue observable, Team oldValue, Team newValue) {
                if (newValue != null) {
                    clearInfo();
                    showTeamInfo(newValue);
                    selectedTeam = newValue;
                }
            }
        });
    }
    private void setCenterAlignment(TableColumn<Team, String> column) {
        column.setCellFactory(tc -> new TableCell<Team, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(null);
                } else {
                    setText(item);
                    setAlignment(javafx.geometry.Pos.CENTER);
                }
            }
        });
    }
    @FXML public void closePopup() {
        teamPickerPopup.setVisible(false);
    }

    @FXML public void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }


}
