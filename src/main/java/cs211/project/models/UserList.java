package cs211.project.models;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList;

    public UserList() {
        userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public ArrayList<User> getAllUser() {
        return userList;
    }
}