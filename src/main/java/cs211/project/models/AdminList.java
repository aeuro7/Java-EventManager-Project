package cs211.project.models;

import java.util.ArrayList;

public class AdminList{
    private ArrayList<Admin> adminList;

    public AdminList() {
        adminList = new ArrayList<>();
    }

    public void addAccount(Admin account) {
        adminList.add(account);
    }

    public ArrayList<Admin> getAllAdmins() {
        return adminList;
    }
}
