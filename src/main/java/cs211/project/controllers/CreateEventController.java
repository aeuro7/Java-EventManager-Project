package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.MemberDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CreateEventController {
    @FXML private TextField eventnameTextField;
    @FXML private DatePicker startDatePicker;
    @FXML private TextField startTimeTextField;
    @FXML private DatePicker dueDatePicker;
    @FXML private TextField dueTimeTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField audienceTextField;
    @FXML private TextField limitStaffTextField;
    @FXML private TextField descriptionTextField;

    private EventList eventList;
    DataSource<EventList> eventListDataSource;
    private MemberList memberList;
    DataSource<MemberList> memberListDataSource;

    private String account;
    @FXML public void initialize() {
        account = (String) FXRouter.getData();
        eventListDataSource = new EventDataSource("data", "event.csv");
        memberListDataSource = new MemberDataSource("data", "member.csv");
        memberList = memberListDataSource.readData();
        eventList = eventListDataSource.readData();
    }
    @FXML
    public void browsepicBotton() {

    }

    @FXML
    public void createEventButton() {
        String eventName = eventnameTextField.getText();
        LocalDate startDate = startDatePicker.getValue();
        String startTimeStr = startTimeTextField.getText();
        LocalDate dueDate = dueDatePicker.getValue();
        String dueTimeStr = dueTimeTextField.getText();
        String location = locationTextField.getText();
        String audience = audienceTextField.getText();
        String limitStaff = limitStaffTextField.getText();
        String info = descriptionTextField.getText();

        LocalTime startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime dueTime = LocalTime.parse(dueTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

        LocalDateTime startDateTime = startDate.atTime(startTime);
        LocalDateTime dueDateTime = dueDate.atTime(dueTime);

        ZoneId systemZone = ZoneId.systemDefault();
        long startTimeMillis = startDateTime.atZone(systemZone).toInstant().toEpochMilli();
        long dueTimeMillis = dueDateTime.atZone(systemZone).toInstant().toEpochMilli();

        Event newEvent = new Event(eventName, startTimeMillis, dueTimeMillis, info,
                Double.parseDouble(audience), location, Double.parseDouble(limitStaff));

        Member member = new Member(account, newEvent.getEventID(), "OWNER");

        eventList.addEvent(newEvent);
        memberList.addMember(member);
        eventListDataSource.writeData(eventList);
        memberListDataSource.writeData(memberList);

        try {
            FXRouter.goTo("main-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML public void gotoMainMenu() {
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
    @FXML private void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}