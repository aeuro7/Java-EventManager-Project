package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.CalendarList;
import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.CalendarHardCode;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarController {
    private User account;
    @FXML private TableView<Calendar> calendarTableView;
    @FXML private TextField searchBox;
    @FXML private Label eventnameLabel;
    @FXML private Label starttimeLabel;
    @FXML private Label duetimeLabel;
    @FXML private Label infoLabel;
    private CalendarList calendarList;
    @FXML public void initialize() {
        account = (User) FXRouter.getData();
        DataSource<CalendarList> dataSource = new CalendarHardCode();
        calendarList = dataSource.readData();
        showTable(calendarList);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchFn(newValue);
        });

        calendarTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Calendar>() {
            @Override
            public void changed(ObservableValue<? extends Calendar> observable, Calendar oldValue, Calendar newValue) {
                if (newValue == null) {
                    clearInfo();
                } else {
                    showInfo(newValue);
                }
            }
        });
    }

    private void clearInfo() {
        eventnameLabel.setText("");
        starttimeLabel.setText("");
        duetimeLabel.setText("");
        infoLabel.setText("");
    }

    private void showInfo(Calendar calendar) {
        eventnameLabel.setText(calendar.getEventLinkName());
        starttimeLabel.setText(formatTimestamp(calendar.getStartTime()));
        duetimeLabel.setText(formatTimestamp(calendar.getDueTime()));
        infoLabel.setText(calendar.getDetail());
    }

    @FXML public void goLogout() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goProflie() {
        try {
            FXRouter.goTo("profile-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goMain() {
        try {
            FXRouter.goTo("main-menu", account);
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
    private void showTable(CalendarList calendars) {
        TableColumn<Calendar, String> eventNameColumn = new TableColumn<>("Event");
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventLinkName"));

        TableColumn<Calendar, String> activityNameColumn = new TableColumn<>("Activity");
        activityNameColumn.setCellValueFactory(new PropertyValueFactory<>("calendarName"));

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
        calendarTableView.getColumns().add(eventNameColumn);
        calendarTableView.getColumns().add(activityNameColumn);
        calendarTableView.getColumns().add(startTime);
        calendarTableView.getColumns().add(dueTime);

        calendarTableView.getItems().clear();

        for (Calendar calendar: calendars.getCalendars()) {
            calendarTableView.getItems().add(calendar);
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
            if (calendar.getEventLinkName().toLowerCase().contains(searchTerm)) {
                calendarTableView.getItems().add(calendar);
            }
        }
    }
}