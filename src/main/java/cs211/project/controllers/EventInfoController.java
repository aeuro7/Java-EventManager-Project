package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.team.Team;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.TeamDataSource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventInfoController {
    @FXML private Label dueTimeLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label locationLabel;
    @FXML private Label detailLabel;
    @FXML private Circle eventPicCircle;
    private DataSource<EventList> datasource;
    private EventList eventList;
    private String userName;
    private Event selectedEvent;

    @FXML public void initialize() {
        datasource = new EventDataSource("data", "event.csv");
        eventList = datasource.readData();

        selectedEvent = ((Pair<Event, String>) FXRouter.getData()).getKey();
        userName = ((Pair<String, String>) FXRouter.getData()).getValue();
        showEventInfo(selectedEvent);

    }
    private void showEventInfo(Event event) {
        startTimeLabel.setText(formatTimestamp(event.getStartTime()));
        dueTimeLabel.setText(formatTimestamp(event.getDueTime()));
        locationLabel.setText(event.getLocation());
        detailLabel.setText(event.getInfo());
        eventPicCircle.setFill(new ImagePattern(new Image("file:" + event.getEventPicture())));
    }
    @FXML private void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void goChat() {
        try {
            FXRouter.goTo("chat-view", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoEditprofile() {
        try {
            FXRouter.goTo("profile-view", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event", userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }
}
