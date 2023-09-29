package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.CalendarList;
import cs211.project.models.Event;
import cs211.project.services.CalendarDataSource;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
public class EventCalendarController {

    @FXML private GridPane calendarContrainer;
    @FXML private Label eventnameLabel;
    @FXML private TextField searchBox;
    @FXML private ScrollPane scrollpain;
    private Event selectedEvent = (Event) FXRouter.getData();
    private String account = selectedEvent.getEventOwner();
    DataSource<CalendarList> calendarListDataSource;
    CalendarList calendarList;
    private int row = 1;

    @FXML public void initialize() {
        calendarListDataSource = new CalendarDataSource("data", "calendar.csv");
        calendarList = calendarListDataSource.readData();
        eventnameLabel.setText(selectedEvent.getEventName());

        fillTheGrid();

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchFn(newValue);
        });
    }

    private void fillTheGrid() {
        calendarContrainer.getChildren().clear();

        for (Calendar calendar: calendarList.getCalendars()) {
            if(calendar.getEventID().equals(selectedEvent.getEventID())) {
                showCalendar(calendar);
            }
        }
    }

    private void SearchFn(String searchTerm) {
        searchTerm = searchTerm.toLowerCase().trim();
        calendarContrainer.getChildren().clear();

        for (Calendar calendar : calendarList.getCalendars()) {
            if (calendar.getCalendarName().toLowerCase().contains(searchTerm)) {
                showCalendar(calendar);
            }
        }
    }

    public void addNewCalendar() {
        try {
            FXRouter.goTo("create-calendar", selectedEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showCalendar(Calendar calendar) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cs211/project/views/calendar-tab-mini.fxml"));
            AnchorPane calendartab = loader.load();
            CalendarMiniTabController calendarTabController = loader.getController();
            calendarTabController.setData(calendar, selectedEvent);

            ContextMenu contextMenu = new ContextMenu();
            MenuItem deleteMenuItem = new MenuItem("Delete");
            MenuItem markItDone = new MenuItem("Mark It Done");
            if(!calendar.getFaction().equals("AUDIENCE") && !calendar.isDone()) {
                contextMenu.getItems().add(markItDone);
            }
            contextMenu.getItems().add(deleteMenuItem);

            calendartab.setOnContextMenuRequested(event -> {
                contextMenu.show(calendartab, event.getScreenX(), event.getScreenY());
            });
            markItDone.setOnAction(event -> {
                calendar.itDone();
                calendarListDataSource.writeData(calendarList);
                fillTheGrid();
            });
            deleteMenuItem.setOnAction(event -> {
                calendarList.removeCalendar(calendar);
                calendarListDataSource.writeData(calendarList);
                fillTheGrid();
            });
            scrollpain.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            calendarContrainer.add(calendartab, 0, row++);
            GridPane.setMargin(calendartab, new Insets(5));
        } catch (IOException e) {
            e.printStackTrace();
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
