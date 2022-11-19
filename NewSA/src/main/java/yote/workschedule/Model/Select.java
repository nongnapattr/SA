package yote.workschedule.Model;

public class Select {

    private String o_detail;
    private String o_time;
    private String o_date;
    private int c_id;
    private String o_status;
    private int o_id;
    private String detail;
    private String date;
    private String time;
    private int O_id;

    public Select(String o_id, String o_date, String o_time, String o_detail) {
        this.o_id = Integer.parseInt(o_id);
        this.date = o_date;
        this.time = o_time;
        this.detail = o_detail;
    }

    public Select(int o_id, int c_id, String o_date, String o_time, String o_detail, String o_status) {
        this.o_id = o_id;
        this.c_id = c_id;
        this.o_date = o_date;
        this.o_time = o_time;
        this.o_detail = o_detail;
        this.o_status = o_status;
    }

    public Select(int o_id, int c_id, String o_date, String o_time, String o_detail) {
        this.o_id = o_id;
        this.c_id = Integer.parseInt(String.valueOf(c_id));
        this.o_date = o_date;
        this.o_time = o_time;
        this.o_detail = o_detail;
    }

    public String getO_status() {
        return o_status;
    }

    public void setO_status(String o_status) {
        this.o_status = o_status;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = Integer.parseInt(o_id);
    }

    public void setO_id(int o_id) {
        O_id = o_id;
    }

    public String getO_detail() {
        return o_detail;
    }

    public void setO_detail(String o_detail) {
        this.o_detail = o_detail;
    }

    public String getO_time() {
        return o_time;
    }

    public void setO_time(String o_time) {
        this.o_time = o_time;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String name) {
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

    @Override
    public String toString() {
        return o_id + "," + detail + " , " + date + " , " + time;
    }

    public String toCSV() {
        return o_id + "," + detail + " , " + date + " , " + time;
    }
}
