package cs211.project.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private String eventID;
    private String eventName;
    private long startTime;
    private long dueTime;
    private String info;
    private long maxSeat;

    private long leftSeat;
    private long bookedSeat;

    private String location;

    private long limitStaffPT; // limit staff per team of the event
    private String eventOwner;
    private String eventPicture;


    public Event(String eventName, long startTime ,long dueTime, String info, long maxSeat, String location, long limitStaffPT, String eventOwner) {
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
        this.eventOwner = eventOwner;
        this.eventPicture = this.eventID;
    }
    public Event(String eventName, long startTime ,long dueTime, String info, long maxSeat, long leftSeat, long booked, String location, long limitStaffPT, String id, String eventOwner, String eventPicture) {
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
        this.eventOwner = eventOwner;
        this.eventPicture = eventPicture;
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

    public long getMaxSeat() {
        return maxSeat;
    }

    public void setMaxSeat(long maxSeat) {
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

    public long getLimitStaffPT() {
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
    public long getLeftSeat() {
        return this.leftSeat;
    }

    public long getBookedSeat() {return this.bookedSeat;}
    public long changeLimitStaff(long newLimitStaffPT) {
        if(newLimitStaffPT != 0) {
            this.limitStaffPT = newLimitStaffPT;
        }
        return limitStaffPT;
    }
    public String getEventOwner() {return this.eventOwner;}

    public String getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(String eventPicture) {
        this.eventPicture = eventPicture;
    }
}




