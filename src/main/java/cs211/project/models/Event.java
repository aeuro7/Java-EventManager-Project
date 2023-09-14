package cs211.project.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private String eventID;
    private String eventName;
    private long startTime;
    private long dueTime;
    private String info;
    private double maxSeat;

    private double leftSeat;
    private double bookedSeat;

    private String location;

    private double limitStaffPT; // limit staff per team of the event


    public Event(String eventName, long startTime ,long dueTime, String info, double maxSeat, String location, double limitStaffPT) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.dueTime = dueTime;
        this.info = info;
        this.maxSeat = maxSeat;
        this.leftSeat = maxSeat;
        this.bookedSeat = 0;
        this.location = location;
        this.limitStaffPT = limitStaffPT;
        this.eventID = String.valueOf(System.currentTimeMillis());
    }
    public Event(String eventName, long startTime ,long dueTime, String info, double maxSeat, double leftSeat, double booked, String location, double limitStaffPT, String id) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.dueTime = dueTime;
        this.info = info;
        this.maxSeat = maxSeat;
        this.leftSeat = leftSeat;
        this.bookedSeat = booked;
        this.location = location;
        this.limitStaffPT = limitStaffPT;
        this.eventID = id;
    }

    public String getEventName() {
        return eventName;
    }
    public String getEventID() {return this.eventID;}

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDueTime() {
        return dueTime;
    }

    public void setDueTime(long dueTime) {
        this.dueTime = dueTime;
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

    public double getBookedSeat() {return this.bookedSeat;}
    public double changeLimitStaff(double newLimitStaffPT) {
        if(newLimitStaffPT != 0) {
            this.limitStaffPT = newLimitStaffPT;
        }
        return limitStaffPT;
    }

}




