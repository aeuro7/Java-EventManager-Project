package cs211.project.services;

import cs211.project.models.User;
import cs211.project.models.UserList;

public class Test {
    public static void main(String[] args) {
        String fileDirectoryName = "test_data";
        String fileName = "test_users.csv";

        // Create a test UserList
        UserList userList = new UserList();
        userList.addUser(new User("John Doe", "john123", "password123"));
        userList.addUser(new User("Jane Smith", "jane456", "securePwd", "admin"));

        // Create a UserDataSource instance
        UserDataSource userDataSource = new UserDataSource(fileDirectoryName, fileName);

        // Write data to the file
        userDataSource.writeData(userList);

        // Read data from the file
        UserList readUserList = userDataSource.readData();

        // Print the read data (for verification)
        for (User user : readUserList.getAllUser()) {
            System.out.println("User: " + user.getUserName()
                    + ", Account: " + user.getAccountName()
                    + ", Password: " + user.getPassWord()
                    + ", Role: " + user.getRole());
        }
    }
}
