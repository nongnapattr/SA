package yote.workschedule.Model;

public class Receipt {

    private int r_id;
    private String o_date;
    private String o_time;
    private String o_detail;
    private String amount;

    public Receipt(int r_id, String o_date, String o_time, String o_detail, String amount) {
        this.r_id = r_id;
        this.o_date = o_date;
        this.o_time = o_time;
        this.o_detail = o_detail;
        this.amount = amount;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
