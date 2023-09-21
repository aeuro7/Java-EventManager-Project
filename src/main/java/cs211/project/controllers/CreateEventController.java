package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
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
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker dueDatePicker;
    @FXML private TextField locationTextField;
    @FXML private TextField audienceTextField;
    @FXML private TextField descriptionTextField;
    @FXML private Circle eventPicCircle;
    @FXML private ChoiceBox<String> hourStartChoice;
    @FXML private ChoiceBox<String> minStartChoice;
    @FXML private ChoiceBox<String> hourDueChoice;
    @FXML private ChoiceBox<String> minDueChoice;

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
        String location = locationTextField.getText();
        String audience = audienceTextField.getText();
        String info = descriptionTextField.getText();
        String startTimeStr = hourStartChoice.getValue() + ":" + minStartChoice.getValue();
        String dueTimeStr = hourDueChoice.getValue() + ":" + minDueChoice.getValue();

        LocalTime startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime dueTime = LocalTime.parse(dueTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

        LocalDateTime startDateTime = startDate.atTime(startTime);
        LocalDateTime dueDateTime = dueDate.atTime(dueTime);

        ZoneId systemZone = ZoneId.systemDefault();
        long startTimeMillis = startDateTime.atZone(systemZone).toInstant().toEpochMilli();
        long dueTimeMillis = dueDateTime.atZone(systemZone).toInstant().toEpochMilli();


        Event newEvent = new Event(eventName, startTimeMillis, dueTimeMillis, info,
                Long.parseLong(audience), location, account);

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
}