package cs211.project.services;

import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;

public class TeamHardCode implements DataSource<TeamList> {

    public TeamList readData() {
        Team teamA = new Team("TeamA","EXO");
        teamA.addTeamStaff("euro","1");
        teamA.addTeamStaff("bam","0");
        TeamList teamList = new TeamList();
        teamList.addTeam(teamA);
        return teamList;
    }

    public void writeData(TeamList o) {

    }


}
