package yote.workschedule.Model;

import java.util.ArrayList;

public class SelectList {
    private ArrayList<Select> selectList;
    private String date;

    public SelectList(){
        selectList = new ArrayList<>();
    }

    public ArrayList<Select> getSelect(){
        return selectList;
    }

    public void add(Select select){
        selectList.add(select);
    }

    public ArrayList<Select> getSelectList(){
        return selectList;
    }

    public String toCSV() {
        String result = "";
        for (Select data : this.selectList) {
            result += data.toCSV() + "\n";
        }
        return result;
    }

    public void add(String o_id,String name, String date, String time) {
        Select select = new Select(o_id,name,date,time);
        selectList.add(select);
    }

}
