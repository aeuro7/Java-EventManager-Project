package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.CalendarList;
import cs211.project.models.Event;
import cs211.project.services.CalendarDataSource;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventCalendarController {
    @FXML private TableView<Calendar> calendarTableView;
    @FXML private Label eventnameLabel;
    @FXML private TextField searchBox;
    private Event selectedEvent = (Event) FXRouter.getData();
    private String account = selectedEvent.getEventOwner();
    DataSource<CalendarList> calendarListDataSource;
    CalendarList calendarList;

    @FXML public void initialize() {
        calendarListDataSource = new CalendarDataSource("data", "calendar.csv");
        calendarList = calendarListDataSource.readData();
        eventnameLabel.setText(selectedEvent.getEventName());
        showTable(calendarList);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchFn(newValue);
        });
    }

    public void addNewCalendar() {
        try {
            FXRouter.goTo("create-calendar", selectedEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showTable(CalendarList calendars) {
        TableColumn<Calendar, String> activityNameColumn = new TableColumn<>("Activity");
        activityNameColumn.setCellValueFactory(new PropertyValueFactory<>("calendarName"));

        TableColumn<Calendar, String> factionColumn = new TableColumn<>("Faction");
        factionColumn.setCellValueFactory(new PropertyValueFactory<>("faction"));

        TableColumn<Calendar, String> startTime = new TableColumn<>("Start-time");
        startTime.setCellValueFactory(cellData -> {
            long timestamp = cellData.getValue().getStartTime();
            String formattedTimestamp = formatTimestamp(timestamp); // Format the timestamp
            return new SimpleStringProperty(formattedTimestamp);
        });

        TableColumn<Calendar, String> dueTime = new TableColumn<>("Due-time");
        dueTime.setCellValueFactory(cellData -> {
            long timestamp = cellData.getValue().getDueTime();
            String formattedTimestamp = formatTimestamp(timestamp); // Format the timestamp
            return new SimpleStringProperty(formattedTimestamp);
        });

        calendarTableView.getColumns().clear();
        calendarTableView.getColumns().add(activityNameColumn);
        calendarTableView.getColumns().add(factionColumn);
        calendarTableView.getColumns().add(startTime);
        calendarTableView.getColumns().add(dueTime);

        calendarTableView.getItems().clear();

        for (Calendar calendar: calendars.getCalendars()) {
            if(calendar.getEventID().equals(selectedEvent.getEventID())) {
                calendarTableView.getItems().add(calendar);
            }
        }
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }
    private void SearchFn(String searchTerm) {
        searchTerm = searchTerm.toLowerCase().trim();
        calendarTableView.getItems().clear();

        for (Calendar calendar : calendarList.getCalendars()) {
            if (calendar.getCalendarName().toLowerCase().contains(searchTerm)) {
                calendarTableView.getItems().add(calendar);
            }
        }
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
    public void goOwner() {
        try {
            FXRouter.goTo("owner-event", selectedEvent);
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
