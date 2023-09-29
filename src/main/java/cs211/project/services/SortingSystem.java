package cs211.project.services;

import cs211.project.models.Event;
import cs211.project.models.users.User;

import java.util.Comparator;

public interface SortingSystem {
    Comparator<User> sortByLastLogin = Comparator.comparing(User::getLastLoginTimestamp).reversed();


}