package cs211.project.models.eventHub;

public class Membership {

    private String username;
    private String eventID;
    private String role;
    public Membership(String username, String eventID, String role) {
        this.username = username;
        this.eventID = eventID;
        this.role = role;
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
    public void setRole(String role) {
        this.role = role;
    }
}
