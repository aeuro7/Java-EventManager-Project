package cs211.project.controllers;

import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.models.team.TeamStaff;
import cs211.project.models.users.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class TeamMemberPopupController {
    @FXML private Label nameLabel;
    @FXML private Label statusLabel;
    @FXML private TableView<Team> teamTableView;
    @FXML private Circle profilepicCircle;
    @FXML private Button promoteButton;
    @FXML private Button banButton;
    @FXML private ChoiceBox<String> teamChoice;
    @FXML private Button confirmButton;

    private TeamList thisStaffHasJoinList = new TeamList();
    private TeamList thisStaffHasNotJoinList = new TeamList();

    public void setData(TeamStaff staff, String eventID, String teamName, User stffUser, TeamList teamList, String guyWhologin) {
        nameLabel.setText(stffUser.getAccountName());
        statusLabel.setText(staff.getRole());
        statusLabel.setText(staff.getRole());
        profilepicCircle.setFill(new ImagePattern(new Image("file:" + "data/UserProfilePicture/" + staff.getName() + ".png")));
        for(Team team: teamList.getAllTeams()) {
            if(team.getEventID().equals(eventID) && team.isInTeam(staff.getName())) {
                if(!team.getNameTeam().equals(teamName)) {
                    thisStaffHasJoinList.addTeam(team);
                }
            } else {
                thisStaffHasNotJoinList.addTeam(team);
            }
        }
        showTable(thisStaffHasJoinList);

    }

    private void showTable(TeamList teamList) {
        TableColumn<Team, String> teamNameColumn = new TableColumn<>("Team Name");
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));

        teamTableView.getColumns().clear();
        teamTableView.getColumns().add(teamNameColumn);

        teamTableView.getItems().clear();

        for (Team team: teamList.getAllTeams()) {
            teamTableView.getItems().add(team);
        }
    }
    @FXML private void promoteHandleButton() {

    }
    @FXML private void banMember() {

    }
    @FXML private void assignTeamButton() {

    }
    @FXML private void confirmAssignTeamButton() {

    }
}
