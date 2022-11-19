package yote.workschedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import yote.workschedule.Model.Invoice;
import yote.workschedule.Model.MemberList;
import yote.workschedule.Model.Select;
import yote.workschedule.Model.SelectList;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.Service.DataSource;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import static java.lang.String.format;
import static yote.workschedule.Controllers.SelectDataForBookingController.SELECT;
import static yote.workschedule.Controllers.WelcomeController.MEMBER;

public class BookingController {

    @FXML private Label programBookingLabel;
    @FXML private Label dateBookingLabel;
    @FXML private Label timeBookingLabel;
    @FXML private Label priceBookingLabel;

    private String date;
    private String time;
    private String price;
    private String program;
    private Object function;
    int amount = (int) FXRouter.getDataIV();
    String detail = (String) FXRouter.getData();
    String dateBooking = (String) FXRouter.getDataII();
    String timeBooking = (String) FXRouter.getDataIII();
    private SelectList selectList;
    private Label checkItems;
    private DataSource<SelectList> dataSource;
    private SelectList SelectedList;
    public Invoice invoices;
    public Select selects;
//    private GetList getList = (GetList) FXRouter.getData();

    @FXML
    private void initialize() throws SQLException {
        System.out.println("123");
        this.selects = selects();
        this.invoices = invoices();
//        System.out.println(selectList.getSelectList());
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
//        String show = "SELECT * FROM ORDERS WHERE O_date = '" + date + "' AND O_time = '" + time + "' AND O_detail = '" + program + "'";
        String show = "SELECT * FROM ORDERS ;";
//        String price = "SELECT * FROM INVOICE ;";
        Statement statement = connectDB.createStatement();
        statement.executeQuery(show);
//        statement.executeQuery(price);
        ResultSet resultSet = statement.executeQuery(show);
//        while (resultSet.next()) {
//            dateBookingLabel.setText(resultSet.getString(3));
//            timeBookingLabel.setText(resultSet.getString(4));
//            programBookingLabel.setText(resultSet.getString(5));
//            invoice();
//        }
        dateBookingLabel.setText(dateBooking);
        timeBookingLabel.setText(timeBooking);
        programBookingLabel.setText(detail);
        priceBookingLabel.setText(String.valueOf(amount));
//        while (resultSet1.next()){
//            priceBookingLabel.setText(resultSet1.getString(2));
//        }
    }

    private void invoice() throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String price = "SELECT * FROM INVOICE ;";
        Statement statement = connectDB.createStatement();
        statement.executeQuery(price);
        ResultSet resultSet = statement.executeQuery(price);
        System.out.println(invoices.getAmount());
        priceBookingLabel.setText(invoices.getAmount());
        while (resultSet.next()){
            priceBookingLabel.setText(resultSet.getString(2));
        }
    }

    private Invoice invoices() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connect = connectNow.getConnection();
        String sql = "SELECT O_id, Amount FROM INVOICE ORDER BY O_id DESC";
        try {
            Statement statement = connect.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);
            while (queryResult.next()) {
                String O_id = queryResult.getString(1);
                String Amount = queryResult.getString(2);
                Invoice invoice = new Invoice(O_id, Amount);
                return invoice;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Select selects() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connect = connectNow.getConnection();
        String sql = "SELECT O_id, O_date, O_time, O_detail FROM ORDERS ORDER BY O_id DESC";
        try {
            Statement statement = connect.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);

            while (queryResult.next()) {
                String O_id = queryResult.getString(1);
                String O_date = queryResult.getString(2);
                String O_time = queryResult.getString(3);
                String O_detail = queryResult.getString(4);
                Select select = new Select(O_id, O_date, O_time, O_detail);
                return select;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @FXML
    public void handleConfirmBookingButton(ActionEvent actionEvent) throws IOException {
        DataBaseConnection connectionClass = new DataBaseConnection();
        Connection connection = connectionClass.getConnection();
        String order = "INSERT INTO ORDERS (C_id, O_date, O_time,O_detail,O_status)VALUES('" + MEMBER.getId() + "','" + dateBooking + "','" + timeBooking + "','" + detail + "','" + 0 + "')";
//        String invoice = "INSERT INTO ORDERS (O_id, Amount)VALUES('" + MEMBER.getId() + "','" + amount + "')";
        try {
            Statement statement = connection.createStatement();
//            Statement statement2 = connection.createStatement();
            System.out.println(statement);
            statement.executeUpdate(order);
//            statement2.executeUpdate(invoice);
            FXRouter.goTo("invoice",detail,dateBooking,timeBooking,amount);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า purchase ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
        //changeBookingStatus();
//        try {
//            FXRouter.goTo("purchase",selects);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("ไปที่หน้า purchase ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//        }
    }

    @FXML
    public void handleBackBookingButton(ActionEvent actionEvent) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("ต้องการยกเลิกรายการหรือไม่");
        alert.setContentText("ต้องการยกเลิกรายการหรือไม่");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            DataBaseConnection connectNow = new DataBaseConnection();
            Connection connectDB = connectNow.getConnection();
            String order = "DELETE FROM ORDERS WHERE O_id = '" + selects.getO_id() + "';";
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(order);
            String re = "DELETE FROM INVOICE WHERE O_id = '" + invoices.getO_id() + "';";
            Statement statement1 = connectDB.createStatement();
            statement1.executeUpdate(re);
            FXRouter.goTo("select_data_for_booking", SELECT);
        } else {
            try {
                FXRouter.goTo("booking");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("ไปที่หน้า booking ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

//    private void changeBookingStatus() {
//
//        DataBaseConnection connectNow = new DataBaseConnection();
//        Connection connectDB = connectNow.getConnection();
//
//        String orderStatus = "UPDATE ORDERS SET O_status = 1 WHERE O_id = orders.O_id;";
//
//        try {
//            PreparedStatement statement = connectDB.prepareStatement(orderStatus);
//            statement.executeUpdate();
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }
}

