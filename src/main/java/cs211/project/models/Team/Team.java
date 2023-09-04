package cs211.project.models.Team;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<TeamStaff> teamStaffList;

    public Team() {
        teamStaffList = new ArrayList<>();
    }

    public void addTeamStaff(TeamStaff teamStaff) {
        teamStaffList.add(teamStaff);
    }

    public void removeTeamStaff(TeamStaff teamStaff) {
        teamStaffList.remove(teamStaff);
    }

    public List<TeamStaff> getAllTeamStaff() {
        return teamStaffList;
    }

}
