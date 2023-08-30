package cs211.project.services;

import cs211.project.models.Event;
import cs211.project.models.EventList;

public class EventDataHardCode implements DataSource{
    private EventList eventList;
    public EventDataHardCode() {eventList = new EventList();}

    @Override
    public EventList readData() {
        Event event1 = new Event("Samsung Better", "29/8/2023", "10:00", "29/8/2023", "15:00", "..", 100, "Apple Central World", 5, "admin");
        Event event2 = new Event("Taylor Swift", "10/9/2023", "10:00", "10/9/2023", "15:00", "..", 100, "SC709", 5, "admin");
        Event event3 = new Event("EXO Planet", "12/9/2023", "18:00", "12/9/2023", "22:00", "..", 100, "Proxima Centauri", 5, "admin");
        Event event4 = new Event("ThreeManDown ", "7/7/2023", "19:00", "7/7/2023", "23:00", "..", 100, "Central Eastville", 5, "admin");
        Event event5 = new Event("Charlie Puth", "4/10/2023", "20:00", "4/10/2023", "22:00", "..", 100, "Oldtrafford stadium", 5, "admin");
        Event event6 = new Event("Ariana Grande", "31/12/2023", "18:00", "31/12/2023", "22:00", "..", 100, "Anfield stadium", 5, "admin");

        eventList.addEvent(event1);
        eventList.addEvent(event2);
        eventList.addEvent(event3);
        eventList.addEvent(event4);
        eventList.addEvent(event5);
        eventList.addEvent(event6);


        return eventList;
    }
    @Override
    public void writeData(Object o) {

    }
}
