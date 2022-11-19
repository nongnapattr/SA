module yote.workschedule {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.compiler;


    opens yote.workschedule to javafx.fxml;
    exports yote.workschedule;

    exports yote.workschedule.Controllers;
    opens yote.workschedule.Controllers to javafx.fxml;
    exports yote.workschedule.Service;
    opens yote.workschedule.Service to javafx.fxml;
}