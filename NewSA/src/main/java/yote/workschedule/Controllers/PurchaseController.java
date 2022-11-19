package yote.workschedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import yote.workschedule.Model.Invoice;
import yote.workschedule.Model.Select;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.sql.*;

public class PurchaseController {
    @FXML private Label programPurchaseLabel;
    @FXML private Label datePurchaseLabel;
    @FXML private Label timePurchaseLabel;
    @FXML private Label pricePurchaseLabel;

    private Select select = (Select) FXRouter.getData();

    @FXML
    private void initialize() throws SQLException {
//        this.select = queryOrder();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String show = "SELECT * FROM ORDERS ;";
        Statement statement = connectDB.createStatement();
        statement.executeQuery(show);
        ResultSet resultSet = statement.executeQuery(show);
        while (resultSet.next()) {
            datePurchaseLabel.setText(resultSet.getString(3));
            timePurchaseLabel.setText(resultSet.getString(4));
            programPurchaseLabel.setText(resultSet.getString(5));
            invoice();
        }
        System.out.println("initialize PurchaseController");
    }

    private Select queryOrder() throws SQLException{
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT * FROM ORDERS ;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int O_id = Integer.parseInt(resultSet.getString(1));
                int C_id  = Integer.parseInt(resultSet.getString(2));
                String O_date = resultSet.getString(3);
                String O_time = resultSet.getString(4);
                String O_detail = resultSet.getString(5);
                String O_status = resultSet.getString(5);

                Select select = new Select(O_id,C_id,O_date,O_time,O_detail,O_status);
                return select;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null ;
    }

    private void invoice() throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String price = "SELECT * FROM INVOICE ;";
        Statement statement = connectDB.createStatement();
        statement.executeQuery(price);
        ResultSet resultSet = statement.executeQuery(price);
        while (resultSet.next()){
            pricePurchaseLabel.setText(resultSet.getString(2));
        }
    }

    private void changeOrderPurchaseStatus() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String orderStatus = "UPDATE ORDERS SET O_status = 1 WHERE O_id = '" + select.getO_id() + "'";
        try {
            PreparedStatement statement = connectDB.prepareStatement(orderStatus);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void handleQrcodePurchaseButton(ActionEvent actionEvent) {
        changeOrderPurchaseStatus();
        try {
            FXRouter.goTo("booking_done");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า qr ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
