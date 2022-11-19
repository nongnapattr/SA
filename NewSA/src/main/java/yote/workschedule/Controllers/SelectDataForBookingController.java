package yote.workschedule.Controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import yote.workschedule.Model.Member;
import yote.workschedule.Model.Select;
import yote.workschedule.Model.SelectList;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.Service.DataSource;
import yote.workschedule.Service.SelectDataSource;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


import static yote.workschedule.Controllers.WelcomeController.MEMBER;


public class SelectDataForBookingController implements Initializable {

    @FXML private Label checkNew;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> selectTime;
    @FXML private RadioButton rb1;
    @FXML private RadioButton rb2;
    @FXML private RadioButton rb3;
    @FXML private RadioButton rb4;
    @FXML private RadioButton rb5;
    @FXML private RadioButton rb6;
    @FXML private RadioButton rb7;
    @FXML private RadioButton rb8;
    @FXML private RadioButton rb9;
    @FXML private RadioButton rb10;

    private Member member = (Member) FXRouter.getData();
//    private Select select = (Select) FXRouter.getData();

    private int amount;
    private Select select1;
    private SelectList selectList;
    private DataSource<SelectList> dataSource;
    public static Select SELECT;
    public ArrayList<Select> select2;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate today = LocalDate.now();
        System.out.println("Current Date= "+today);

        System.out.println("initialize SelectDataForBookingController");
        dataSource = new SelectDataSource("Data", "Select.csv");
        selectList = dataSource.readData();
        selectTime.setItems(FXCollections.observableArrayList("10:00-11:00", "11:00-12:00", "12:00-13:00", "13:00-14:00", "14:00-15:00","15:00-16:00"));
    }

    @FXML
    void checkData(ActionEvent event) throws SQLException, IOException {
        int count = 0;
        String items = "";
        if (rb1.isSelected()) {
            count++;
            items = rb1.getText();
            amount = 200;
        }
        if (rb2.isSelected()) {
            count++;
            items += rb2.getText();
            amount = 300;
        }
        if (rb3.isSelected()) {
            count++;
            items += rb3.getText();
            amount = 250;
        }
        if (rb4.isSelected()) {
            count++;
            items += rb4.getText();
            amount = 200;
        }
        if (rb5.isSelected()) {
            count++;
            items += rb5.getText();
            amount = 300;
        }
        if (rb6.isSelected()) {
            count++;
            items += rb6.getText();
            amount = 400;
        }
        if (rb7.isSelected()) {
            count++;
            items += rb7.getText();
            amount = 500;
        }
        if (rb8.isSelected()) {
            count++;
            items += rb8.getText();
            amount = 500;
        }
        if (rb9.isSelected()) {
            count++;
            items += rb9.getText();
            amount = 200;
        }
        if (rb10.isSelected()) {
            count++;
            items += rb10.getText();
            amount = 400;
        }
        checkNew.setText(items);
        if (count > 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.OK);
            alert.setHeaderText("กรุณาเลือกเพียงอย่างเดียว");
            alert.setContentText("You want to go back ?");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                //do something
                FXRouter.goTo("select_data_for_booking");
            }
        }
    }

    @FXML
    public void handleNextToBookingButton(ActionEvent actionEvent) throws SQLException, IOException {
        String detail = checkNew.getText();
        String time = selectTime.getValue();
        String date = String.valueOf(datePicker.getValue());
        LocalDate today = LocalDate.now();
        System.out.println("Current Date= " + today);

        if (datePicker.getValue() == null || time == null || Objects.equals(detail, "")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "" + "", ButtonType.OK);
            alert.setHeaderText("กรุณาเลือกรายการให้ครบ");
            alert.setContentText("กรุณาเลือกรายการให้ครบ");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                //do something
                FXRouter.goTo("select_data_for_booking");
            }
        } else {
            DataBaseConnection connectionClass = new DataBaseConnection();
            Connection connection = connectionClass.getConnection();
            System.out.println(MEMBER.getId());
            FXRouter.goTo("booking", detail,date,time,amount);
//            String checkTimeDate = "SELECT count(1) FROM orders WHERE O_date = '" + date + "' AND O_time = '" + time + "'";
//            String checkTimeDate = "SELECT count(1) FROM orders WHERE C_id = '" + MEMBER.getId() + "' AND O_date = '" + date + "' AND O_time = '" + time + "'";
//            try {
//                Statement statement = connection.createStatement();
//                ResultSet queryResult = statement.executeQuery(checkTimeDate);
//                while (queryResult.next()) {
//                    if (queryResult.getInt(1) == 1) {
//                        Alert alert = new Alert(Alert.AlertType.WARNING, "" + "", ButtonType.OK);
//                        alert.setHeaderText("ไม่สามารถเลือกวันที่ทำการจองไปแล้วได้อีก");
//                        alert.setContentText("ไม่สามารถเลือกวันที่ทำการจองไปแล้วได้อีก");
//                        FXRouter.goTo("select_data_for_booking");
//                        alert.showAndWait();
//                        System.out.printf("try again");
//                    } else {
//                        String checkTimeDate1 = "SELECT count(1) FROM orders WHERE O_date = '" + date + "' AND O_time = '" + time + "'";
//                        try {
//                            Statement statement1 = connection.createStatement();
//                            ResultSet queryResult1 = statement.executeQuery(checkTimeDate1);
//                            while (queryResult1.next()) {
//                                if (queryResult1.getInt(1) == 3) {
//                                    Alert alert = new Alert(Alert.AlertType.WARNING, "" + "", ButtonType.OK);
//                                    alert.setHeaderText("คิวเต็ม");
//                                    alert.setContentText("คิวเต็ม");
//                                    FXRouter.goTo("select_data_for_booking");
//                                    alert.showAndWait();
//                                    System.out.printf("try again");
//                                } else {
//                                    if (datePicker.getValue().isAfter(today)) {
//                                        String order = "INSERT INTO ORDERS (C_id, O_date, O_time,O_detail)VALUES('" + MEMBER.getId() + "','" + date + "','" + time + "','" + detail + "')";
////                                      Statement statement = connection.createStatement();
//                                        invoice();
//                                        System.out.println(statement);
//                                        statement.executeUpdate(order);
//                                        FXRouter.goTo("booking", amount);
//                                    } else {
//                                        Alert alert = new Alert(Alert.AlertType.WARNING, "" + "", ButtonType.OK);
//                                        alert.setHeaderText("กรุณาเลือกวันที่ใหม่");
//                                        alert.setContentText("กรุณาเลือกวันที่ใหม่");
//                                        FXRouter.goTo("select_data_for_booking");
//                                        alert.showAndWait();
//                                    }
//                                }
//                            }
//                        } catch (SQLException e) {
//                            throw new RuntimeException(e);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (RuntimeException e) {
//                throw new RuntimeException(e);
//            }
        }
    }

    private void invoice() throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String price = "INSERT INTO INVOICE (Amount) VALUES ('" + amount + "') ;";
        Statement statement = connectDB.createStatement();
        statement.executeUpdate(price);
    }

    @FXML
    void handleBackToWelcomeButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("welcome");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า welcome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handleLogoutButton(ActionEvent event) throws SQLException,IOException{
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String orderStatus  = "UPDATE customer SET C_status = 0 WHERE C_username = '" + MEMBER.getUserName() + "'";
        try {
            PreparedStatement statement = connectDB.prepareStatement(orderStatus);
            statement.executeUpdate();
            FXRouter.goTo("welcome");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
