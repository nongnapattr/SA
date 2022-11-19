package yote.workschedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import yote.workschedule.Model.MemberList;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.Service.DataSource;
//import yote.workschedule.Service.MemberDataSource;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class RegisterUserController {

    public TextField confirmPasswordRegisterTextField;
    private MemberList memberList;
    private DataSource<MemberList> dataSource;
    private Alert alert;
    private String roleDefault = "Customer";
    private String role = roleDefault;

    @FXML private TextField usernameRegisterTextField;
    @FXML private PasswordField passwordRegisterTextField;
    @FXML private TextField nameTextField;

    @FXML private PasswordField confirmPasswordTextField;
    @FXML private TextField phoneRegisterTextField;
    @FXML private ComboBox comboBox;

    @FXML private Label userNameLabel;
    @FXML private Label nameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label conPassLabel;
    @FXML private Label phoneLabel;



//    @FXML
//    public void selectRole(ActionEvent event) {
//        if (comboBox.getValue().equals("Admin")) {
//            role = "Admin";
//        }
//        if (comboBox.getValue().equals("Customer")) {
//            role = "Customer";
//        }
//    }

    @FXML
    public void handleUseConfirmRegisterButton(ActionEvent actionEvent) throws SQLException, IOException {
        String name = nameTextField.getText();
        if (name.equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.OK);
            alert.setHeaderText("กรอกข้อมูลให้ครบถ้วน");
            alert.setContentText("กรอกข้อมูลให้ครบถ้วน");
            alert.show();
            // nameLabel.setText("ต้องการชื่อ");
            return;
        }
        String userName = usernameRegisterTextField.getText();
        if (userName.equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.OK);
            alert.setHeaderText("กรอกข้อมูลให้ครบถ้วน");
            alert.setContentText("กรอกข้อมูลให้ครบถ้วน");
            alert.show();
            //userNameLabel.setText("ต้องการ Username");
            return;
        }
        String password = passwordRegisterTextField.getText();
        if (password.equals("") ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.OK);
            alert.setHeaderText("กรอกข้อมูลให้ครบถ้วน");
            alert.setContentText("กรอกข้อมูลให้ครบถ้วน");
            alert.show();
            //passwordLabel.setText("ต้องการ Password");
            return;
        }
        String confirmPassword = confirmPasswordTextField.getText();
        if (!Objects.equals(confirmPassword, password)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.OK);
            alert.setHeaderText("รหัสผ่านไม่ตรงกับรหัสข้างบน");
            alert.setContentText("รหัสผ่านไม่ตรงกับรหัสข้างบน");
            alert.showAndWait();
            return;
        }
        String phoneNumber = phoneRegisterTextField.getText();
        if (phoneNumber.equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back " + " ?", ButtonType.OK);
            alert.setHeaderText("กรอกข้อมูลให้ครบถ้วน");
            alert.setContentText("กรอกข้อมูลให้ครบถ้วน");
            alert.show();
            //phoneLabel.setText("ต้องการเบอร์มือถือ");
            return;
        }

        if (validateRegister()){
            DataBaseConnection connectionClass = new DataBaseConnection();
            Connection connection = connectionClass.getConnection();
            String register = "INSERT INTO CUSTOMER (C_name,C_tel,C_username,C_password,C_status,Role)VALUES('" + name + "','" + phoneNumber + "','" + userName + "','" + password + "','" + 0 + "','" + "Customer" +"')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(register);
        }
    }

    public boolean validateRegister() {
        String username = usernameRegisterTextField.getText();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM database.customer WHERE C_username = '" + username + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Go back " + " ?", ButtonType.OK);
                    alert.setHeaderText("ลงทะเบียนสำเร็จ");
                    alert.setContentText("ลงทะเบียนสำเร็จ");
                    alert.showAndWait();
                    FXRouter.goTo("welcome");
                    return true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Username ซ้ำ ", ButtonType.OK);
                    alert.setHeaderText("Username ถูกใช้แล้ว ");
                    alert.setContentText("Username ถูกใช้แล้ว ");

                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        FXRouter.goTo("register_user");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }

    @FXML
    void handleBackToWelcomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("welcome");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า welcome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
