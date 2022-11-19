package yote.workschedule.Controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.events.MouseEvent;
import yote.workschedule.Model.All;
import yote.workschedule.Model.Invoice;
import yote.workschedule.Model.Member;
import yote.workschedule.Model.Select;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static yote.workschedule.Controllers.WelcomeController.MEMBER;

public class ManagerSeeDetailController {
    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    @FXML private Label phoneLabel;
    @FXML private Label dateLabel;
    @FXML private Label timeLabel;
    @FXML private Label detailLabel;

    @FXML private Label programShow;
    @FXML private Label dateShow;
    @FXML private Label timeShow;
    @FXML private Label AmountShow;
    @FXML private Label OrderIDShow;

    @FXML private TableColumn <Invoice,String> column1;

    @FXML private TableView<Invoice> tableView1;

    private ArrayList<Invoice> invoices;
    private ArrayList<Select> selects;
    private ArrayList<Member> members;
    private Select select;
    private Invoice invoice;
    private MouseEvent event;
    private ArrayList<All> alls;

    public ManagerSeeDetailController() {
    }

    @FXML
    private void initialize() throws SQLException {
        this.invoices = invoices();
        this.selects = selects();
        show();
//      displaySelected(event);
//        handleSelectListView();
//        DataBaseConnection connectNow = new DataBaseConnection();
//        Connection connectDB = connectNow.getConnection();
//        String price = "SELECT * FROM INVOICE ;";
//        Statement statement = connectDB.createStatement();
//        statement.executeQuery(price);
//        ResultSet resultSet = statement.executeQuery(price);
        System.out.println("initialize ManagerSeeDetailController");
    }

//    private void invoice() throws SQLException {
//        DataBaseConnection connectNow = new DataBaseConnection();
//        Connection connectDB = connectNow.getConnection();
//        String price = "SELECT * FROM INVOICE ;";
//        Statement statement = connectDB.createStatement();
//        statement.executeQuery(price);
//        ResultSet resultSet = statement.executeQuery(price);
//    }

    private ArrayList<Member> members() {
        ArrayList<Member> memberArrayList = new ArrayList<Member>();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String customer = "SELECT * FROM CUSTOMER ;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(customer);
            while (resultSet.next()) {
                String C_name = resultSet.getString(1);
                int C_id  = Integer.parseInt(resultSet.getString(2));
                String C_tel = resultSet.getString(3);
                String C_username = resultSet.getString(4);
                String C_password = resultSet.getString(5);
                String C_status = resultSet.getString(6);
                String Role = resultSet.getString(7);
                Member member = new Member(C_name,C_id,C_tel,C_username,C_password,C_status,Role);
                memberArrayList.add(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return memberArrayList;
    }

    private ArrayList<Select> selects() {
        ArrayList<Select> selectArrayList = new ArrayList<Select>();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String order = "SELECT * FROM ORDERS ;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(order);
            while (resultSet.next()) {
                int O_id = Integer.parseInt(resultSet.getString(1));
                int C_id  = Integer.parseInt(resultSet.getString(2));
                String O_date = resultSet.getString(3);
                String O_time = resultSet.getString(4);
                String O_detail = resultSet.getString(5);
                String O_status = resultSet.getString(6);
                Select select = new Select(O_id,C_id,O_date,O_time,O_detail,O_status);
                selectArrayList.add(select);
                System.out.println(select.getO_id());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectArrayList;
    }

    private ArrayList<Invoice> invoices() {
        ArrayList<Invoice> invoiceArrayList = new ArrayList<Invoice>();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        try {
            String invoice = "SELECT * FROM INVOICE INNER JOIN ORDERS ON invoice.O_id = orders.O_id INNER JOIN CUSTOMER ON customer.C_id = orders.C_id ORDER BY orders.O_id DESC";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(invoice);
            while (resultSet.next()) {
                int O_id = Integer.parseInt(resultSet.getString(1));
                String amount = resultSet.getString(2);
                int O_id1 = Integer.parseInt(resultSet.getString(3));
                int C_id  = Integer.parseInt(resultSet.getString(4));
                String O_date = resultSet.getString(5);
                String O_time = resultSet.getString(6);
                String O_detail = resultSet.getString(7);
                String O_status = resultSet.getString(8);
                String C_name = resultSet.getString(9);
                int C_id1  = Integer.parseInt(resultSet.getString(10));
                String C_tel = resultSet.getString(11);
                String C_username = resultSet.getString(12);
                String C_password = resultSet.getString(13);
                String C_status = resultSet.getString(14);
                String Role = resultSet.getString(15);

                Invoice invoice1 = new Invoice(O_id,amount,O_id1,C_id,O_date,O_time,O_detail,O_status,C_name,C_id1,C_tel,C_username,C_password,C_status,Role);
                invoiceArrayList.add(invoice1);
                System.out.println(invoice1.getO_id());
                System.out.println(invoice1.getAmount());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoiceArrayList;
    }

    private void show(){

        tableView1.getColumns().clear();

        ObservableList<Invoice> data = FXCollections.observableArrayList();
        for (Invoice invoice : invoices){
            data.add(invoice);
        }

        ObservableList<Select> data1 = FXCollections.observableArrayList();
        for (Select select : selects){
            data1.add(select);
        }

        TableColumn<Invoice, String> O_idColumn = new TableColumn<>("Order");
        O_idColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(String.valueOf(invoice.getO_id()));
        });
        tableView1.getColumns().add(O_idColumn);

        TableColumn<Invoice, String> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(invoice.getAmount());
        });
        tableView1.getColumns().add(amountColumn);
        tableView1.setItems(data);

        TableColumn<Invoice, String> C_idColumn = new TableColumn<>("C_ID");
        C_idColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(String.valueOf(invoice.getC_id()));
        });
        tableView1.getColumns().add(C_idColumn);

        TableColumn<Invoice, String> C_nameColumn = new TableColumn<>("Name");
        C_nameColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(String.valueOf(invoice.getName()));
        });
        tableView1.getColumns().add(C_nameColumn);

        TableColumn<Invoice, String> O_dateColumn = new TableColumn<>("Order Date");
        O_dateColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(invoice.getO_date());
        });
        tableView1.getColumns().add(O_dateColumn);

        TableColumn<Invoice, String> O_timeColumn = new TableColumn<>("Order Time");
        O_timeColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(invoice.getO_time());
        });
        tableView1.getColumns().add(O_timeColumn);

        TableColumn<Invoice, String> O_detailColumn = new TableColumn<>("Order Detail");
        O_detailColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(invoice.getO_detail());
        });
        tableView1.getColumns().add(O_detailColumn);

        TableColumn<Invoice, String> O_statusColumn = new TableColumn<>("Status");
        O_statusColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(invoice.getO_status());
        });
        tableView1.getColumns().add(O_statusColumn);
    }

    private void showSelectedUser(Member member){
        idLabel.setText(String.valueOf(member.getId()));
        nameLabel.setText(member.getName());
        phoneLabel.setText(member.getPhoneNumber());
        System.out.println("Selected Value");

    }
    private void showSelectedOrder(Select select){
        dateLabel.setText(String.valueOf(select.getDate()));
        timeLabel.setText(select.getDate());
        detailLabel.setText(select.getDetail());
        System.out.println(select.getDate());
        System.out.println(select.getTime());
    }

    private void checkByPid(){
        for (Select select : selects){
            for (Member member : members()){
                if (select.getC_id() == member.getId())
                    idLabel.setText(String.valueOf(member.getId()));
                nameLabel.setText(member.getName());
                phoneLabel.setText(member.getPhoneNumber());
                System.out.println(member.getId());
                System.out.println(member.getName());
                System.out.println(member.getPhoneNumber());
            }
        }
    }

    private void checkByOid(){
        for (Invoice invoice : invoices){
            for (Select select : selects){
                if (invoice.getO_id() == select.getO_id()){
                    dateLabel.setText(String.valueOf(select.getO_date()));
                    timeLabel.setText(select.getO_time());
                    detailLabel.setText(select.getO_detail());
                    System.out.println(select.getO_date());
                    System.out.println(select.getO_time());
                    System.out.println(select.getO_detail());
                }
            }
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Go back ?");
        alert.setContentText("You want to go back ?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //do something
            FXRouter.goTo("welcome");
        }else {
            try {
                FXRouter.goTo("manager_see_detail");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("ไปที่หน้า manager_see_detail ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML
    void handleUseReportButton(ActionEvent event) {
        try {
            FXRouter.goTo("report");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

//    private void displaySelected(MouseEvent event){
//        Invoice invoice = tableView.getSelectionModel().getSelectedItem();
//        if (invoice == null){
//            System.out.println("Nothing");
//        } else {
//            String c_id = String.valueOf(invoice.getO_id());
//            System.out.println(c_id);
//        }
//    }


//    private void handleSelectListView(){
//        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
//                //Check whether item is selected and set value of selected item to Label
//                if(tableView.getSelectionModel().getSelectedItem() != null)
//                {
//                    TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();
//                    ObservableList selectedCells = selectionModel.getSelectedCells();
//                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//                    Object val = tablePosition.getTableColumn().getCellData(newValue);
//                    showSelectedUser((Member) newValue);
////                    checkByOid();
////                    checkByPid();
//                    System.out.println(val);
//                }
//            }
//        });
//    }


//    private void handleSelectTableView(){
//        tableView.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<Select>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Select> observableValue, Select oldBannedUser, Select newBannedUser) {
//                        showSelectedOrder(newBannedUser);
//                    }
//                }
//        );
//
//    }

//    private void handleSelectTableView(){
//        tableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSelect, newSelect) -> {
//            if (newSelect != null) {
//                showSelectedOrder(newSelect);
//            }
//        });
//    }
}
