package cs211.project.models;

public class User {
    private final String userName; // userName เปลี่ยนไม่ได้
    private String accountName; // accountName เปลี่ยนได้
    private String passWord; // passWord เปลี่ยนได้
    private String role;

    public User(String userName, String accountName, String passWord) {
        this.accountName = accountName;
        this.userName = userName;
        this.passWord = passWord;
        this.role = "user";
    }
    public User(String userName, String accountName, String passWord, String role) {
        this.accountName = accountName;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public boolean isThisAccout(String userName) {
        return(this.userName.equals(userName));
    } //เช็คว่า UserName ที่เข้ามาตรงกับ Obj นี้หรือไม่ Return เป็น True/false

    public String getUserName() {return this.userName;}
    public String getAccountName() {return this.accountName;}
    public String getPassWord() {return this.passWord;} // use For write on cas ONLY!!!
    public String getRole() {return this.role;}
    public void changePassWord(String newPassword) {
        if(!newPassword.equals("")) {
            this.passWord = newPassword;
        }
    }// method เปลี่ยนรหัสแต่รหัสที่เข้ามาต้องไม่เป็น Empty String

    public boolean isPasswordCorrect(String passWord) {
        return this.passWord.equals(passWord);
    } // ตรวจสอบ รหัสที่เข้ามา ว่าตรงกันมั้ย Return เป็น True/false

    public boolean authenticate(String userName, String passWord) {
        return isThisAccout(userName) && isPasswordCorrect(passWord);
    } // ตรวจสอบรหัสผ่านและ userName ใช้ตอน Login

    public boolean isAdmin() {return this.role.equals("admin");}

    public void thisIsAdmin() {this.role = "admin";}

    public void thisIsUser() {this.role = "user";}
    public void changeAccountName(String name) {
        if(!name.equals("")) {
            this.accountName = name;
        }
    }// method เปลี่ยนชื่อแต่ชื่อที่เข้ามาต้องไม่เป็น Empty String

    @Override
    public String toString() {
        return userName;
    } // ให้ฟังค์ชั่นนี้ คืนค่าแค่UserName
}