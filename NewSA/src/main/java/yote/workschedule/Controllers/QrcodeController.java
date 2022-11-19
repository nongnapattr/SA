package yote.workschedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;

public class QrcodeController {
    @FXML
    public void upload(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("booking_done");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า booking done ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
