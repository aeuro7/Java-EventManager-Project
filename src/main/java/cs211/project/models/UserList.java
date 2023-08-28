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

    public boolean isUserNameExists(String username) {
        for (User user : getAllUser()) {
            if (user.getUserName().toLowerCase().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public User loginFn(String username, String password) {
        for(User user : getAllUser()) {
            if(user.authenticate(username, password)) {
                return user;
            }
        }
        return null;
    }
    public User findUserByUserName(String username) {
        for(User user : getAllUser()) {
            if(user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
}