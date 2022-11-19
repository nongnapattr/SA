package yote.workschedule.Model;

public class Work {

    public Work(String name, String date,String time) {
        this.name = name;
        this.date = String.valueOf(date);
        this.time = time;
        ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String name;

    @Override
    public String toString() {
        return name + " , " + date + " , " + time;
    }

    private String date;
    private final String time;

    public String toCsv() {
        return name + " , " + date + " , " + time;
    }
}
