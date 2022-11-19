package yote.workschedule;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import yote.workschedule.fxrouter.FXRouter;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }

    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "SAProject", 800, 600);
        configRoute();
        FXRouter.goTo("welcome");
    }

    private void configRoute() {
        String packageStr = "yote/workschedule/";
        FXRouter.when("work", packageStr+"work.fxml");
        FXRouter.when("welcome", packageStr+"welcome.fxml");
        FXRouter.when("register_user", packageStr+"register_user.fxml");
        FXRouter.when("select_data_for_booking", packageStr+"select_data_for_booking.fxml");
        FXRouter.when("booking", packageStr+"booking.fxml");
        FXRouter.when("purchase", packageStr+"purchase.fxml");
        FXRouter.when("booking_done", packageStr+"booking_done.fxml");
        FXRouter.when("login_manager", packageStr+"login_manager.fxml");
        FXRouter.when("manager_see_detail", packageStr+"manager_see_detail.fxml");
        FXRouter.when("report", packageStr+"report.fxml");
        FXRouter.when("invoice", packageStr+"invoice.fxml");

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}