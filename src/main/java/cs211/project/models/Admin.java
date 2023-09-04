package cs211.project.models;

import cs211.project.services.DataSource;
import cs211.project.services.UserDataSource;

public class Admin extends User {

    public Admin(String userName, String accountName, String passWord) {
        super(userName, accountName, passWord);
    }

    public Admin(String userName, String accountName, String passWord, String role, long timeStamp, String picture) {
        super(userName, accountName, passWord, role, timeStamp, picture);
    }
    public Admin(User account) {
        super(account.getUserName(), account.getAccountName(), account.getPassWord());
    }

    private UserList getAllUserInfo() {
        DataSource<UserList> dataSource = new UserDataSource("data", "login.csv");
        UserList userList = new UserList();
        userList = dataSource.readData();
        return userList;
    }
}
