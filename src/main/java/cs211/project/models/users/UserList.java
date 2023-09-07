package cs211.project.models.users;

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

    public void changeInfo(String username, String accountName, String password) {
        for(User user : getAllUser()) {
            if(user.getUserName().equals(username)) {
                user.changeAccountName(accountName);
                user.changePassWord(password);
            }
        }
    }
    public void changeRole(String username) {
        for(User selectedUser : getAllUser()) {
            if(selectedUser.getUserName().equals(username)) {
                if(selectedUser.isAdmin()) {
                    selectedUser.thisIsUser();
                } else {
                    selectedUser.thisIsAdmin();
                }
            }
        }
    }
    public void deleteThisAccount(String username) {
        for(User selectedUser : getAllUser()) {
            if(selectedUser.getUserName().equals(username)) {
                userList.remove(selectedUser);
                break;
            }
        }
    }
}