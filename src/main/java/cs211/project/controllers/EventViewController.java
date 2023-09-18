package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private DataSource<EventList> datasource;
    private DataSource<TeamList> teamListDataSource;
    private TeamList teamlist;
    private EventList eventList;
    private String userName;
    private Event selectedEvent;
    private Team selectedTeam;

    @FXML public void initialize() {
        datasource = new EventDataSource("data", "event.csv");
        teamListDataSource = new TeamDataSource("data", "team.csv");
        eventList = datasource.readData();

        String eventname = ((Pair<String, String>) FXRouter.getData()).getKey();
        userName = ((Pair<String, String>) FXRouter.getData()).getValue();
        selectedEvent = eventList.findEventByEventName(eventname);
        showEventInfo(selectedEvent);
        closePopup();
        teamMaxSeatLabel.setText(String.valueOf(selectedEvent.getLimitStaffPT()));
        listTeamTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue<? extends Team> observable, Team oldValue, Team newValue) {
                if (newValue == null) {
                    clearInfo();
                } else {
                    showTeamInfo(newValue);
                    selectedTeam = newValue;
                }
            }
        });
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
    }
    private void clearInfo() {
        teamNameLabel.setText("");
        teamLeaderLabel.setText("");
        teamSeatleftLabel.setText("00");
    }
    private void showTeamInfo(Team team) {
        teamNameLabel.setText(team.getNameTeam());
        teamLeaderLabel.setText(team.getLeaderName());
        teamSeatleftLabel.setText(String.valueOf(team.getSeatLeft()));
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
    @FXML private void goEdit() {
        try {
            FXRouter.goTo("owner-event", selectedEvent);
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
    @FXML private void joinStaff() {
        teamPickerPopup.setVisible(true);
        teamlist = teamListDataSource.readData();

        listTeamTableView.getItems().clear();

        TableColumn<Team, String> nameColumn = new TableColumn<>("Team Name");
        nameColumn.setCellValueFactory(param -> {
            Object obj = param.getValue();
            if (obj instanceof Member) {
                Member member = (Member) obj;
                return new SimpleStringProperty(member.getUsername());
            } else if (obj instanceof Team) {
                Team team = (Team) obj;
                return new SimpleStringProperty(team.getNameTeam());
            } else {
                return new SimpleStringProperty("");
            }
        });

        listTeamTableView.getColumns().clear();

        setCenterAlignment(nameColumn);
        nameColumn.setMinWidth(200);

        listTeamTableView.getColumns().add(nameColumn);

        for (Team team: teamlist.getAllTeams()) {
            if(team.getEventID().equals(selectedEvent.getEventID())) {
                listTeamTableView.getItems().add(team);
            }
        }
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
    @FXML public void joinTeambutton() {
        selectedTeam.addTeamStaff(userName);
        showTeamInfo(selectedTeam);
        teamListDataSource.writeData(teamlist);
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
