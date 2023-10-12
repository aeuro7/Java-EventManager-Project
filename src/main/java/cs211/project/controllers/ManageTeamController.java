package cs211.project.controllers;

import cs211.project.models.eventHub.Event;
import cs211.project.models.eventHub.EventList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManageTeamController {

    @FXML private TableView<Team> teamTableView;
    @FXML private TextField searchBox;

    private TeamList fullteamList;
    private TeamList teamList = new TeamList();
    private String account = (String) FXRouter.getData();
    private EventList eventList;

    @FXML
    private void initialize() {
        DataSource<TeamList> dataSource = new TeamDataSource("data", "team.csv");
        fullteamList = dataSource.readData();
        eventList = (new EventDataSource("data", "event.csv").readData());
        fullteamList = (new TeamDataSource("data", "team.csv").readData());

        List<Pair> joinData = new ArrayList<>();

        for(Event event: eventList.getAllEvent()) {
            if(event.getEventOwner().equals(account)) {
                joinData.add(new Pair(event.getEventID(), "OWNER"));
            }
        }
        for(Team team: fullteamList.getAllTeams()) {
            if(team.isInTeam(account)) {
                if(!team.isThisGuyAreBaned(account)) {
                    joinData.add(new Pair(team.getEventID(), team.getNameTeam()));
                }
            }
        }

        for (Pair data : joinData) {
            for (Team team : fullteamList.getAllTeams()) {
                if(team.getEventID().equals(data.getKey()) && data.getValue().equals("OWNER")) {
                    teamList.addTeam(team);
                }
                else if (team.getEventID().equals(data.getKey()) && team.getNameTeam().equals(data.getValue())) {
                    teamList.addTeam(team);
                }

            }
        }
        showTable(teamList);
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchFn(newValue);
        });
    }

    private void showTable(TeamList teamList) {

        TableColumn<Team, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setCellValueFactory(cellData -> {
            Team team = cellData.getValue();
            return new SimpleStringProperty(eventList.findEventByID(team.getEventID()).getEventName());
        });

        TableColumn<Team, String> nameTeamColumn = new TableColumn<>("Team Name");
        nameTeamColumn.setCellValueFactory(new PropertyValueFactory<>("nameTeam"));

        TableColumn<Team, String> leaderNameColumn = new TableColumn<>("Leader Name");
        leaderNameColumn.setCellValueFactory(new PropertyValueFactory<>("leaderName"));


        teamTableView.getColumns().clear();
        teamTableView.getColumns().addAll(nameTeamColumn, eventNameColumn,leaderNameColumn);
        nameTeamColumn.setMinWidth(100);
        eventNameColumn.setMinWidth(248);
        leaderNameColumn.setMinWidth(198);

        teamTableView.getItems().clear();

        for (Team team: teamList.getAllTeams()) {
            teamTableView.getItems().add(team);
        }

        teamTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue observable, Team oldValue, Team newValue) {
                if (newValue != null) {
                    try {
                        Pair<String , Team> sender = new Pair<String, Team>(account, newValue);
                        FXRouter.goTo("manage-team-view", sender);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


    @FXML
    public void goCredit() {
        try {
            FXRouter.goTo("credit-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            FXRouter.goTo("main-menu", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoEditprofile() {
        try {
            FXRouter.goTo("profile-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goChat() {
        try {
            FXRouter.goTo("chat-view",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void SearchFn(String searchTerm) {
        searchTerm = searchTerm.toLowerCase().trim();
        teamTableView.getItems().clear();

        for (Team team : teamList.getAllTeams()) {
            if (team.getNameTeam().toLowerCase().contains(searchTerm)) {
                teamTableView.getItems().add(team);
            }
        }
    }
}

