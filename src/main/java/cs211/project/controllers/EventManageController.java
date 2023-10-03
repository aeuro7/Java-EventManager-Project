package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventManageController {


    private DataSource<MemberList> memberListDataSource = new MemberDataSource("data", "member.csv");
    private DataSource<EventList> eventListDataSource = new EventDataSource("data", "event.csv");
    String username = (String) FXRouter.getData();

    @FXML TextField searchBox;

    private EventList eventList;
    private MemberList memberList;

    private DataSource<UserList> datasourceUser;

    @FXML private GridPane eventContrainer;
    @FXML private ScrollPane scrollpain;
    private DataSource<EventList> datasource;
    private int column = 0;
    private int row = 1;

    private UserList userList;

    private User account ;

    @FXML
    private void initialize() {
        datasource = new EventDataSource("data", "event.csv");
        eventList = datasource.readData();
        datasourceUser = new UserDataSource("data", "login.csv");
        userList = datasourceUser.readData();
        String username = (String) FXRouter.getData();
        account = userList.findUserByUserName(username);
        memberList = memberListDataSource.readData();

        for (Member member : memberList.getMemberList()) {
            if (member.getUsername().equals(username) && member.getRole().equals("OWNER")) {
                String eventID = member.getEventID();
                Event event = eventList.findEventByID(eventID);
                if (event != null) {
                    showEvent(event);
                }
            }
        }
    }



    private void showEvent(Event event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cs211/project/views/ongoing-event-tab.fxml"));
            AnchorPane eventinfoTab = loader.load();
            OnGoingTabController infoTabController = loader.getController();
            infoTabController.setData(event,userList.findUserByUserName(event.getEventOwner()).getAccountName());

            eventinfoTab.setOnMouseClicked(activity -> {
                try {
                    FXRouter.goTo("owner-event", event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            if(column == 1) {
                column = 0;
                row++;
            }
            scrollpain.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            eventContrainer.add(eventinfoTab, column++, row);
            GridPane.setMargin(eventinfoTab, new Insets(3));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            FXRouter.goTo("manage-team",username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event",username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }

    private void SearchFn(String searchTerm) {
        eventContrainer.getChildren().clear();
        for (Member member : memberList.getMemberList()) {
            if (member.getUsername().equals(username) && member.getRole().equals("OWNER")) {
                String eventID = member.getEventID();
                Event event = eventList.findEventByID(eventID);
                if (event != null && (searchTerm == null || searchTerm.isEmpty() || event.getEventName().toLowerCase().contains(searchTerm.toLowerCase()))) {
                    showEvent(event);
                }
            }
        }
    }


    public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", account.getUserName());
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


}
