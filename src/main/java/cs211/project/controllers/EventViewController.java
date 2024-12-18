package cs211.project.controllers;

import cs211.project.models.eventHub.Event;
import cs211.project.models.eventHub.EventList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.*;
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
    @FXML private Label startTimeBookingLabel;
    @FXML private Label endTimeBookingLabel;

    private DataSource<EventList> eventDatasource;
    private DataSource<TeamList> teamListDataSource;
    private EventList eventList;
    private String userName;
    private Event selectedEvent;
    private Team selectedTeam;
    private String text;
    private TeamList joinableTeamlist = new TeamList();
    private TeamList allTeamlist;
    private MemberList memberList = new MemberList();

    @FXML public void initialize() {
        eventDatasource = new EventDataSource("data", "event.csv");
        teamListDataSource = new TeamDataSource("data", "team.csv");
        memberList = (new MemberDataSource("data", "member.csv")).readData();
        allTeamlist = teamListDataSource.readData();
        eventList = eventDatasource.readData();

        Pair<String, String> eventData = (Pair<String, String>) FXRouter.getData();
        String eventID = eventData.getKey();
        userName = eventData.getValue();
        selectedEvent = eventList.findEventByID(eventID);

        showEventInfo(selectedEvent);
        closePopup();
        teamMaxSeatLabel.setText("00");
        text = "";
        hideJoinAudienceButton();
        hideJoinTeamButton();

        boolean isJointhisEvent = false;
        text = "";

        for(Member runner: memberList.getMemberList()) {
            if(runner.getUsername().equals(userName) && runner.getEventID().equals(selectedEvent.getEventID())) {
                if(runner.getBanStatus()) {
                    text = "BANNED";
                }else if (runner.getUsername().equals(selectedEvent.getEventOwner())) {
                    text = "This is Your Event";
                } else {
                    text = "Already join!";
                }
                isJointhisEvent = true;
                break;
            }
        }

        if(!isJointhisEvent) {
            for(Team runner: allTeamlist.getAllTeams()) {
                if(runner.getEventID().equals(selectedEvent.getEventID()) && runner.isInTeam(userName)) {
                    if (runner.isThisGuyAreBaned(userName)) {
                        text = "BANNED";
                    } else {
                        text = "Already join team!";
                    }
                    isJointhisEvent = true;
                    break;
                }
            }
        }

        if(!isJointhisEvent) {
            long currentTime = System.currentTimeMillis();
            if(selectedEvent.getStartBookingTime() < currentTime && selectedEvent.getDueBookingTime() > currentTime) {
                showJoinAudienceButton();
            }
            for(Team runner: allTeamlist.getAllTeams()) {
                if(runner.getEventID().equals(selectedEvent.getEventID()) && runner.getStartJoin() < System.currentTimeMillis() && runner.getEndJoin() > System.currentTimeMillis()) {
                    joinableTeamlist.addTeam(runner);
                }
            }
            if(!joinableTeamlist.getAllTeams().isEmpty()) {
                showJoinTeamButton();
            }
        }
        textLabel.setText(text);
    }

    private void hideJoinAudienceButton() {
        joinButton.setVisible(false);
    }

    private void showJoinAudienceButton() {
        joinButton.setVisible(true);
    }

    private void hideJoinTeamButton() {
        joinMemberButton.setVisible(false);
    }
    private void showJoinTeamButton() {
        joinMemberButton.setVisible(true);
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
        startTimeBookingLabel.setText(formatTimestamp(event.getStartBookingTime()));
        endTimeBookingLabel.setText(formatTimestamp(event.getDueBookingTime()));
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
            teamListDataSource.writeData(allTeamlist);
            goMainMenu();
        }
    }
    @FXML private void joinStaff() {
        teamPickerPopup.setVisible(true);

        TableColumn<Team, String> nameColumn = new TableColumn<>("Team Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameTeam"));

        listTeamTableView.getColumns().clear();

        setCenterAlignment(nameColumn);
        nameColumn.setMinWidth(200);

        listTeamTableView.getColumns().add(nameColumn);

        listTeamTableView.getItems().clear();

        for (Team team: joinableTeamlist.getAllTeams()) {
            listTeamTableView.getItems().add(team);
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

    @FXML
    public void goCredit() {
        try {
            FXRouter.goTo("credit-view", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }

}