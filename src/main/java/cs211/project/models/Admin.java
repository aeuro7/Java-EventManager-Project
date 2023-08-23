package cs211.project.models;

import cs211.project.services.DataSource;
import cs211.project.services.UserDataHardCode;

public class Admin extends User {

    public Admin(String userName, String accountName, String passWord) {
        super(userName, accountName, passWord);
    }

    public Admin(String userName, String accountName, String passWord, String role, long timeStamp) {
        super(userName, accountName, passWord, role, timeStamp);
    }
    public Admin(User account) {
        super(account.getUserName(), account.getAccountName(), account.getPassWord());
    }

    private UserList getAllUserInfo() {
        DataSource<UserList> dataSource = new UserDataHardCode();
        UserList userList = new UserList();
        userList = dataSource.readData();
        return userList;
    }
}
