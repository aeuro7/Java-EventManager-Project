package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.eventHub.MemberList;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.MemberDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    @FXML private Circle eventPicCircle;
    private DataSource<EventList> datasource;
    private EventList eventList;
    private String userName;
    private Event selectedEvent;
    private void showEventInfo(Event event) {
        eventNameLabel.setText(event.getEventName());
        startTimeLabel.setText(formatTimestamp(event.getStartTime()));
        dueTimeLabel.setText(formatTimestamp(event.getDueTime()));
        locationLabel.setText(event.getLocation());
        detailLabel.setText(event.getInfo());
        seatLeftLabel.setText(Double.toString(event.getLeftSeat()));
        maxSeatLabel.setText(Double.toString(event.getMaxSeat()));
        eventPicCircle.setFill(new ImagePattern(new Image("file:" + event.getEventPicture())));
    }

    @FXML public void initialize() {
        datasource = new EventDataSource("data", "event.csv");
        eventList = datasource.readData();

        String eventname = ((Pair<String, String>) FXRouter.getData()).getKey();
        userName = ((Pair<String, String>) FXRouter.getData()).getValue();
        selectedEvent = eventList.findEventByEventName(eventname);
        showEventInfo(selectedEvent);
    }

    @FXML private void joinEvent() {
        DataSource<MemberList> memberListDataSource = new MemberDataSource("data", "member.csv");
        MemberList memberList = memberListDataSource.readData();
        memberList.addMember(userName, selectedEvent.getEventID());
        memberListDataSource.writeData(memberList);
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
    @FXML private void joinStaff() {
        try {
            FXRouter.goTo("owner-event", selectedEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
