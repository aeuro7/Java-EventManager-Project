package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.MemberDataSource;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookhistoryViewController {

    @FXML private Group nowOn;
    @FXML private Group nowCom;
    @FXML private TableView eventTableView;
    private DataSource<MemberList> memberListDataSource = new MemberDataSource("data", "member.csv");
    private DataSource<EventList> eventListDataSource = new EventDataSource("data", "event.csv");
    String username;

    private EventList eventList;
    private MemberList memberList;

    @FXML
    private void initialize() {
        eventList = eventListDataSource.readData();
        memberList = memberListDataSource.readData();
        username = (String) FXRouter.getData();
        nowCom.setVisible(false);
        nowOn.setVisible(false);
        showTable(eventList);
        showOngoing();
        eventTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue observable, Event oldValue, Event newValue) {
                if (newValue != null) {
                    Pair sender = new Pair<Event, String>(newValue, username);
                    try {
                        FXRouter.goTo("event-info", sender);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
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

        TableColumn<Event, String> locationNewColumn = new TableColumn<>("Location");
        locationNewColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        eventTableView.getColumns().clear();

        eventTableView.getColumns().add(eventNameColumn);
        eventNameColumn.setMinWidth(120);

        eventTableView.getColumns().add(startTimeColumn);
        startTimeColumn.setMinWidth(135);

        eventTableView.getColumns().add(dueTimeColumn);
        dueTimeColumn.setMinWidth(135);

        eventTableView.getColumns().add(locationNewColumn);
        locationNewColumn.setMinWidth(153);

        eventTableView.getItems().clear();
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
            FXRouter.goTo("main-menu",username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML private void gotoEditprofile() {
        try {
            FXRouter.goTo("profile-view",username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view",username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team", username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event", username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML private void showOngoing() {
            nowOn.setVisible(true);
            nowCom.setVisible(false);

        eventTableView.getItems().clear();

        for (Member member : memberList.getMemberList()) {
            if (member.getUsername().equals(username) && member.getRole().equals("AUDIENCE") ) {
                String eventID = member.getEventID();
                Event addEvent = eventList.findEventByID(eventID);
                if (addEvent != null && System.currentTimeMillis() < addEvent.getStartTime()) {
                    eventTableView.getItems().add(addEvent);
                }
            }
        }

    }

    @FXML private void showCompleted() {
        nowCom.setVisible(true);
        nowOn.setVisible(false);

        eventTableView.getItems().clear();

        for (Member member : memberList.getMemberList()) {
            if (member.getUsername().equals(username) && member.getRole().equals("AUDIENCE")) {
                String eventID = member.getEventID();
                Event addEvent = eventList.findEventByID(eventID);
                if (addEvent != null && System.currentTimeMillis() > addEvent.getStartTime()) {
                    eventTableView.getItems().add(addEvent);
                }
            }
        }


    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }


}

