package cs211.project.models;

import cs211.project.services.SortingSystem;

import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> eventList;

    public EventList() {
        eventList = new ArrayList<>();
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public ArrayList<Event> getAllEvent() {
        return eventList;
    }

    public Event findEventByID(String id) {
        for(Event event : getAllEvent()) {
            if(event.getEventID().equals(id)) {
                return event;
            }
        }
        return null;
    }

    public void sortEventByNearCurrentDate() {
        eventList.sort(SortingSystem.eventSortByNearCurrentDate);
    }
}
