package cs211.project.controllers;

import cs211.project.models.eventHub.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventTabController {
    @FXML private Rectangle profileRectangle;
    @FXML private Label eventnameLabel;
    @FXML private Label starttimeLabel;
    @FXML private Label duetimeLabel;
    @FXML private Label ownernameLabel;
    @FXML private Label seatleftLabel;
    @FXML private Label maxseatLabel;

    private Event currentEvent;

    public void setData(Event event, String ownerEventname) {
        currentEvent = event;
        profileRectangle.setFill(new ImagePattern(new Image("file:" + event.getEventPicture())));
        eventnameLabel.setText(event.getEventName());
        starttimeLabel.setText(formatTimestamp(event.getStartTime()));
        duetimeLabel.setText(formatTimestamp(event.getDueTime()));
        ownernameLabel.setText(ownerEventname);
        seatleftLabel.setText(String.valueOf(event.getLeftSeat()));
        maxseatLabel.setText(String.valueOf(event.getMaxSeat()));
    }
    public Event getData() {
        return currentEvent;
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return dateFormat.format(new Date(timestamp));
    }
}
