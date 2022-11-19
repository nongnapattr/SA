package yote.workschedule.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import yote.workschedule.Model.Work;
import yote.workschedule.Model.WorkList;
import yote.workschedule.Service.DataSource;
import yote.workschedule.Service.WorkDataSource;

import java.time.LocalDate;

public class WorkController {

    private WorkList workList;
    private DataSource<WorkList> dataSource;

    @FXML private TextField timeText;
    @FXML private DatePicker datePicker;
    @FXML private Button submitButton;
    @FXML private TextField workText;
    @FXML private ListView<Work> listView;
    @FXML private Label workLabel;
    @FXML private Label dateLabel;
    @FXML private Label timeLabel;

    @FXML private TextField name;


    public  void initialize(){
        readData();
        DataSource<WorkList> dataSource = new WorkDataSource();
        this.workList = dataSource.readData();
        showListView();
    }

    private void showListView() {
        ObservableList<Work> items = FXCollections.observableArrayList();
        for (Work work: workList.getWorkList()) {
            items.add(work);
        }
        listView.setItems(items);
    }

    private void readData() {
        dataSource = new WorkDataSource();
        workList = dataSource.readData();
    }

    public void submit(){
//        listView.getItems().add(name.getText());
        String name = workText.getText();
        if (name.equals("")){
            workLabel.setText("ต้องการข้อมูลชื่อสื่งที่จะทำ");
            return;
        }
        LocalDate date = datePicker.getValue();
        if (date == null ){
            dateLabel.setText("ต้องการวันที่");
            return;
        }
        String time = timeText.getText();
        if (time.equals("")){
            workLabel.setText("ต้องการเวลา");
            return;
        }
        workList.add(name, String.valueOf(date),time);
        dataSource.writeData((workList));


    }

}