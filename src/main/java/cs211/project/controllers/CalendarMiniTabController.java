package cs211.project.controllers;

import cs211.project.models.eventHub.Calendar;
import cs211.project.models.eventHub.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarMiniTabController {
    @FXML private Label activitynameLabel;
    @FXML private Label factionLabel;
    @FXML private Label starttimeLabel;
    @FXML private Label duetimeLabel;
    @FXML private Label detailLabel;
    @FXML private Label daysleftLabel;
    @FXML private Label daysleftFixLabel;
    @FXML private Rectangle profileRectangle;

    public void setData(Calendar calendar, Event event) {
        profileRectangle.setFill(new ImagePattern(new Image("file:" + event.getEventPicture())));
        activitynameLabel.setText(calendar.getCalendarName());
        factionLabel.setText(calendar.getFaction());
        starttimeLabel.setText(formatTimestamp(calendar.getStartTime()));
        duetimeLabel.setText(formatTimestamp(calendar.getDueTime()));
        detailLabel.setText(calendar.getDetail());
        if (!calendar.isDone()) {
            long millisecondsPerDay = 24 * 60 * 60 * 1000;
            long currentTime = System.currentTimeMillis();
            long startTime = calendar.getStartTime();
            long dayleft = (startTime - currentTime + millisecondsPerDay - 1) / millisecondsPerDay;

            if (dayleft == 0) {
                daysleftLabel.setText("TODAY");
                daysleftFixLabel.setVisible(true);
            } else if(dayleft < 0) {
                daysleftLabel.setText("DONE");
                daysleftFixLabel.setVisible(false);
            }
            else {
                daysleftLabel.setText(String.valueOf(dayleft));
                daysleftFixLabel.setVisible(true);
            }
        } else {
            daysleftLabel.setText("DONE");
            daysleftFixLabel.setVisible(false);
        }
    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return dateFormat.format(new Date(timestamp));
    }
}
