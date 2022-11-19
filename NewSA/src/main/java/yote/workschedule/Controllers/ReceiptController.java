package yote.workschedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static yote.workschedule.Controllers.WelcomeController.MEMBER;

public class ReceiptController {

    @FXML private Label idLabel;
    @FXML private Label nameReceiptLabel;
    @FXML private Label programReceiptLabel;
    @FXML private Label dateReceiptLabel;
    @FXML private Label timeReceiptLabel;
    @FXML private Label priceReceiptLabel;

    @FXML
    private void initialize() throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String ID = "SELECT * FROM CUSTOMER ;";
        Statement statement = connectDB.createStatement();
        statement.executeQuery(ID);
        ResultSet resultSet = statement.executeQuery(ID);
        idLabel.setText(String.valueOf(MEMBER.getId()));
        nameReceiptLabel.setText(String.valueOf(MEMBER.getName()));
        showReceipt();

        System.out.println("initialize BookingDoneController");
    }

    @FXML
    public void handleHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("select_data_for_booking");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า select data for booking ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    private void receipt() throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String price = "SELECT * FROM INVOICE ;";
        Statement statement = connectDB.createStatement();
        statement.executeQuery(price);
        ResultSet resultSet = statement.executeQuery(price);
        while (resultSet.next()){
            priceReceiptLabel.setText(resultSet.getString(2));
        }
    }

    private void showReceipt() throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String show = "SELECT * FROM ORDERS ;";
        Statement statement = connectDB.createStatement();
        statement.executeQuery(show);
        ResultSet resultSet = statement.executeQuery(show);
        while (resultSet.next()) {
            dateReceiptLabel.setText(resultSet.getString(3));
            timeReceiptLabel.setText(resultSet.getString(4));
            programReceiptLabel.setText(resultSet.getString(5));
            receipt();
        }
    }

}
