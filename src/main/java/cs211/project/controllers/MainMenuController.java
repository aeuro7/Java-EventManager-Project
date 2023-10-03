package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.EventDataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataSource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.io.IOException;

public class MainMenuController {

    @FXML private GridPane eventContrainer;
    @FXML private Button adminButton;
    @FXML private ScrollPane scrollpain;
    @FXML private Circle userProficCircle;
    private EventList eventList;
    private DataSource<EventList> datasource;

    @FXML TextField searchBox;
    @FXML Label accountnameLabel;
    private User account ;
    private UserList userList;
    private int column = 0;
    private int row = 1;
    private DataSource<UserList> datasourceUser;

    @FXML public void initialize() {
        datasource = new EventDataSource("data", "event.csv");
        eventList = datasource.readData();
        adminButton.setVisible(false);
        datasourceUser = new UserDataSource("data", "login.csv");
        userList = datasourceUser.readData();
        String username = (String) FXRouter.getData();
        account = userList.findUserByUserName(username);
        userProficCircle.setFill(new ImagePattern(new Image("file:" + "data/UserProfilePicture/" + account + ".png")));
        accountnameLabel.setText(account.getAccountName());
        if(account.isAdmin()) {
            adminButton.setVisible(true);
        }

        for(Event event: eventList.getAllEvent()) {
            if(event.getDueTime() > System.currentTimeMillis()) {
                showEvent(event);
            }
        }
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchFn(newValue);
        });
    }

    private void showEvent(Event event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cs211/project/views/event-tab-info.fxml"));
            AnchorPane eventinfoTab = loader.load();
            EventTabController infoTabController = loader.getController();
            infoTabController.setData(event,userList.findUserByUserName(event.getEventOwner()).getAccountName());

            eventinfoTab.setOnMouseClicked(activity -> {
                try {
                    Pair<String , String> sender = new Pair<String, String>(event.getEventID(), account.getUserName());
                    FXRouter.goTo("event-view", sender);
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
    @FXML
    public void goAdminview() {
        try {
            FXRouter.goTo("admin-view", account.getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goProflie() {
        try {
            FXRouter.goTo("profile-view", account.getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
    @FXML
    public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", account.getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goChat() {
        try {
            FXRouter.goTo("chat-view", account.getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void SearchFn(String searchTerm) {
        eventContrainer.getChildren().clear();
        for (Event event : eventList.getAllEvent()) {
            if (searchTerm == null || searchTerm.isEmpty() || event.getEventName().toLowerCase().contains(searchTerm.toLowerCase())) {
                showEvent(event);
            }
        }
    }

}