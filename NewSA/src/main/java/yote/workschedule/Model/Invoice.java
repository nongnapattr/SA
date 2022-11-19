package yote.workschedule.Model;

public class Invoice extends Member {
    private int o_id;
    private int o_id1;
    private String amount;
    private int c_id;
    private String o_date;
    private String o_time;
    private String o_detail;
    private String o_status;
    private int c_id1;
    private String phoneNumber;
    private String userName;
    private String password;
    private String role;
    private String status;
    private String c_name;
    private String c_username;

    private int i_id;

    private String name;
    private String date;
    private String time;
    private String detail;

    public Invoice(String o_id, String amount) {
        this.o_id = Integer.parseInt(o_id);
        this.amount = amount;
    }

    public Invoice(String o_id, String amount,int i_id) {
        this.o_id = Integer.parseInt(o_id);
        this.amount = amount;
        this.i_id = i_id;
    }


    public Invoice(int o_id,String amount,int i_id,int c_id,String o_date,String o_time,String o_detail,String o_status,String c_name,String c_username){
        this.o_id = o_id;
        this.amount = amount;
        this.i_id = i_id;
        this.c_id = c_id;
        this.o_date = o_date;
        this.o_time = o_time;
        this.o_detail = o_detail;
        this.o_status = o_status;
        this.c_name = c_name;
        this.c_username = c_username;
    }

    public Invoice(int o_id, String amount,int o_id1,int c_id,String o_date,String o_time,String o_detail,String o_status, String c_name, int c_id1, String c_tel, String c_username, String c_password,String status, String role) {
        this.o_id = o_id;
        this.amount = amount;
        this.o_id1 = o_id1;
        this.c_id = c_id;
        this.o_date = o_date;
        this.o_time = o_time;
        this.o_detail= o_detail;
        this.o_status = o_status;
        this.name = c_name;
        this.c_id1 = c_id1;
        this.phoneNumber = c_tel;
        this.userName = c_username;
        this.password = c_password;
        this.status = status;
        this.role = role;
    }

    public Invoice(int o_id, String amount,int o_id1,int c_id,String o_date,String o_time,String o_detail,String o_status,String c_name, int c_id1, String c_tel, String c_username, String c_password) {
        this.o_id = o_id;
        this.amount = amount;
        this.o_id1 = o_id1;
        this.c_id = c_id;
        this.o_date = o_date;
        this.o_time = o_time;
        this.o_detail= o_detail;
        this.name = c_name;
        this.c_id1 = c_id1;
        this.phoneNumber = c_tel;
        this.userName = c_username;
        this.password = c_password;
        this.o_status = o_status;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public int getI_id() {
        return i_id;
    }

    public void setI_id(int i_id) {
        this.i_id = i_id;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getO_status() {
        return o_status;
    }

    public void setO_status(String o_status) {
        this.o_status = o_status;
    }

    public int getO_id1() {
        return o_id1;
    }

    public void setO_id1(int o_id1) {
        this.o_id1 = o_id1;
    }

    public int getC_id1() {
        return c_id1;
    }

    public void setC_id1(int c_id1) {
        this.c_id1 = c_id1;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
    }

    public String getO_time() {
        return o_time;
    }

    public void setO_time(String o_time) {
        this.o_time = o_time;
    }

    public String getO_detail() {
        return o_detail;
    }

    public void setO_detail(String o_detail) {
        this.o_detail = o_detail;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
