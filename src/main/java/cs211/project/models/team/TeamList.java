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

    public void removeTeam(Team team) {
        teamList.remove(team);
    }

    public List<Team> getAllTeams() {
        return teamList;
    }

}
