package cs211.project.models.team;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<TeamStaff> teamStaffList;

    private String eventID;

    public String getEventID() {
        return eventID;
    }

    public Team() {
        teamStaffList = new ArrayList<>();
    }

    public void addTeamStaff(TeamStaff teamStaff) {
        teamStaffList.add(teamStaff);
    }

    public void addTeamStaff(String username){
        TeamStaff teamStaff = new TeamStaff(username,"CREW");
        teamStaffList.add(teamStaff);
    }

    public void addTeamStaff(String username,String role){
        TeamStaff teamStaff = new TeamStaff(username,role);
        teamStaffList.add(teamStaff);
    }

    public void removeTeamStaff(TeamStaff teamStaff) {
        teamStaffList.remove(teamStaff);
    }

    public List<TeamStaff> getAllTeamStaff() {
        return teamStaffList;
    }

}
