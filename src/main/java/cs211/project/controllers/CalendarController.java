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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    @FXML private GridPane calendarContrainer;
    @FXML private TextField searchBox;
    @FXML private ScrollPane scrollpain;
    private CalendarList calendarList = new CalendarList();
    private EventList eventList = new EventList();

    private int column = 0;
    private int row = 1;
    @FXML public void initialize() {
        account = (String) FXRouter.getData();
        DataSource<CalendarList> dataSource = new CalendarDataSource("data", "calendar.csv");
        CalendarList fullcalendarList = dataSource.readData();

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

        for (Calendar calendar : calendarList.getCalendars()) {
            showEvent(calendar);
        }

//        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
//            SearchFn(newValue);
//        });
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

    private void showEvent(Calendar calendar) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cs211/project/views/calendar-tab.fxml"));
            AnchorPane calendartab = loader.load();
            CalendarTabController calendarTabController = loader.getController();
            calendarTabController.setData(calendar, eventList.findEventByID(calendar.getEventID()));

            if(column == 1) {
                column = 0;
                row++;
            }
            scrollpain.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            calendarContrainer.add(calendartab, column++, row);
            GridPane.setMargin(calendartab, new Insets(5));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }
//    private void SearchFn(String searchTerm) {
//        searchTerm = searchTerm.toLowerCase().trim();
//        calendarContrainer.getChildren().clear();
//
//        if (calendar.getCalendarName().toLowerCase().contains(searchTerm)) {
//            showEvent(calendar);
//        }
//    }
}