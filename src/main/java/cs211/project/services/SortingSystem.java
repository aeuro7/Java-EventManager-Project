package cs211.project.services;

import cs211.project.models.Calendar;
import cs211.project.models.Event;
import cs211.project.models.users.User;

import java.util.Comparator;

public interface SortingSystem {
    Comparator<User> sortByLastLogin = Comparator.comparing(User::getLastLoginTimestamp).reversed();

    Comparator<Event> eventSortByNearCurrentDate = (event1, event2) -> {
        long diff1 = event1.getStartTime() - System.currentTimeMillis();
        long diff2 = event2.getStartTime() - System.currentTimeMillis();

        if (diff1 < 0 && diff2 < 0) {
            return Long.compare(event2.getStartTime(), event1.getStartTime());
        } else if (diff1 < 0) {
            return -1;
        } else if (diff2 < 0) {
            return 1;
        } else {
            return Long.compare(diff1, diff2);
        }
    };

    Comparator<Calendar> calendarSortByNearCurrentDate = (calendar1, calendar2) -> {
        long diff1 = calendar1.getStartTime() - System.currentTimeMillis();
        long diff2 = calendar2.getStartTime() - System.currentTimeMillis();

        if (diff1 < 0 && diff2 < 0) {
            return Long.compare(calendar2.getStartTime(), calendar1.getStartTime());
        } else if (diff1 < 0) {
            return -1;
        } else if (diff2 < 0) {
            return 1;
        } else {
            return Long.compare(diff1, diff2);
        }
    };
}
