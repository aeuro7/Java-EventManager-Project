package cs211.project.models.team;

import java.util.ArrayList;
import java.util.Objects;

public class Team {
    private ArrayList<TeamStaff> teamStaffList;
    private String nameTeam;
    private String eventID;
    private String leaderName;
    private final long maxStaff;
    private long seatLeft;
    private long startJoin;
    private long endJoin;
    public String getEventID() {
        return eventID;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public Team(String nameTeam, String eventID, long limitStaff,long startJoin,long endJoin) {
        teamStaffList = new ArrayList<>();
        this.nameTeam = nameTeam;
        this.eventID = eventID;
        this.leaderName = "No Leader";
        maxStaff = limitStaff;
        this.startJoin = startJoin;
        this.endJoin = endJoin;
        setSeatLeft();
    }
    public Team(String nameTeam, String eventID, long limitStaff, String leaderName, long startJoin, long endJoin) {
        this(nameTeam, eventID, limitStaff, startJoin, endJoin);
        this.leaderName = leaderName;
        setSeatLeft();
    }

    public void addTeamStaff(TeamStaff teamStaff) {
        teamStaffList.add(teamStaff);
        setSeatLeft();
    }

    public void addTeamStaff(String username){
        TeamStaff teamStaff = new TeamStaff(username,"CREW");
        teamStaffList.add(teamStaff);
        setSeatLeft();
    }
    public ArrayList<TeamStaff> getAllTeamStaff() {
        return teamStaffList;
    }

    public String getLeaderName() {
        return leaderName;
    }
    public long getMaxStaff() {
        return maxStaff;
    }
    private void setSeatLeft() {
        long count = maxStaff;
        for(TeamStaff staff: teamStaffList) {
            if(staff.getRole().equals("BAN")) {
                continue;
            }
            count--;
        }
        this.seatLeft = count;
    }

    public boolean isInTeam(String username) {
        for(TeamStaff teamStaff: teamStaffList) {
            if(teamStaff.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public boolean isThisGuyAreBaned(String username) {
        for(TeamStaff teamStaff: teamStaffList) {
            if(teamStaff.getName().equals(username)) {
                if(teamStaff.getRole().equals("BAN")) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Team otherTeam = (Team) obj;
        return Objects.equals(this.eventID, otherTeam.eventID);
    }

    public long getSeatLeft() {
        return seatLeft;
    }

    public long getStartJoin() {
        return startJoin;
    }
    public long getEndJoin() {
        return endJoin;
    }

}