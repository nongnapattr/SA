package yote.workschedule.Model;

public class Member {

    private int c_id;
    private String name;
    private String phoneNumber;
    private String userName;
    private String password;
    private String role;
    private String status;

    public Member(String c_name, int c_id, String c_tel, String c_username, String c_password) {
        this.name = c_name;
        this.c_id = c_id;
        this.phoneNumber = c_tel;
        this.userName = c_username;
        this.password = c_password;
    }

    public Member(String c_name, int c_id, String c_tel, String c_username, String c_password,String status, String role) {
        this.name = c_name;
        this.c_id = c_id;
        this.phoneNumber = c_tel;
        this.userName = c_username;
        this.password = c_password;
        this.status = status;
        this.role = role;

    }

    public Member() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getId() {
        return c_id;
    }

    public void setId(int id) {
        this.c_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return name + "," + userName + " , " + password + " , " + phoneNumber;
    }

    public String toCsv() {
        return name + " , " + userName + " , " + password + " , " + phoneNumber;
    }

}
