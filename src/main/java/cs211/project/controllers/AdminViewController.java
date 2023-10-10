package cs211.project.controllers;

import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserDataSource;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminViewController {
    @FXML private TableView<User> userTableView;
    @FXML private TextField searchBox;
    @FXML private Label label_name;
    @FXML private Circle adminProficCircle;
    private UserList userList;
    private DataSource<UserList> datasource;
    String account = (String) FXRouter.getData();
    @FXML public void initialize() {
        datasource = new UserDataSource("data", "login.csv");
        userList = datasource.readData();
        label_name.setText(userList.findUserByUserName(account).getAccountName());
        userList.sortUsersByLastTimeLogin();
        adminProficCircle.setFill(new ImagePattern(new Image("file:" + userList.findUserByUserName(account).getProfilePicture())));
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

        setCenterAlignment(userNameColumn);
        setCenterAlignment(accountNameColumn);
        setCenterAlignment(roleColumn);
        setCenterAlignment(timestampColumn);

        userNameColumn.setMinWidth(200);
        accountNameColumn.setMinWidth(200);
        roleColumn.setMinWidth(50);
        timestampColumn.setMinWidth(230);

        userTableView.getItems().clear();

        for (User user: userList.getAllUser()) {
            if(user.isAdmin()) {
                continue;
            }
            userTableView.getItems().add(user);
        }
    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }
    @FXML private void logoutButton() {
        try {
            FXRouter.goTo("login-view", account);
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
    @FXML private void changeAdminProfile() {
        try {
            FXRouter.goTo("admin-edit-profile", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private <T> void setCenterAlignment(TableColumn<T, String> column) {
        column.setCellFactory(tc -> new TableCell<T, String>() {
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

}