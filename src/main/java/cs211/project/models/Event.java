package cs211.project.models;

public class Event {
    private String eventName;
    private String startDate;
    private String dueDate;
    private String startTime;
    private String dueTime;
    private String info; //detail on createEvent page
    private double maxSeat;

    private double leftSeat;
    private double bookedSeat;

    private String location;
    private double limitStaffPT; // limit staff per team of the event

    private final User eventOwner;

    public Event(String eventName, String startDate, String startTime, String dueDate, String dueTime, String info, double maxSeat, String location, double limitStaffPT, User eventOwner) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.startTime = startTime;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.info = info;
        this.maxSeat = maxSeat;
        this.leftSeat = maxSeat;
        this.bookedSeat = 0;
        this.location = location;
        this.limitStaffPT = limitStaffPT;
        this.eventOwner = eventOwner;
    }

    public String getEventName() {
        return eventName;
    }
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        if(!startDate.equals("")) {
            this.startDate = startDate;
        }
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        if(!dueDate.equals("")) {
            this.dueDate = dueDate;
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        if(!startTime.equals("")) {
            this.startTime = startTime;
        }
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        if(!dueTime.equals("")) {
            this.dueTime = dueTime;
        }
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if(!info.equals("")) {
            this.info = info;
        }
    }

    public double getMaxSeat() {
        return maxSeat;
    }

    public void setMaxSeat(double maxSeat) {
        if(bookedSeat <= maxSeat) {
            this.maxSeat = maxSeat;
        }
        leftSeatAssign();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if(!location.equals("")) {
            this.location = location;
        }
    }

    public double getLimitStaffPT() {
        return limitStaffPT;
    }

    public User getEventOwner() {
        return eventOwner;
    }

    public void changeEventName(String newEventName) {
        if(!newEventName.equals("")) {
            this.eventName = newEventName;
        }
    }

    public void boooking() {
        if(bookedSeat < maxSeat) this.bookedSeat++;
        leftSeatAssign();
    }

    private void leftSeatAssign() {
        leftSeat = maxSeat-bookedSeat;
    }
    public double getLeftSeat() {
        return this.leftSeat;
    }
    public double changeLimitStaff(double newLimitStaffPT) {
        if(newLimitStaffPT != 0) {
            this.limitStaffPT = newLimitStaffPT;
        }
        return limitStaffPT;
    }
}




