package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataHardCode;
import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class EventViewController {
    @FXML private Label eventNameLabel;
    @FXML private Label startDateLabel;
    @FXML private Label detailLabel;
    @FXML private Label seatLeftLabel;
    @FXML private Label dueDateLabel;
    @FXML private Label dueTimeLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label locationLabel;
    @FXML private Label maxSeatLabel;
    private DataSource<EventList> datasource;
    private EventList eventList;
    private void showEventInfo(Event event) {
        eventNameLabel.setText(event.getEventName());
        startDateLabel.setText(event.getStartDate());
        startTimeLabel.setText(event.getStartTime());
        dueDateLabel.setText(event.getDueDate());
        dueTimeLabel.setText(event.getDueTime());
        locationLabel.setText(event.getLocation());
        detailLabel.setText(event.getInfo());
        seatLeftLabel.setText(Double.toString(event.getLeftSeat()));
        maxSeatLabel.setText(Double.toString(event.getMaxSeat()));
    }

    @FXML public void initialize() {
        datasource = new EventDataHardCode();
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
}
