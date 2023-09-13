package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataSource;
import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    private DataSource<EventList> datasource;
    private EventList eventList;
    private void showEventInfo(Event event) {
        eventNameLabel.setText(event.getEventName());
        startTimeLabel.setText(formatTimestamp(event.getStartTime()));
        dueTimeLabel.setText(formatTimestamp(event.getDueTime()));
        locationLabel.setText(event.getLocation());
        detailLabel.setText(event.getInfo());
        seatLeftLabel.setText(Double.toString(event.getLeftSeat()));
        maxSeatLabel.setText(Double.toString(event.getMaxSeat()));
    }

    @FXML public void initialize() {
        datasource = new EventDataSource("data", "event.csv");
        eventList = datasource.readData();

        String eventname = (String) FXRouter.getData();
        Event selectedEvent = eventList.findEventByEventName(eventname);
        showEventInfo(selectedEvent);
    }

    @FXML private void joinEvent() {

    }
    @FXML private void goMainMenu() {
        try {
            FXRouter.goTo("main-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void goProflie() {
        try {
            FXRouter.goTo("profile-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void joinStaff() {

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
