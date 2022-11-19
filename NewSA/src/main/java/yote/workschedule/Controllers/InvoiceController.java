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
import java.util.ArrayList;

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

    private ArrayList<Invoice> invoices;
    public Select selects;
    private Invoice invoice;

    @FXML
    private void initialize() throws SQLException {
        this.selects = queryOrder();
        this.invoices = invoices();

        invoiceIDLabel.setText(String.valueOf(invoice.getI_id()));
        nameInvoiceLabel.setText(invoice.getC_name());
        programInvoiceLabel.setText(detail);
        dateInvoiceLabel.setText(dateBooking);
        timeInvoiceLabel.setText(timeBooking);
        priceInvoiceLabel.setText(String.valueOf(amount));
        System.out.println("O_ID = "+ selects.getO_id());
        System.out.println("initialize InvoiceController");
    }

    private Select queryOrder() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connect = connectNow.getConnection();
        String sql = "SELECT O_id, C_id, O_date, O_time, O_detail, O_status FROM ORDERS ORDER BY O_id DESC LIMIT 1;";
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

    private ArrayList<Invoice> invoices() {
        ArrayList<Invoice> invoiceArrayList = new ArrayList<Invoice>();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        try {
            String invoice = "SELECT * FROM INVOICE INNER JOIN ORDERS ON invoice.O_id = orders.O_id INNER JOIN CUSTOMER ON customer.C_id = orders.C_id ";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(invoice);
            while (resultSet.next()) {
                int O_id = Integer.parseInt(resultSet.getString(1));
                String amount = resultSet.getString(2);
                int I_id = Integer.parseInt(resultSet.getString(3));
                int C_id  = Integer.parseInt(resultSet.getString(5));
                String O_date = resultSet.getString(6);
                String O_time = resultSet.getString(7);
                String O_detail = resultSet.getString(8);
                String O_status = resultSet.getString(9);
                String C_name = resultSet.getString(10);
                String C_username = resultSet.getString(13);

                Invoice invoice1 = new Invoice(O_id,amount,I_id,C_id,O_date,O_time,O_detail,O_status,C_name,C_username);
                invoiceArrayList.add(invoice1);
                this.invoice = invoice1;
                System.out.println(invoice1.getO_id());
                System.out.println(invoice1.getAmount());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoiceArrayList;
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
