package cs211.project.models.team;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private ArrayList<TeamStaff> teamStaffList;

    private String nameTeam;

    private String eventID;
    private String leaderName;
    private final long maxStaff;
    private long seatLeft;

    public String getEventID() {
        return eventID;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public Team(String nameTeam, String eventID, long limitStaff) {
        teamStaffList = new ArrayList<>();
        this.nameTeam = nameTeam;
        this.eventID = eventID;
        this.leaderName = "No Leader";
        maxStaff = limitStaff;
        setSeatLeft();
    }
    public Team(String nameTeam, String eventID, long limitStaff, String leaderName) {
        this(nameTeam, eventID, limitStaff);
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

    public void addTeamStaff(String username,String role){
        TeamStaff teamStaff = new TeamStaff(username,role);
        teamStaffList.add(teamStaff);
        setSeatLeft();
    }

    public void removeTeamStaff(TeamStaff teamStaff) {
        teamStaffList.remove(teamStaff);
        setSeatLeft();
    }

    public ArrayList<TeamStaff> getAllTeamStaff() {
        return teamStaffList;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public boolean setLeaderName(String leaderName) {
        for(TeamStaff staff: teamStaffList) {
            if(leaderName.equals(staff.getName())) {
                this.leaderName = leaderName;
                return true;
            }
        }
        return false;
    }

    public long getMaxStaff() {
        return maxStaff;
    }
    private void setSeatLeft() {
        long count = 0;
        for(TeamStaff staff: teamStaffList) {
            count++;
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

    public long getSeatLeft() {
        return seatLeft;
    }
}