package yote.workschedule.Model;

public class All {

    private int c_id;

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public String getName() {
        return name;
    }

    public All(int o_id, int c_id,String amount, String detail, String date, String time) {
        this.o_id = o_id;
        this.c_id = c_id;
        this.amount = amount;
        this.detail = detail;
        this.date = date;
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    private int o_id;
    private String name;
    private String phoneNumber;
    private String amount;
    private String detail;
    private String date;
    private String time;

    public int getId() {
        return c_id ;
    }
}
