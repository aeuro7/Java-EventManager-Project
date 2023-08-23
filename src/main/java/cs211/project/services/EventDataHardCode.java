package cs211.project.services;

import cs211.project.models.Event;
import cs211.project.models.EventList;

public class EventDataHardCode implements DataSource{
    private EventList eventList;
    public EventDataHardCode() {eventList = new EventList();}

    @Override
    public EventList readData() {
        Event event1 = new Event("Samsung Better Than Apple", "29/8/2023", "10:00", "29/8/2023", "15:00", "..", 100, "Apple Central World", 5, "admin");
        Event event2 = new Event("Taylor Swift", "10/9/2023", "10:00", "10/9/2023", "15:00", "..", 100, "SC709", 5, "admin");
        Event event3 = new Event("EXO Planet", "12/9/2023", "18:00", "12/9/2023", "22:00", "..", 100, "Sanam Jan", 5, "admin");
        eventList.addEvent(event1);
        eventList.addEvent(event2);
        eventList.addEvent(event3);
        return eventList;
    }
    @Override
    public void writeData(Object o) {

    }
}
