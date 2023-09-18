package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.services.FXRouter;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventManageController {


    @FXML private EventList eventList;
    @FXML private TableView eventTableView;

    @FXML
    private void initialize() {
        showTable(eventList);

    }


    private void showTable(EventList eventList) {
        TableColumn<Event, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        TableColumn<Event, String> startTimeColumn = new TableColumn<>("Start From");
        startTimeColumn.setCellValueFactory(cellData -> {
            long startTime = cellData.getValue().getStartTime();
            String formattedTimestamp = formatTimestamp(startTime); // Format the timestamp
            return new SimpleStringProperty(formattedTimestamp);
        });

        TableColumn<Event, String> dueTimeColumn = new TableColumn<>("End At");
        dueTimeColumn.setCellValueFactory(cellData -> {
            long endTime = cellData.getValue().getDueTime();
            String formattedTimestamp = formatTimestamp(endTime);
            return new SimpleStringProperty(formattedTimestamp);
        });

        TableColumn<Event, String> leftSeatNewColumn = new TableColumn<>("Seat Available");
        leftSeatNewColumn.setCellValueFactory(new PropertyValueFactory<>("leftSeat"));

        TableColumn<Event, String> locationNewColumn = new TableColumn<>("Location");
        locationNewColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        eventTableView.getColumns().clear();

        eventTableView.getColumns().add(eventNameColumn);
        eventNameColumn.setMinWidth(180);

        eventTableView.getColumns().add(startTimeColumn);
        eventTableView.getColumns().add(dueTimeColumn);

        eventTableView.getColumns().add(leftSeatNewColumn);
        leftSeatNewColumn.setMinWidth(30);
        eventTableView.getColumns().add(locationNewColumn);
        locationNewColumn.setMinWidth(184);

        eventTableView.getItems().clear();

//        for (Event event : eventList.getAllEvent()) {
//            eventTableView.getItems().add(event);
//        }

    }

    @FXML
    private void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoEditprofile() {
        try {
            FXRouter.goTo("profile-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

  @FXML private void gotoManageEvent2() {
        try {
            FXRouter.goTo("manage-event2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }



}

