package yote.workschedule.Controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.events.MouseEvent;
import yote.workschedule.Model.Invoice;
import yote.workschedule.Model.Member;
import yote.workschedule.Model.Select;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.Service.DataSource;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static yote.workschedule.Controllers.WelcomeController.MEMBER;

public class ReportController {
    @FXML private TableView<Invoice> tableView2;
    @FXML private TextField SearchTextField;

    private ArrayList<Invoice> invoices;
    private ArrayList<Select> selects;
    private ArrayList<Member> members;
    private Select select;
    private Invoice invoice;
    private MouseEvent event;

    public ReportController() {
    }

    @FXML
    private void initialize() throws SQLException {
        this.invoices = invoices();
        this.selects = selects();
        showReport();
        //showTryReport();

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String price = "SELECT * FROM INVOICE ;";
        Statement statement = connectDB.createStatement();
        statement.executeQuery(price);
        ResultSet resultSet = statement.executeQuery(price);
        for(Invoice invoice : invoices){
            System.out.println("invoice status = " + invoice.getO_status());
        }

        System.out.println("initialize ManagerSeeDetailController");
    }

    private ArrayList<Select> selects() {
        ArrayList<Select> selectArrayList = new ArrayList<Select>();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String order = "SELECT * FROM ORDERS";
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
            String invoicess = "SELECT * FROM `invoice`INNER JOIN `orders` ON invoice.O_id = orders.O_id INNER JOIN `customer` ON customer.C_id = orders.C_id WHERE orders.O_status=1;";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(invoicess);
            while (resultSet.next()) {
                int O_id = Integer.parseInt(resultSet.getString(1));
                String amount = resultSet.getString(2);
                int O_id1 = Integer.parseInt(resultSet.getString(3));
                int C_id  = Integer.parseInt(resultSet.getString(4));
                String O_date = resultSet.getString(5);
                String O_time = resultSet.getString(6);
                String O_detail = resultSet.getString(7);
                String O_status = resultSet.getString(8);
                String Name = resultSet.getString(9);
                int C_id1 = Integer.parseInt(resultSet.getString(10));
                String PhoneNumber = resultSet.getString(11);
                String UserName = resultSet.getString(12);
                String password = resultSet.getString(13);

                Invoice invoice2 = new Invoice(O_id,amount,O_id1,C_id,O_date,O_time,O_detail,O_status,Name,C_id1,PhoneNumber,UserName,password);
                invoiceArrayList.add(invoice2);
                System.out.println(invoice2.getO_id());
                System.out.println(invoice2.getAmount());
                System.out.println("2 = " + invoice2.getO_status());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoiceArrayList;
    }

    private void showReport(){
        tableView2.getColumns().clear();

        ObservableList<Invoice> data3 = FXCollections.observableArrayList();
        for (Invoice invoice : invoices){
            System.out.println("invoice status = " + invoice.getO_status());
            System.out.println("invoice status 2 = " + invoice.getStatus());
            data3.add(invoice);
        }

        ObservableList<Select> data4 = FXCollections.observableArrayList();
        for (Select select : selects){
            data4.add(select);
        }

        for (Invoice invoice : invoices) {
            for (Select select : selects) {
                if (select.getO_id() == invoice.getO_id()) {
                    tableView2.getItems().addAll(invoice);
                }
            }
        }

//        TableColumn<Invoice, String> O_idReportColumn = new TableColumn<>("O_ID");
//        O_idReportColumn.setCellValueFactory(cellData -> {
//            Invoice invoice = cellData.getValue();
//            return new ReadOnlyStringWrapper(String.valueOf(invoice.getO_id1()));
//        });
//        tableView2.getColumns().add(O_idReportColumn);
//
//        TableColumn<Invoice, String> C_idReportColumn = new TableColumn<>("C_ID");
//        C_idReportColumn.setCellValueFactory(cellData -> {
//            Invoice invoice = cellData.getValue();
//            return new ReadOnlyStringWrapper(String.valueOf(invoice.getC_id()));
//        });
//        tableView2.getColumns().add(C_idReportColumn);

        TableColumn<Invoice, String> C_usernameReportColumn = new TableColumn<>("Username");
        C_usernameReportColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(invoice.getName());
        });
        tableView2.getColumns().add(C_usernameReportColumn);

        TableColumn<Invoice, String> C_telReportColumn = new TableColumn<>("Phone");
        C_telReportColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(invoice.getPhoneNumber());
        });
        tableView2.getColumns().add(C_telReportColumn);

        TableColumn<Invoice, String> O_dateReportColumn = new TableColumn<>("Date");
        O_dateReportColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(invoice.getO_date());
        });
        tableView2.getColumns().add(O_dateReportColumn);

        TableColumn<Invoice, String> O_timeReportColumn = new TableColumn<>("Time");
        O_timeReportColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(invoice.getO_time());
        });
        tableView2.getColumns().add(O_timeReportColumn);


        TableColumn<Invoice, String> O_detailReportColumn = new TableColumn<>("Detail");
        O_detailReportColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(String.valueOf(invoice.getO_detail()));
        });
        tableView2.getColumns().add(O_detailReportColumn);

        TableColumn<Invoice, String> O_statusReportColumn = new TableColumn<>("Status");
        O_statusReportColumn.setCellValueFactory(cellData -> {
            Invoice invoice = cellData.getValue();
            return new ReadOnlyStringWrapper(String.valueOf(invoice.getO_status()));
        });
        tableView2.getColumns().add(O_statusReportColumn);


        FilteredList<Invoice> filteredData = new FilteredList<>(data3, b -> true);
        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoice1 -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

//                if (String.valueOf(invoice1.getO_id()).toLowerCase().indexOf(searchKeyword) > -1) {
//                    return true;
//                } else if (String.valueOf(invoice1.getC_id()).toLowerCase().indexOf(searchKeyword) > -1) {
//                    return true;
                if (invoice1.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (invoice1.getPhoneNumber().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (invoice1.getO_date().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (invoice1.getO_time().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (invoice1.getO_detail().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (invoice1.getO_status().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<Invoice> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView2.comparatorProperty());
        tableView2.setItems(sortedData);
    }

    @FXML
    void handleBackButton(ActionEvent actionEvent) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Go back ?");
        alert.setContentText("You want to go back ?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //do something
            FXRouter.goTo("manager_see_detail");
        } else {
            try {
                FXRouter.goTo("report");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("ไปที่หน้า report ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }

    }

}
