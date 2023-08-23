package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataHardCode;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class AdminViewController {
    @FXML private TableView<User> userTableView;
    private UserList userList;
    private DataSource<UserList> datasource;

    @FXML public void initialize() {
        datasource = new UserDataHardCode();
        userList = datasource.readData();
        showTable(userList);
    }

    private void showTable(UserList userList) {
        TableColumn<User, String> userNameColumn = new TableColumn<>("Username");
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn<User, String> accountNameColumn = new TableColumn<>("Account Name");
        accountNameColumn.setCellValueFactory(new PropertyValueFactory<>("accountName"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        userTableView.getColumns().clear();
        userTableView.getColumns().add(userNameColumn);
        userTableView.getColumns().add(accountNameColumn);
        userTableView.getColumns().add(roleColumn);

        userTableView.getItems().clear();

        for (User user: userList.getAllUser()) {
            userTableView.getItems().add(user);
        }
    }
    @FXML private void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void logoutButton() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void goProflie() {
        try {
            FXRouter.goTo("profile-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}