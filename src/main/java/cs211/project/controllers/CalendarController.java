package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.CalendarList;
import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CalendarController {
    private String account;
    @FXML private TableView<Calendar> calendarTableView;
    @FXML private TextField searchBox;
    @FXML private Label eventnameLabel;
    @FXML private Label starttimeLabel;
    @FXML private Label duetimeLabel;
    @FXML private Label infoLabel;
    @FXML private Circle eventpicCircle;
    private CalendarList calendarList = new CalendarList();
    private EventList eventList = new EventList();
    @FXML public void initialize() {
        account = (String) FXRouter.getData();
        DataSource<CalendarList> dataSource = new CalendarDataSource("data", "calendar.csv");
        CalendarList fullcalendarList = dataSource.readData();
        clearInfo();

        eventList = (new EventDataSource("data", "event.csv").readData());

        MemberList memberList = (new MemberDataSource("data", "member.csv").readData());
        TeamList teamList = (new TeamDataSource("data", "team.csv").readData());

        List<Pair> joinData = new ArrayList<>();

        for(Member member: memberList.getMemberList()) {
            if(member.getUsername().equals(account)) {
                joinData.add(new Pair(member.getEventID(), member.getRole()));
            }
        }
        for(Team team: teamList.getAllTeams()) {
            if(team.isInTeam(account)) {
                joinData.add(new Pair(team.getEventID(), team.getNameTeam()));
            }
        }

        for (Pair data : joinData) {
            for (Calendar calendar : fullcalendarList.getCalendars()) {

                if(calendar.getEventID().equals(data.getKey()) && data.getValue().equals("OWNER")) {
                    calendarList.addNewCalendar(calendar);
                }
                else if (calendar.getEventID().equals(data.getKey()) && calendar.getFaction().equals(data.getValue())) {
                    calendarList.addNewCalendar(calendar);
                }

            }
        }

        showTable();

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchFn(newValue);
        });

        calendarTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Calendar>() {
            @Override
            public void changed(ObservableValue<? extends Calendar> observable, Calendar oldValue, Calendar newValue) {
                if (newValue == null) {
                    clearInfo();
                } else {
                    showInfo(newValue);
                }
            }
        });
    }

    private void clearInfo() {
        eventnameLabel.setText("");
        starttimeLabel.setText("");
        duetimeLabel.setText("");
        infoLabel.setText("");
        eventpicCircle.setFill(new ImagePattern(new Image("file:" + "data/EventPicture/default.png")));
    }

    private void showInfo(Calendar calendar) {
        Event selectEvent = eventList.findEventByID(calendar.getEventID());
        eventnameLabel.setText(selectEvent.getEventName());
        starttimeLabel.setText(formatTimestamp(calendar.getStartTime()));
        duetimeLabel.setText(formatTimestamp(calendar.getDueTime()));
        infoLabel.setText(calendar.getDetail());
        eventpicCircle.setFill(new ImagePattern(new Image("file:" + selectEvent.getEventPicture())));
    }

    @FXML public void goLogout() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goProflie() {
        try {
            FXRouter.goTo("profile-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goMain() {
        try {
            FXRouter.goTo("main-menu", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goChat() {
        try {
            FXRouter.goTo("chat-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showTable() {
        TableColumn<Calendar, String> activityNameColumn = new TableColumn<>("Activity");
        activityNameColumn.setCellValueFactory(new PropertyValueFactory<>("calendarName"));

        TableColumn<Calendar, String> factionColumn = new TableColumn<>("Faction");
        factionColumn.setCellValueFactory(new PropertyValueFactory<>("faction"));

        TableColumn<Calendar, String> startTime = new TableColumn<>("Start-time");
        startTime.setCellValueFactory(cellData -> {
            long timestamp = cellData.getValue().getStartTime();
            String formattedTimestamp = formatTimestamp(timestamp); // Format the timestamp
            return new SimpleStringProperty(formattedTimestamp);
        });

        TableColumn<Calendar, String> dueTime = new TableColumn<>("Due-time");
        dueTime.setCellValueFactory(cellData -> {
            long timestamp = cellData.getValue().getDueTime();
            String formattedTimestamp = formatTimestamp(timestamp); // Format the timestamp
            return new SimpleStringProperty(formattedTimestamp);
        });

        calendarTableView.getColumns().clear();
        calendarTableView.getColumns().add(activityNameColumn);
        calendarTableView.getColumns().add(factionColumn);
        calendarTableView.getColumns().add(startTime);
        calendarTableView.getColumns().add(dueTime);

        calendarTableView.getItems().clear();

        for (Calendar calendar: calendarList.getCalendars()) {
            calendarTableView.getItems().add(calendar);
        }
    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }
    private void SearchFn(String searchTerm) {
        searchTerm = searchTerm.toLowerCase().trim();
        calendarTableView.getItems().clear();

        for (Calendar calendar : calendarList.getCalendars()) {
            if (calendar.getCalendarName().toLowerCase().contains(searchTerm)) {
                calendarTableView.getItems().add(calendar);
            }
        }
    }
}