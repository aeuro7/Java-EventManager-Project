package cs211.project.models.team;

import java.util.ArrayList;
import java.util.List;

public class TeamList {
    private ArrayList<Team> teamList;

    public TeamList() {
        teamList = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teamList.add(team);
    }
    public Team findTeamByNameAndEventID(String name, String eventID) {
        for(Team team: teamList) {
            if(team.getEventID().equals(eventID) && team.getNameTeam().equals(name)) {
                return team;
            }
        }
        return null;
    }

    public List<Team> getAllTeams() {
        return teamList;
    }

}
