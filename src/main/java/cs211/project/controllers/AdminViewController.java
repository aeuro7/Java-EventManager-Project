package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataSource;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminViewController {
    @FXML private TableView<User> userTableView;
    @FXML private TextField searchBox;
    private UserList userList;
    private DataSource<UserList> datasource;

    @FXML public void initialize() {
        datasource = new UserDataSource("data", "login.csv");
        userList = datasource.readData();
        showTable(userList);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchFn(newValue);
        });

        userTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue observable, User oldValue, User newValue) {
                if (newValue != null) {
                    try {
                        FXRouter.goTo("admin-edit", newValue.getUserName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void showTable(UserList userList) {
        TableColumn<User, String> userNameColumn = new TableColumn<>("Username");
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn<User, String> accountNameColumn = new TableColumn<>("Account Name");
        accountNameColumn.setCellValueFactory(new PropertyValueFactory<>("accountName"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<User, String> timestampColumn = new TableColumn<>("Last Login Timestamp");
        timestampColumn.setCellValueFactory(cellData -> {
            long timestamp = cellData.getValue().getLastLoginTimestamp();
            String formattedTimestamp = formatTimestamp(timestamp); // Format the timestamp
            return new SimpleStringProperty(formattedTimestamp);
        });

        userTableView.getColumns().clear();
        userTableView.getColumns().add(userNameColumn);
        userTableView.getColumns().add(accountNameColumn);
        userTableView.getColumns().add(roleColumn);
        userTableView.getColumns().add(timestampColumn);

        userTableView.getItems().clear();

        for (User user: userList.getAllUser()) {
            userTableView.getItems().add(user);
        }
    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
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
    private void SearchFn(String searchTerm) {
        searchTerm = searchTerm.toLowerCase().trim();
        userTableView.getItems().clear();

        for (User user : userList.getAllUser()) {
            if (user.getUserName().toLowerCase().contains(searchTerm)) {
                userTableView.getItems().add(user);
            }
        }
    }
}