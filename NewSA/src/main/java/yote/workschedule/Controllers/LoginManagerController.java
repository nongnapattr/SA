package yote.workschedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import yote.workschedule.Service.DataBaseConnection;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;
import java.sql.*;

import static yote.workschedule.Controllers.WelcomeController.MEMBER;

public class LoginManagerController {

    @FXML private Label loginLabel;
    @FXML private TextField usernameLoginTextField;
    @FXML private TextField passwordLoginTextField;

    @FXML
    private void initialize() {
//        loginLabel.setText(MEMBER.getRole());
        System.out.println("initialize LoginManagerController");
    }

    @FXML
    void handleBackToLoginButton(ActionEvent event) {
        try {
            FXRouter.goTo("welcome");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า welcome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleManagerLoginButton(ActionEvent actionEvent) {
        String username = usernameLoginTextField.getText();
        String password = passwordLoginTextField.getText();
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM admin WHERE A_username = '" + username + "' AND A_password = '" + password + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    // loginLabel.setText("Congrats");

//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "" + "", ButtonType.OK);
//                    alert.setHeaderText("Login Manager");
//                    alert.setContentText("Login Manager");
//                    alert.showAndWait();
                    FXRouter.goTo("manager_see_detail");
                } else {
                    //loginLabel.setText("Invalid login. please try again.");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "" + "", ButtonType.OK);
                    alert.setHeaderText("Invalid login. please try again.");
                    alert.setContentText("Invalid login. please try again.");
                    alert.showAndWait();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า manager see detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
