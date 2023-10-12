package cs211.project.models.eventHub;

import cs211.project.services.SortingSystem;

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
    }public void addNewCalendar(String name, String event, String faction, long startTime, long dueTime, boolean status, String info) {
        Calendar calendar = new Calendar(name, event, faction, startTime, dueTime, status, info);
        addNewCalendar(calendar);
    }

    public void sortCalendarByNearStartDate() {
        calendars.sort(SortingSystem.calendarSortByNearCurrentDate);
    }

    public void removeCalendar(Calendar target) {
        for(Calendar calendar: calendars) {
            if(calendar.equals(target)) {
                calendars.remove(calendar);
                break;
            }
        }
    }

    public ArrayList<Calendar> getCalendars() {
        return calendars;
    }
}
