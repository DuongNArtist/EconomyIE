package economyie;

import economyie.controllers.GateController;
import economyie.controllers.LoginController;
import economyie.controllers.MainController;
import gate.Gate;
import gate.util.Out;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Duong on 17/02/2016.
 */
public class MainApplication extends Application {

    public static String gateHome = "C:\\gate";
    public static String rootHome = System.getProperty("user.dir");
    public static String userHome = System.getProperty("user.home");

    public static void main(String[] args) {
        launch(args);
    }

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Hệ thống trích rút thông tin Kinh tế Tiếng Việt");
        this.stage.setResizable(false);
        try {
            URL url = MainApplication.class.getResource("views/main.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            MainController controller = loader.getController();
            controller.setApplication(this);
            this.stage.setScene(scene);
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
