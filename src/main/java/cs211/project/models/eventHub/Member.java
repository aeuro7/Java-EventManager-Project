package cs211.project.models.eventHub;

public class Member {

    private String username;
    private String eventID;
    private boolean banStatus;
    private String role;
    public Member(String username, String eventID, String role) {
        this.username = username;
        this.eventID = eventID;
        this.role = role;
        banStatus = false;
    }
    public Member(String username, String eventID, String role, boolean banStatus) {
        this(username, eventID, role);
        this.banStatus = banStatus;
    }

    public String getUsername() {
        return username;
    }
    public String getEventID() {
        return eventID;
    }
    public String getRole() {
        return role;
    }
    public boolean ban() {
        if(banStatus) {
            return false;
        } else{
             banStatus = true;
             return true;
        }
    }
    public boolean unBan() {
        if(banStatus) {
            banStatus = false;
            return true;
        } else{
            return false;
        }
    }
    public boolean getBanStatus() {
        return banStatus;
    }
    public boolean isAudience() {
        if(this.role.equals("AUDIENCE")) {
            return true;
        } else {
            return false;
        }
    }
}
