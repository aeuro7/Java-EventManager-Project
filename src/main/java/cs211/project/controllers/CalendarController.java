package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.CalendarList;
import cs211.project.models.EventList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalendarController {
    private String account;
    @FXML private GridPane calendarContrainer;
    @FXML private TextField searchBox;
    @FXML private ScrollPane scrollpain;
    private CalendarList calendarList = new CalendarList();
    private EventList eventList = new EventList();
    private int row = 1;
    @FXML public void initialize() {
        account = (String) FXRouter.getData();
        DataSource<CalendarList> dataSource = new CalendarDataSource("data", "calendar.csv");
        CalendarList fullcalendarList = dataSource.readData();

        eventList = (new EventDataSource("data", "event.csv").readData());

        MemberList memberList = (new MemberDataSource("data", "member.csv").readData());
        TeamList teamList = (new TeamDataSource("data", "team.csv").readData());

        Set<Pair> joinData = new HashSet<>();

        for (Member member : memberList.getMemberList()) {
            if (member.getUsername().equals(account) && !member.getBanStatus()) {
                joinData.add(new Pair(member.getEventID(), member.getRole()));
            }
        }

        for (Team team : teamList.getAllTeams()) {
            if (team.isInTeam(account) && !team.isThisGuyAreBaned(account)) {
                joinData.add(new Pair(team.getEventID(), team.getNameTeam()));
            }
        }

        for (Calendar calendar : fullcalendarList.getCalendars()) {
            for (Pair data : joinData) {
                if (calendar.getEventID().equals(data.getKey()) && ((data.getValue().equals("OWNER") || calendar.getFaction().equals(data.getValue())))) {
                    calendarList.addNewCalendar(calendar);
                }
            }
        }

        for (Calendar calendar : calendarList.getCalendars()) {
            showCalendar(calendar);
        }


        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchFn(newValue);
        });
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

    private void showCalendar(Calendar calendar) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cs211/project/views/calendar-tab.fxml"));
            AnchorPane calendartab = loader.load();
            CalendarTabController calendarTabController = loader.getController();
            calendarTabController.setData(calendar, eventList.findEventByID(calendar.getEventID()));

            scrollpain.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            calendarContrainer.add(calendartab, 0, row++);
            GridPane.setMargin(calendartab, new Insets(5));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void SearchFn(String searchTerm) {
        searchTerm = searchTerm.toLowerCase().trim();
        calendarContrainer.getChildren().clear();

        for (Calendar calendar : calendarList.getCalendars()) {
            if (calendar.getCalendarName().toLowerCase().contains(searchTerm)) {
                showCalendar(calendar);
            }
        }
    }
}