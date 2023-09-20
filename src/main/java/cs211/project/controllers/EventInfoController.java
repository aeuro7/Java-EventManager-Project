package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.team.Team;
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
    @FXML public void initialize() {
        eventList = datasource.readData();

        Event eventname = ((Pair<Event, String>) FXRouter.getData()).getKey();
        userName = ((Pair<String, String>) FXRouter.getData()).getValue();
        selectedEvent = eventList.findEventByEventName(eventname);
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
            FXRouter.goTo("calendar-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void goChat() {
        try {
            FXRouter.goTo("chat-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoEditprofile() {
        try {
            FXRouter.goTo("profile-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }
}
