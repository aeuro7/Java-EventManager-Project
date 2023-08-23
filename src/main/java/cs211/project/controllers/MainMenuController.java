package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataHardCode;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataHardCode;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class MainMenuController {

    @FXML private TableView<Event> eventTableView;
    private EventList eventList;
    private DataSource<EventList> datasource;

    @FXML public void initialize() {
        datasource = new EventDataHardCode();
        eventList = datasource.readData();
        showTable(eventList);
    }

    private void showTable(EventList eventList) {
        TableColumn<Event, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        TableColumn<Event, String> startDateNewColumn = new TableColumn<>("Date");
        startDateNewColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Event, String> dueDateNewColumn = new TableColumn<>("Due date");
        dueDateNewColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        TableColumn<Event, String> startTimeNewColumn = new TableColumn<>("Start");
        startTimeNewColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        TableColumn<Event, String> dueTimeNewColumn = new TableColumn<>("End");
        dueTimeNewColumn.setCellValueFactory(new PropertyValueFactory<>("dueTime"));

        TableColumn<Event, String> leftSeatNewColumn = new TableColumn<>("Left Seat");
        leftSeatNewColumn.setCellValueFactory(new PropertyValueFactory<>("leftSeat"));

        TableColumn<Event, String> locationNewColumn = new TableColumn<>("Location");
        locationNewColumn.setCellValueFactory(new PropertyValueFactory<>("location"));


        eventTableView.getColumns().clear();

        eventTableView.getColumns().add(eventNameColumn);
        eventTableView.getColumns().add(startDateNewColumn);
        eventTableView.getColumns().add(dueDateNewColumn);
        eventTableView.getColumns().add(startTimeNewColumn);
        eventTableView.getColumns().add(dueTimeNewColumn);

        eventTableView.getColumns().add(leftSeatNewColumn);
        eventTableView.getColumns().add(locationNewColumn);

        eventTableView.getItems().clear();

        for (Event event: eventList.getAllEvent()) {
            eventTableView.getItems().add(event);
        }
    }


    @FXML
    public void goLogout() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goAdminview() {
        try {
            FXRouter.goTo("admin-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goProflie() {
        try {
            FXRouter.goTo("profile-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
