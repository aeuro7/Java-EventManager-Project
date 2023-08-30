package cs211.project.models;

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

    public boolean isThisEventExist(String Event) {
        for(Event event: eventList) {
            if(event.getEventName().equals(Event)) {
                return true;
            }
        }
        return false;
    }

    public Event findEventByEventName(String eventName) {
        for(Event event : getAllEvent()) {
            if(event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    //ขอฟังค์ชั่นที่แบบปังๆ ที่จะให้ พารามิเตอร์ เป็น string username แล้วคืนค่า เป็น eventlist ที่มี username นี้ เป็นเจ้าของ

}
