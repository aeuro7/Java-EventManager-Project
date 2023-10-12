package cs211.project.models.eventHub;

public class Calendar {
    private String calendarName;
    private String eventID;
    private String faction;
    private boolean status;
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
        this.status = false;
    }
    public Calendar(String name, String event, String faction, long startTime, long dueTime, String info) {
        this(name, event, faction, startTime, dueTime);
        this.detail = info;
    }
    public Calendar(String name, String event, String faction, long startTime, long dueTime, boolean status, String info) {
        this(name, event, faction, startTime, dueTime);
        this.detail = info;
        this.status = status;
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
    public boolean isDone() {
        return this.status;
    }
    public void itDone() {
        this.status = true;
    }
}