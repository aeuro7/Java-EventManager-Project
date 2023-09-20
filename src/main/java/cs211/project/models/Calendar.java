package cs211.project.models;

public class Calendar {
    private String calendarName;
    private String eventID;
    private String faction;
    private long startTime;
    private long dueTime;
    private String detail;

    public Calendar(String name, String event, String faction, long startTime, long dueTime) {
        this.calendarName = name;
        this.eventID = event;
        this.faction = faction;
        this.startTime = startTime;
        this.dueTime = dueTime;
        this.detail = "";
    }
    public Calendar(String name, String event, String faction, long startTime, long dueTime, String info) {
        this(name, event, faction, startTime, dueTime);
        this.detail = info;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public String getEventID() {
        return eventID;
    }
    public String getFaction() {
        return faction;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getDueTime() {
        return dueTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    public void setFaction(String faction) {
        this.faction = faction;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public void setDueTime(long dueTime) {
        this.dueTime = dueTime;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
}