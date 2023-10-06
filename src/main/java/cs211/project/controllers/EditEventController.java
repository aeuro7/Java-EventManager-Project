package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataSource;
import cs211.project.services.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class EditEventController {
    @FXML private TextField eventnameTextField;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker dueDatePicker;
    @FXML private DatePicker startBookedPicker;
    @FXML private DatePicker dueBookedPicker;
    @FXML private TextField locationTextField;
    @FXML private TextField audienceTextField;
    @FXML private TextField descriptionTextField;
    @FXML private Circle eventPicCircle;
    @FXML private ChoiceBox hourStartChoice;
    @FXML private ChoiceBox minStartChoice;
    @FXML private ChoiceBox hourDueChoice;
    @FXML private ChoiceBox minDueChoice;
    @FXML private ChoiceBox<String> hrStartBookedChoice;
    @FXML private ChoiceBox<String> mStartBookedChoice;
    @FXML private ChoiceBox<String> hrDueBookedChoice;
    @FXML private ChoiceBox<String> mDueBookedChoice;

    private EventList eventList;
    DataSource<EventList> eventListDataSource;
    private File selectedImageFile;
    private Event selectEvent;
    private String account;

    @FXML public void initialize() {
        eventListDataSource = new EventDataSource("data", "event.csv");
        eventList = eventListDataSource.readData();
        selectEvent = eventList.findEventByID((String) FXRouter.getData());
        account = selectEvent.getEventOwner();
        eventPicCircle.setFill(new ImagePattern(new Image("file:" + selectEvent.getEventPicture())));

        hourStartChoice.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23"
        );
        minStartChoice.getItems().addAll("00", "15", "30", "45");
        hourDueChoice.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23"
        );
        minDueChoice.getItems().addAll("00", "15", "30", "45");
        hourStartChoice.setValue("00");
        minStartChoice.setValue("00");
        hourDueChoice.setValue("00");
        minDueChoice.setValue("00");

        hrStartBookedChoice.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23"
        );
        mStartBookedChoice.getItems().addAll("00", "15", "30", "45");
        hrDueBookedChoice.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23"
        );
        mDueBookedChoice.getItems().addAll("00", "15", "30", "45");
        hrStartBookedChoice.setValue("00");
        mStartBookedChoice.setValue("00");
        hrDueBookedChoice.setValue("00");
        mDueBookedChoice.setValue("00");

        eventnameTextField.setText(selectEvent.getEventName());

        LocalDateTime startDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(selectEvent.getStartTime()), ZoneId.systemDefault());
        LocalDateTime dueDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(selectEvent.getDueTime()), ZoneId.systemDefault());
        LocalDateTime startBookingDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(selectEvent.getStartBookingTime()), ZoneId.systemDefault());
        LocalDateTime dueBookingDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(selectEvent.getDueBookingTime()), ZoneId.systemDefault());


        startDatePicker.setValue(startDateTime.toLocalDate());
        dueDatePicker.setValue(dueDateTime.toLocalDate());
        hourStartChoice.setValue(String.format("%02d", startDateTime.getHour()));
        minStartChoice.setValue(String.format("%02d", startDateTime.getMinute()));
        hourDueChoice.setValue(String.format("%02d", dueDateTime.getHour()));
        minDueChoice.setValue(String.format("%02d", dueDateTime.getMinute()));

        startBookedPicker.setValue(startBookingDate.toLocalDate());
        dueBookedPicker.setValue(dueBookingDate.toLocalDate());
        hrStartBookedChoice.setValue(String.format("%02d", startBookingDate.getHour()));
        mStartBookedChoice.setValue(String.format("%02d", startBookingDate.getMinute()));
        hrDueBookedChoice.setValue(String.format("%02d", dueBookingDate.getHour()));
        mDueBookedChoice.setValue(String.format("%02d", dueBookingDate.getMinute()));

        locationTextField.setText(selectEvent.getLocation());
        audienceTextField.setText(String.valueOf(selectEvent.getMaxSeat()));
        descriptionTextField.setText(selectEvent.getInfo());
    }
    public void browseButtonClick(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (PNG, JPG)", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        selectedImageFile = chooser.showOpenDialog(source.getScene().getWindow());

        if (selectedImageFile != null) {
            Image profileImage = new Image(selectedImageFile.toURI().toString());
            ImagePattern imagePattern = new ImagePattern(profileImage);
            eventPicCircle.setFill(imagePattern);
        }
        copyFile(selectEvent.getEventID(), selectedImageFile);
    }
    public void editEvent() {
        String eventName = eventnameTextField.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate dueDate = dueDatePicker.getValue();
        LocalDate startBookingDate = startBookedPicker.getValue();
        LocalDate dueBookingDate = dueBookedPicker.getValue();
        String location = locationTextField.getText();
        String audience = audienceTextField.getText();
        String info = descriptionTextField.getText();
        String startTimeStr = hourStartChoice.getValue() + ":" + minStartChoice.getValue();
        String dueTimeStr = hourDueChoice.getValue() + ":" + minDueChoice.getValue();
        String startBookingStr = hrStartBookedChoice.getValue() + ":" + mStartBookedChoice.getValue();
        String dueBookingStr = hrDueBookedChoice.getValue() + ":" + mDueBookedChoice.getValue();

        LocalTime startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime dueTime = LocalTime.parse(dueTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime startBookingTime = LocalTime.parse(startBookingStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime dueBookingTime = LocalTime.parse(dueBookingStr, DateTimeFormatter.ofPattern("HH:mm"));

        LocalDateTime startDateTime = startDate.atTime(startTime);
        LocalDateTime dueDateTime = dueDate.atTime(dueTime);
        LocalDateTime startBookingDateTime = startBookingDate.atTime(startBookingTime);
        LocalDateTime dueBookingDateTime = dueBookingDate.atTime(dueBookingTime);

        ZoneId systemZone = ZoneId.systemDefault();
        long startTimeMillis = startDateTime.atZone(systemZone).toInstant().toEpochMilli();
        long dueTimeMillis = dueDateTime.atZone(systemZone).toInstant().toEpochMilli();
        long startBookingTimeMillis = startBookingDateTime.atZone(systemZone).toInstant().toEpochMilli();
        long dueBookingTimeMillis = dueBookingDateTime.atZone(systemZone).toInstant().toEpochMilli();

        selectEvent.changeEventName(eventName);
        selectEvent.setStartTime(startTimeMillis);
        selectEvent.setDueTime(dueTimeMillis);
        selectEvent.setLocation(location);
        selectEvent.setMaxSeat(Long.parseLong(audience));
        selectEvent.setInfo(info);
        selectEvent.setStartBookingTime(startBookingTimeMillis);
        selectEvent.setDueBookingTime(dueBookingTimeMillis);

        eventListDataSource.writeData(eventList);

        try {
            FXRouter.goTo("owner-event", selectEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void goProflie() {
        try {
            FXRouter.goTo("profile-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("owner-event", selectEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", account);
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
    private String copyFile(String eventID, File source) {
        String userProfilePictureFolder = "data/EventPicture";
        File userProfilePictureDir = new File(userProfilePictureFolder);
        if (!userProfilePictureDir.exists()) {
            userProfilePictureDir.mkdirs();
        }
        String imageFilename = eventID + ".png";
        String profilePicturePath = userProfilePictureFolder + "/" + imageFilename;
        try {
            File destinationFile = new File(profilePicturePath);
            Files.copy(source.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return profilePicturePath;
        }
    }

    @FXML
    public void goCredit() {
        try {
            FXRouter.goTo("credit-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
