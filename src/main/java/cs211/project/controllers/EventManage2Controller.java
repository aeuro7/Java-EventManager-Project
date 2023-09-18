package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class EventManage2Controller {


    @FXML private UserList userList;
    @FXML private TableView mainTable;

    @FXML private Button deletButton;
    @FXML private Button editEventButton;
    @FXML private Button teamButton;
    @FXML private Button customerButton;

    @FXML
    private void initialize() {
        showTable(userList);
    }

    private void showTable(UserList userList) {
        TableColumn<Event, String> userNameColumn = new TableColumn<>("Name");
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Event, String> userRoleColumn = new TableColumn<>("Role");
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("Role"));

        mainTable.getColumns().clear();

        mainTable.getColumns().add(userNameColumn);
        mainTable.getColumns().add(userRoleColumn);

        mainTable.getItems().clear();

//        for (Event event : eventList.getAllEvent()) {
//            eventTableView.getItems().add(event);
//        }

    }

    @FXML
    private void deletButton() {
    }


    @FXML
    private void editEventButton () {
    }
    @FXML
    private void teamButton() {
    }

    @FXML
    private void customerButton () {
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
    } @FXML private void goAccountView() {
        try {
            FXRouter.goTo("account-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}

