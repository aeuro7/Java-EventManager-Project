package cs211.project.services;

import cs211.project.models.User;
import cs211.project.models.UserList;

import java.io.*;

public class UserDataSource implements DataSource<UserList> {
    private String fileDirectoryName;
    private String fileName;
    private UserList userList;
    public UserDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }
    @Override
    public UserList readData() {
        UserList userList = new UserList();
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                User newUser = new User(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), Long.parseLong(data[4]), data[5].trim());
                userList.addUser(newUser);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    @Override
    public void writeData(UserList userList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (User newUser: userList.getAllUser()){
                String line = newUser.getUserName() + ","
                        + newUser.getAccountName() + ","
                        + newUser.getPassWord() + ","
                        + newUser.getRole() + ","
                        + newUser.getLastLoginTimestamp() + ","
                        + newUser.getProfilePicture();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}