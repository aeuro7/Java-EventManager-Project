package cs211.project.services;

import cs211.project.models.User;
import cs211.project.models.UserList;

import java.util.ArrayList;

public class UserDataHardCode implements DataSource {
    private UserList userList;
    public UserDataHardCode() {
        userList = new UserList();
    }
    @Override
    public UserList readData() {
        User newUser = new User("admin", "admin", "admin");
        User newUser2 = new User("baiboy001", "Big_bro", "1111");
        User newUser3 = new User("humnoi112", "Big_Dig", "5555");
        userList.addUser(newUser);
        userList.addUser(newUser2);
        userList.addUser(newUser3);
        return userList;
    }
    @Override
    public void writeData(Object o) {

    }
}