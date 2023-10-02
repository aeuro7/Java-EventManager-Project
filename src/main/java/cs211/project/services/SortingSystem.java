package cs211.project.services;

import cs211.project.models.Event;
import cs211.project.models.users.User;

import java.util.Comparator;

public interface SortingSystem {
    Comparator<User> sortByLastLogin = Comparator.comparing(User::getLastLoginTimestamp).reversed();

    Comparator<Event> sortByNearCurrentDate = (event1, event2) -> {
        long diff1 = Math.abs(event1.getStartTime() - System.currentTimeMillis());
        long diff2 = Math.abs(event2.getStartTime() - System.currentTimeMillis());

        return Long.compare(diff1, diff2);
    };
}