package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OnGoingTabController {

    @FXML private Rectangle profileRectangle;
    @FXML private Label eventnameLabel;
    @FXML private Label starttimeLabel;
    @FXML private Label duetimeLabel;
    @FXML private Label ownernameLabel;
    @FXML private Label daysleftLabel;
    private Event currentEvent;

    public void setData(Event event, String ownerEventname) {
        currentEvent = event;
        profileRectangle.setFill(new ImagePattern(new Image("file:" + event.getEventPicture())));
        eventnameLabel.setText(event.getEventName());
        starttimeLabel.setText(formatTimestamp(event.getStartTime()));
        duetimeLabel.setText(formatTimestamp(event.getDueTime()));
        ownernameLabel.setText(ownerEventname);
        long millisecondsPerDay = 24 * 60 * 60 * 1000;
        long currentTime = System.currentTimeMillis();
        long startTime = event.getStartTime();
        long dayleft = (startTime - currentTime + millisecondsPerDay - 1) / millisecondsPerDay;
        daysleftLabel.setText(String.valueOf(dayleft));
    }
    public Event getData() {
        return currentEvent;
    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return dateFormat.format(new Date(timestamp));
    }
}