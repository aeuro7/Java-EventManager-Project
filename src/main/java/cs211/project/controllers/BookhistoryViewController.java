package cs211.project.controllers;

import cs211.project.models.eventHub.Event;
import cs211.project.models.eventHub.EventList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookhistoryViewController {

    @FXML private Group nowOn;
    @FXML private Group nowCom;
    private DataSource<MemberList> memberListDataSource = new MemberDataSource("data", "member.csv");
    String username;

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
        username = (String) FXRouter.getData();
        nowCom.setVisible(false);
        nowOn.setVisible(false);
        datasource = new EventDataSource("data", "event.csv");
        eventList = datasource.readData();
        datasourceUser = new UserDataSource("data", "login.csv");
        userList = datasourceUser.readData();
        String username = (String) FXRouter.getData();
        account = userList.findUserByUserName(username);
        memberList = memberListDataSource.readData();

        showOngoing();

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
                    Pair<Event, String> sender = new Pair<Event, String>(event, account.getUserName());
                    FXRouter.goTo("event-info", sender);
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


    private void showEventComplete(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cs211/project/views/completed-event-tab.fxml"));
            AnchorPane eventinfoTab = loader.load();
            CompletedTabController infoTabController = loader.getController();
            infoTabController.setData(event, userList.findUserByUserName(event.getEventOwner()).getAccountName());

            eventinfoTab.setOnMouseClicked(activity -> {
                try {
                    Pair<Event, String> sender = new Pair<Event, String>(event, account.getUserName());
                    FXRouter.goTo("event-info", sender);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            if (column == 1) {
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
    @FXML
    public void goCredit() {
        try {
            FXRouter.goTo("credit-view", username);
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

        eventContrainer.getChildren().clear();


        for (Member member : memberList.getMemberList()) {
            if (member.getUsername().equals(username) && member.getRole().equals("AUDIENCE")) {
                String eventID = member.getEventID();
                Event addEvent = eventList.findEventByID(eventID);
                if (addEvent != null && System.currentTimeMillis() < addEvent.getStartTime()) {
                    showEvent(addEvent);
                }
            }
        }
    }
    @FXML private void showCompleted() {
        nowCom.setVisible(true);
        nowOn.setVisible(false);

        eventContrainer.getChildren().clear(); // เคลียร์ข้อมูลก่อนแสดง Event ใหม่

        for (Member member : memberList.getMemberList()) {
            if (member.getUsername().equals(username) && member.getRole().equals("AUDIENCE")) {
                String eventID = member.getEventID();
                Event addEvent = eventList.findEventByID(eventID);
                if (addEvent != null && System.currentTimeMillis() > addEvent.getStartTime()) {
                    showEventComplete(addEvent);
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

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }


}
