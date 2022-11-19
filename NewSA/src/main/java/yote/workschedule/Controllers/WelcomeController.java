package yote.workschedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import yote.workschedule.Model.Member;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;


public class WelcomeController {

    @FXML private TextField usernameLoginTextField;
    @FXML private PasswordField passwordLoginTextField;
    @FXML private ImageView adminImageView;

    public ArrayList<Member> memberList;
    public static Member MEMBER;

    @FXML
    private void initialize() {
        this.memberList = members();
        System.out.println("initialize WelcomeController");
    }

    @FXML
    public void handleRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("register_user");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleLoginButton(ActionEvent actionEvent) {
        if (usernameLoginTextField.getText().equals("") && passwordLoginTextField.getText().equals("")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Go back " + " ?", ButtonType.OK);
            alert1.setHeaderText("กรุณาใส่ Username และ Password");
            alert1.setContentText("กรุณาใส่ Username และ Password");
            alert1.showAndWait();
            return;
        } else {
//            try {
            validateLogin();
            sqlmember(usernameLoginTextField.getText());
//                FXRouter.goTo("select_data_for_booking");
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.err.println("ไปที่หน้า select data for booking ไม่ได้");
//                System.err.println("ให้ตรวจสอบการกำหนด route");
//            }
        }
    }

    @FXML
    public void handleManager(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("login_manager");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า login_manager ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    private void sqlmember(String username) {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connect = connectNow.getConnection();

        String sql = "SELECT * FROM customer WHERE C_username = '" + username + "';";
        try {
            Statement statement = connect.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);

            while (queryResult.next()) {
                String C_name = queryResult.getString(1);
                int C_id = Integer.parseInt(queryResult.getString(2));
                String C_tel = queryResult.getString(3);
                String C_username = queryResult.getString(4);
                String C_password = queryResult.getString(5);
                String C_Status = queryResult.getString(6);
                String Role = queryResult.getString(7);


                this.MEMBER = new Member(C_name, C_id, C_tel, C_username, C_password, C_Status, Role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void useRole() throws IOException{
        for(Member member : memberList){
            String role = member.getRole();
            if(role.equals("Admin") && (member.getUserName().equals(usernameLoginTextField.getText()))){
                FXRouter.goTo("manager_see_detail", MEMBER);
            }
            if(role.equals("Customer") && (member.getUserName().equals(usernameLoginTextField.getText()))){
                FXRouter.goTo("select_data_for_booking", MEMBER);
            }
        }
    }

    private ArrayList<Member> members() {
        ArrayList<Member> memberArrayList = new ArrayList<Member>();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String role = "SELECT * FROM CUSTOMER;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(role);
            while (queryResult.next()) {

                String C_name = queryResult.getString(1);
                int C_id = Integer.parseInt(queryResult.getString(2));
                String C_tel = queryResult.getString(3);
                String C_username = queryResult.getString(4);
                String C_password = queryResult.getString(5);
                String C_Status = queryResult.getString(6);
                String Role = queryResult.getString(7);

                Member member = new Member(C_name, C_id, C_tel, C_username, C_password, C_Status, Role);
                memberArrayList.add(member);
                System.out.println(member.getRole());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return memberArrayList;
    }


    public void validateLogin() {
        String username = usernameLoginTextField.getText();
        String password = passwordLoginTextField.getText();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM database.customer WHERE C_username = '" + username + "' AND C_password = '" + password + "'";
//        String userStatus = "UPDATE CUSTOMER SET c_status = 1 WHERE C_username = '" + username + "';";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
//            PreparedStatement statement1 = connectDB.prepareStatement(userStatus);
//            statement1.executeUpdate();

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "" + "", ButtonType.OK);
                    alert.setHeaderText("Login !");
                    alert.setContentText("Login !");
                    //alert.showAndWait();
                    changeLoginStatus();
                    useRole();
//                    FXRouter.goTo("select_data_for_booking", MEMBER);
                } else {
                    if (usernameLoginTextField.getText().equals("") && passwordLoginTextField.getText().equals("")) {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR, "Go back " + " ?", ButtonType.OK);
                        alert1.setHeaderText("กรุณาใส่ Username และ Password");
                        alert1.setContentText("กรุณาใส่ Username และ Password");
                        alert1.showAndWait();
                        return;
                    } else {
                        if (usernameLoginTextField.getText().equals("")) {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR, "Go back " + " ?", ButtonType.OK);
                            alert2.setHeaderText("กรุณาใส่ Username และ Password");
                            alert2.setContentText("กรุณาใส่ Username และ Password");
                            alert2.showAndWait();
                            return;

                        } else if (passwordLoginTextField.getText().equals("")) {
                            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.OK);
                            alert2.setHeaderText("กรุณาใส่ Username และ Password");
                            alert2.setContentText("กรุณาใส่ Username และ Password");
                            alert2.showAndWait();
                            return;

                        } else {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR, "Go back " + " ?", ButtonType.OK);
                            alert2.setHeaderText("Username หรือ Password ไม่ถูกต้อง");
                            alert2.setContentText("Username หรือ Password ไม่ถูกต้อง");
                            alert2.showAndWait();
                            return;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void changeLoginStatus() {
        String username = usernameLoginTextField.getText();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String orderStatus = "UPDATE customer SET C_status = 1 WHERE C_username = '" + username + "';";
        try {
            PreparedStatement statement = connectDB.prepareStatement(orderStatus);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
