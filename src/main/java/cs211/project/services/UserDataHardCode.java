package cs211.project.services;

import cs211.project.models.User;
import cs211.project.models.UserList;

public class UserDataHardCode implements DataSource {
    private UserList userList;

    public UserDataHardCode() {
        userList = new UserList();
    }

    @Override
    public UserList readData() {
        long currentTime = System.currentTimeMillis(); // Get current time

        User newUser = new User("admin", "admin", "admin", "admin", currentTime);
        User newUser2 = new User("baiboy001", "Big_bro", "1111", currentTime);
        User newUser3 = new User("humnoi112", "Big_Dig", "5555", currentTime);

        userList.addUser(newUser);
        userList.addUser(newUser2);
        userList.addUser(newUser3);

        return userList;
    }

    @Override
    public void writeData(Object o) {
        // Implement the writing logic here
    }
}
