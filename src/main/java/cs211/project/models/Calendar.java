package cs211.project.models;

public class Calendar {
    private String calendarName;
    private String eventLinkName;
    private long startTime;
    private long dueTime;
    private String detail;

    public Calendar(String name, String event, long startTime, long dueTime) {
        this.calendarName = name;
        this.eventLinkName = event;
        this.startTime = startTime;
        this.dueTime = dueTime;
        this.detail = "";
    }
    public Calendar(String name, String event, long startTime, long dueTime, String info) {
        this(name, event, startTime, dueTime);
        this.detail = info;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public String getEventLinkName() {
        return eventLinkName;
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

    public void setEventLinkName(String eventLinkName) {
        this.eventLinkName = eventLinkName;
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