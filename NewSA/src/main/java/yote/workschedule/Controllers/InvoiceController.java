package yote.workschedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import yote.workschedule.Model.Invoice;
import yote.workschedule.Model.Select;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static yote.workschedule.Controllers.WelcomeController.MEMBER;

public class InvoiceController {

    @FXML private Label programInvoiceLabel;
    @FXML private Label dateInvoiceLabel;
    @FXML private Label timeInvoiceLabel;
    @FXML private Label priceInvoiceLabel;
    @FXML private Label nameInvoiceLabel;
    @FXML private Label invoiceIDLabel;

    int amount = (int) FXRouter.getDataIV();
    String detail = (String) FXRouter.getData();
    String dateBooking = (String) FXRouter.getDataII();
    String timeBooking = (String) FXRouter.getDataIII();

    public Invoice invoices;
    public Select selects;

    @FXML
    private void initialize() throws SQLException {
        this.selects = queryOrder();
        programInvoiceLabel.setText(detail);
        dateInvoiceLabel.setText(dateBooking);
        timeInvoiceLabel.setText(timeBooking);
        priceInvoiceLabel.setText(String.valueOf(amount));
        System.out.println("initialize InvoiceController");
    }

    private Select queryOrder() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connect = connectNow.getConnection();
        String sql = "SELECT C_id, O_date, O_time, O_detail, O_status FROM ORDERS ";
        try {
            Statement statement = connect.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);

            while (queryResult.next()) {
                int O_id = Integer.parseInt(queryResult.getString(1));
                int C_id = Integer.parseInt(queryResult.getString(2));
                String O_date = queryResult.getString(3);
                String O_time = queryResult.getString(4);
                String O_detail = queryResult.getString(5);
                String O_status = queryResult.getString(6);
                Select select = new Select(O_id, C_id, O_date, O_time, O_detail, O_status);
                return select;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



    @FXML
    void handleConfirmInvoiceButton(ActionEvent actionEvent) throws IOException {
        DataBaseConnection connectionClass = new DataBaseConnection();
        Connection connection = connectionClass.getConnection();
        String invoice = "INSERT INTO INVOICE (O_id,Amount)VALUES('" + MEMBER.getId() + "','" + amount + "')";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(invoice);
            FXRouter.goTo("purchase");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า purchase ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }





}
