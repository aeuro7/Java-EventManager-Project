package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.models.users.User;
import cs211.project.models.users.UserList;
import cs211.project.services.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ManageEventController {
    private Event selectEvent;
    private String account;
    private DataSource<MemberList> memberDatasource;
    private DataSource<TeamList> teamDatasource;
    @FXML private Circle profilepicCircle;
    @FXML private TableView<Object> listTableView;
    @FXML private Label eventnameLabel;
    @FXML private Button addTeamPopup;
    @FXML private AnchorPane teamPopup;


    @FXML private TextField teamnameTextfield;


    @FXML public void initialize() {
        selectEvent = (Event) FXRouter.getData();
        eventnameLabel.setText(selectEvent.getEventName());
        account = selectEvent.getEventOwner();
        profilepicCircle.setFill(new ImagePattern(new Image("file:" + selectEvent.getEventPicture())));
        memberDatasource = new MemberDataSource("data", "member.csv");
        teamDatasource = new TeamDataSource("data", "team.csv");
        addTeamPopup.setVisible(false);
        popupClosed();
    }

    public void goEdit() {
        try {
            FXRouter.goTo("edit-event-view", selectEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void cancelEvent() {
        //change status to offline on event object
    }

    public void showAudienceOnTable() {
        addTeamPopup.setVisible(false);
        MemberList memberList = memberDatasource.readData();

        listTableView.getItems().clear();

        TableColumn<Object, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(param -> {
            Object obj = param.getValue();
            if (obj instanceof Member) {
                Member member = (Member) obj;
                return new SimpleStringProperty(member.getUsername());
            } else if (obj instanceof Team) {
                Team team = (Team) obj;
                return new SimpleStringProperty(team.getNameTeam());
            } else {
                return new SimpleStringProperty("");
            }
        });

        TableColumn<Object, String> roleColumn = new TableColumn<>("role");
        roleColumn.setCellValueFactory(param -> {
            Object obj = param.getValue();
            if (obj instanceof Member) {
                Member member = (Member) obj;
                return new SimpleStringProperty(member.getRole());
            } else {
                return new SimpleStringProperty("");
            }
        });

        listTableView.getColumns().clear();

        setCenterAlignment(nameColumn);
        setCenterAlignment(roleColumn);
        nameColumn.setMinWidth(148);
        roleColumn.setMinWidth(150);

        listTableView.getColumns().add(nameColumn);
        listTableView.getColumns().add(roleColumn);

        for (Member member: memberList.getMemberList()) {
            if(member.getEventID().equals(selectEvent.getEventID())) {
                if(member.isAudience()) {
                    listTableView.getItems().add(member);
                }
            }
        }
    }
    private void setCenterAlignment(TableColumn<Object, String> column) {
        column.setCellFactory(tc -> new TableCell<Object, String>() {
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

    public void showTeamOnTable() {
        addTeamPopup.setVisible(true);
        listTableView.getItems().clear();

        TeamList teamList = teamDatasource.readData();

        TableColumn<Object, String> nameColumn = new TableColumn<>("Team Name");
        nameColumn.setCellValueFactory(param -> {
            Object obj = param.getValue();
            if (obj instanceof Member) {
                Member member = (Member) obj;
                return new SimpleStringProperty(member.getUsername());
            } else if (obj instanceof Team) {
                Team team = (Team) obj;
                return new SimpleStringProperty(team.getNameTeam());
            } else {
                return new SimpleStringProperty("");
            }
        });

        listTableView.getColumns().clear();

        setCenterAlignment(nameColumn);
        nameColumn.setMinWidth(298);

        listTableView.getColumns().add(nameColumn);

        for (Team team: teamList.getAllTeams()) {
            if(team.getEventID().equals(selectEvent.getEventID())) {
                listTableView.getItems().add(team);
            }
        }
    }

    public void popupClosed() {
        teamPopup.setVisible(false);
    }
    public void addTeamPopup() {
        teamPopup.setVisible(true);
    }
    public void addTeamButton() {
        String teamName =  teamnameTextfield.getText();
        TeamList teamList = teamDatasource.readData();
        if(!teamName.equals("")) {
            boolean check = true;
            for(Team team: teamList.getAllTeams()) {
                if(teamName.equals(team.getNameTeam())) {
                    check = false;
                }
            }
            if(check) {
                Team newTeam = new Team(teamName, selectEvent.getEventID());
                teamList.addTeam(newTeam);
                teamDatasource.writeData(teamList);
                teamnameTextfield.clear();
                showTeamOnTable();
                popupClosed();
            }
        }
    }
    public void goeditCalendar() {

    }



    @FXML public void goProflie() {
        try {
            FXRouter.goTo("profile-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goChat() {
        try {
            FXRouter.goTo("chat-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goLogout() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoBook() {
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
    }
}
