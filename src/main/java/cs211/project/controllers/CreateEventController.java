package cs211.project.controllers;

import cs211.project.models.eventHub.Event;
import cs211.project.models.eventHub.EventList;
import cs211.project.models.chats.Chat;
import cs211.project.models.chats.ChatList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CreateEventController {
    @FXML private TextField eventnameTextField;
    @FXML private Label eventNameErrorLabel;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker dueDatePicker;
    @FXML private DatePicker startBookedPicker;
    @FXML private DatePicker dueBookedPicker;
    @FXML private TextField locationTextField;
    @FXML private Label locationErrorLabel;
    @FXML private Label timeErrorLabel;
    @FXML private TextField audienceTextField;
    @FXML private Label audienceErrorLabel;
    @FXML private TextField descriptionTextField;
    @FXML private Circle eventPicCircle;
    @FXML private ChoiceBox<String> hourStartChoice;
    @FXML private ChoiceBox<String> minStartChoice;
    @FXML private ChoiceBox<String> hourDueChoice;
    @FXML private ChoiceBox<String> minDueChoice;
    @FXML private ChoiceBox<String> hrStartBookedChoice;
    @FXML private ChoiceBox<String> mStartBookedChoice;
    @FXML private ChoiceBox<String> hrDueBookedChoice;
    @FXML private ChoiceBox<String> mDueBookedChoice;

    private EventList eventList;
    DataSource<EventList> eventListDataSource;
    private MemberList memberList;
    DataSource<MemberList> memberListDataSource;
    DataSource<ChatList> chatListDataSource;
    private ChatList chatList;
    private File selectedImageFile;
    private String account;
    private String picturePath;
    @FXML public void initialize() {
        account = (String) FXRouter.getData();
        picturePath = "data/EventPicture/default.png";
        eventPicCircle.setFill(new ImagePattern(new Image("file:" + picturePath)));
        eventListDataSource = new EventDataSource("data", "event.csv");
        memberListDataSource = new MemberDataSource("data", "member.csv");
        chatListDataSource = new ChatListDataSource("data", "chat.csv");
        memberList = memberListDataSource.readData();
        eventList = eventListDataSource.readData();
        chatList = chatListDataSource.readData();

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

        eventNameErrorLabel.setVisible(false);
        locationErrorLabel.setVisible(false);
        audienceErrorLabel.setVisible(false);
        hideTimeErrorLabel();

        TextFilter.safeForCSV(eventnameTextField);
        TextFilter.safeForCSV(locationTextField);
        TextFilter.allowOnlyNumber(audienceTextField);
        TextFilter.safeForCSV(descriptionTextField);
    }
    @FXML public void browseButtonClick(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (PNG, JPG)", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        selectedImageFile = chooser.showOpenDialog(source.getScene().getWindow());

        if (selectedImageFile != null) {
            Image selectedImage = new Image(selectedImageFile.toURI().toString());
            eventPicCircle.setFill(new ImagePattern(selectedImage));
        }
    }

    @FXML public void createEventButton() {
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

        boolean nameCheck = false;
        boolean timeCheck = false;
        boolean bookingtimeCheck = false;
        boolean locationCheck = false;
        boolean audieunCheck = false;

        if(!eventName.equals("")) {
            nameCheck = true;
            eventNameErrorLabel.setVisible(false);
        } else{
            eventNameErrorLabel.setVisible(true);
        }

        if(!location.equals("")) {
            locationCheck = true;
            locationErrorLabel.setVisible(false);
        } else{
            locationErrorLabel.setVisible(true);
        }

        if(!audience.equals("")) {
            audieunCheck = true;
            audienceErrorLabel.setVisible(false);
        } else{
            audienceErrorLabel.setVisible(true);
        }

        if(startDate == null || dueDate == null || startTimeStr == null || dueTimeStr == null) {
            showTimeErrorLabel("Time must be chosen!");
        } else {
            LocalTime startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime dueTime = LocalTime.parse(dueTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

            LocalDateTime startDateTime = startDate.atTime(startTime);
            LocalDateTime dueDateTime = dueDate.atTime(dueTime);

            ZoneId systemZone = ZoneId.systemDefault();
            long startTimeMillis = startDateTime.atZone(systemZone).toInstant().toEpochMilli();
            long dueTimeMillis = dueDateTime.atZone(systemZone).toInstant().toEpochMilli();

            if(dueTimeMillis > startTimeMillis) {
                timeCheck = true;
                hideTimeErrorLabel();
            } else {
                showTimeErrorLabel("Start time must be before due time.");
            }
            if(startBookingDate == null || dueBookingDate == null || startBookingStr == null || dueBookingStr == null) {
                showTimeErrorLabel("Booking Time must be chosen!");
            } else {
                LocalTime startBookingTime = LocalTime.parse(startBookingStr, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime dueBookingTime = LocalTime.parse(dueBookingStr, DateTimeFormatter.ofPattern("HH:mm"));

                LocalDateTime startBookingDateTime = startBookingDate.atTime(startBookingTime);
                LocalDateTime dueBookingDateTime = dueBookingDate.atTime(dueBookingTime);
                long startBookingTimeMillis = startBookingDateTime.atZone(systemZone).toInstant().toEpochMilli();
                long dueBookingTimeMillis = dueBookingDateTime.atZone(systemZone).toInstant().toEpochMilli();

                if(dueBookingTimeMillis > startBookingTimeMillis) {
                    bookingtimeCheck = true;
                    hideTimeErrorLabel();
                } else {
                    showTimeErrorLabel("Start time must be before due time.");
                }

                if(nameCheck && timeCheck && bookingtimeCheck && locationCheck && audieunCheck) {
                    Event newEvent = new Event(eventName, startTimeMillis, dueTimeMillis, startBookingTimeMillis, dueBookingTimeMillis, info, Long.parseLong(audience), location, account);


                    String profilePicturePath;
                    if (selectedImageFile != null) {
                        profilePicturePath = copyFile(newEvent.getEventID(), selectedImageFile);
                    } else {
                        Path path = Path.of(picturePath);
                        selectedImageFile = path.toFile();
                        profilePicturePath = copyFile(newEvent.getEventID(), selectedImageFile);;
                    }
                    newEvent.setEventPicture(profilePicturePath);

                    Member member = new Member(account, newEvent.getEventID(), "OWNER");
                    Chat chat = new Chat(newEvent.getEventID(), "AUDIENCE");

                    eventList.addEvent(newEvent);
                    memberList.addMember(member);
                    chatList.addChat(chat);

                    eventListDataSource.writeData(eventList);
                    memberListDataSource.writeData(memberList);
                    chatListDataSource.writeData(chatList);

                    try {
                        FXRouter.goTo("owner-event", newEvent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


    private void showTimeErrorLabel(String text) {
        timeErrorLabel.setText(text);
    }

    private void hideTimeErrorLabel() {
        timeErrorLabel.setText("");
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
    @FXML private void logoutButton() {
        try {
            FXRouter.goTo("login-view");
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

    @FXML
    public void gotoChat() {
        try {
            FXRouter.goTo("chat-view",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}