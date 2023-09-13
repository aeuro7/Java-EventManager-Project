package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.users.User;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataSource;
import cs211.project.services.FXRouter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainMenuController {

    @FXML private TableView<Event> eventTableView;
    @FXML private Button adminButton;
    private EventList eventList;
    private DataSource<EventList> datasource;

    @FXML TextField searchBox;
    @FXML Label accountnameLabel;
    private User account = (User) FXRouter.getData();

    @FXML public void initialize() {
        datasource = new EventDataSource("data", "event.csv");
        eventList = datasource.readData();
        adminButton.setVisible(false);
        showTable(eventList);

        accountnameLabel.setText(account.getAccountName());
        if(account.isAdmin()) {
            adminButton.setVisible(true);
        }

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchFn(newValue);
        });
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

        setCenterAlignment(startTimeColumn);
        setCenterAlignment(dueTimeColumn);


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

        for (Event event: eventList.getAllEvent()) {
            eventTableView.getItems().add(event);
        }

        eventTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue observable, Event oldValue, Event newValue) {
                if (newValue != null) {
                    try {
                        FXRouter.goTo("event-view", newValue.getEventName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });



    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }


    private void setCenterAlignment(TableColumn<Event, String> column) {
        column.setCellFactory(tc -> new TableCell<Event, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(null);
                } else {
                    setText(item);
                    setAlignment(javafx.geometry.Pos.CENTER);
                }
            }
        });
    }

    @FXML
    public void goLogout() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void createEvent() {
        try {
            FXRouter.goTo("create-event", account.getUserName());
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
            FXRouter.goTo("profile-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goChat() {
        try {
            FXRouter.goTo("chat-view", account.getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void SearchFn(String searchTerm) {
        searchTerm = searchTerm.toLowerCase().trim();
        eventTableView.getItems().clear();

        for (Event event : eventList.getAllEvent()) {
            if (event.getEventName().toLowerCase().contains(searchTerm)) {
                eventTableView.getItems().add(event);
            }
        }
    }




}