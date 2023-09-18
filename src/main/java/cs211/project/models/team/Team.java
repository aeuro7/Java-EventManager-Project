package cs211.project.models.team;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private ArrayList<TeamStaff> teamStaffList;

    private String nameTeam;

    private String eventID;

    public String getEventID() {
        return eventID;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public Team(String nameTeam, String eventID) {
        teamStaffList = new ArrayList<>();
        this.nameTeam = nameTeam;
        this.eventID = eventID;
    }

    public void addTeamStaff(TeamStaff teamStaff) {
        teamStaffList.add(teamStaff);
    }

    public void addTeamStaff(String username){
        TeamStaff teamStaff = new TeamStaff(username,"0");
        teamStaffList.add(teamStaff);
    }

    public void addTeamStaff(String username,String role){
        TeamStaff teamStaff = new TeamStaff(username,role);
        teamStaffList.add(teamStaff);
    }

    public void removeTeamStaff(TeamStaff teamStaff) {
        teamStaffList.remove(teamStaff);
    }

    public ArrayList<TeamStaff> getAllTeamStaff() {
        return teamStaffList;
    }

}
