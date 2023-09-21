package cs211.project.models;

import java.util.ArrayList;

public class CalendarList {
    private ArrayList<Calendar> calendars;

    public CalendarList() {
        calendars = new ArrayList<>();
    }

    public void addNewCalendar(Calendar calendar) {
        calendars.add(calendar);
    }
    public void addNewCalendar(String name, String event, String faction, long startTime, long dueTime, String info) {
        Calendar calendar = new Calendar(name, event, faction, startTime, dueTime, info);
        addNewCalendar(calendar);
    }

    public Calendar findCalendarByName(String name) {
        for(Calendar calendar: calendars) {
            if(calendar.getCalendarName().equals(name)) {
                return calendar;
            }
        }
        return null;
    }

    public ArrayList<Calendar> getCalendars() {
        return calendars;
    }
}
