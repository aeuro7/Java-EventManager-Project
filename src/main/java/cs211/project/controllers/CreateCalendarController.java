package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.CalendarList;
import cs211.project.models.Event;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.*;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CreateCalendarController {
    @FXML private Label eventnameLabel;
    @FXML private TextField descriptionTextField;
    @FXML private TextField nameTextfield;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker dueDatePicker;
    @FXML private ChoiceBox<String> hourStartChoice;
    @FXML private ChoiceBox<String> minStartChoice;
    @FXML private ChoiceBox<String> hourDueChoice;
    @FXML private ChoiceBox<String> minDueChoice;
    @FXML private ChoiceBox<String> majorChoice;
    @FXML private ChoiceBox<String> teamChoice;
    @FXML private Label nameErrorLabel;
    @FXML private Label timeErrorLabel;

    private Event selectEvent = (Event) FXRouter.getData();
    private String account = selectEvent.getEventOwner();
    DataSource<CalendarList> calendarListDataSource;
    DataSource<TeamList> teamListDataSource;
    CalendarList calendarList;

    @FXML public void initialize() {
        calendarListDataSource = new CalendarDataSource("data", "calendar.csv");
        teamListDataSource = new TeamDataSource("data", "team.csv");
        calendarList = calendarListDataSource.readData();
        TeamList teamList = teamListDataSource.readData();
        eventnameLabel.setText(selectEvent.getEventName());

        TextFilter.allowAlphanumericOnly(nameTextfield);
        TextFilter.preventSeperateOnly(descriptionTextField);
        hideNameErrorLabel();
        hideTimeErrorLabel();
        teamChoice.setVisible(false);

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
        majorChoice.getItems().addAll("AUDIENCE", "TEAM");

        majorChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(majorChoice.getValue().equals("TEAM")) {
                teamChoice.setVisible(true);
                teamChoice.getItems().clear();
                for(Team team: teamList.getAllTeams()) {
                    if(team.getEventID().equals(selectEvent.getEventID())) {
                        teamChoice.getItems().add(team.getNameTeam());
                    }
                }
            }
            if(majorChoice.getValue().equals("AUDIENCE")) {
                teamChoice.setVisible(false);
            }
        });

        minDueChoice.getItems().addAll("00", "15", "30", "45");
        hourStartChoice.setValue("00");
        minStartChoice.setValue("00");
        hourDueChoice.setValue("00");
        minDueChoice.setValue("00");
        majorChoice.setValue("AUDIENCE");
    }

    @FXML public void createButton() {

        String name = nameTextfield.getText();
        String faction = (majorChoice.getValue().equals("AUDIENCE")) ? majorChoice.getValue():teamChoice.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate dueDate = dueDatePicker.getValue();
        String startTimeStr = hourStartChoice.getValue() + ":" + minStartChoice.getValue();
        String dueTimeStr = hourDueChoice.getValue() + ":" + minDueChoice.getValue();
        String description = descriptionTextField.getText();

        boolean nameCheck = false;
        boolean timeCheck = false;

        if(!name.equals("")) {
            nameCheck = true;
            hideNameErrorLabel();
        } else{
            showNameErrorLabel("Name is Required!");
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


            if(nameCheck && timeCheck) {
                Calendar newCalendar = new Calendar(name, selectEvent.getEventID(), faction, startTimeMillis, dueTimeMillis, description);
                calendarList.addNewCalendar(newCalendar);
                calendarListDataSource.writeData(calendarList);
                goEventCalendar();
            }
        }
    }

    private void showNameErrorLabel(String text) {
        nameErrorLabel.setText(text);
    }

    private void hideNameErrorLabel() {
        nameErrorLabel.setText("");
    }

    private void showTimeErrorLabel(String text) {
        timeErrorLabel.setText(text);
    }

    private void hideTimeErrorLabel() {
        timeErrorLabel.setText("");
    }

    @FXML
    public void goProflie() {
        try {
            FXRouter.goTo("profile-view", account);
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
    public void goEventCalendar() {
        try {
            FXRouter.goTo("calendar-event", selectEvent);
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
    public void goLogout() {
        try {
            FXRouter.goTo("login-view");
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
    @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
