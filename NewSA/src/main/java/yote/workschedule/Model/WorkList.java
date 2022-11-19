package yote.workschedule.Model;

import java.util.ArrayList;

public class WorkList  {
    private ArrayList<Work> workList;

    public WorkList(){
        workList = new ArrayList<>();
    }

    public ArrayList<Work> getWorks(){
        return workList;
    }

    public void add(Work work){
        workList.add(work);
    }

    public ArrayList<Work> getWorkList(){
        return workList;
    }

    public String toCsv() {
        String result = "";
        for (Work product: workList) {
            result += product.toCsv() + "\n";
        }
        return result;
    }

    public void add(String name, String date, String time) {
        Work work = new Work(name,date,time);
        workList.add(work);
    }
}
