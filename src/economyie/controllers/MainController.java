package economyie.controllers;

import economyie.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Duong on 17/02/2016.
 */
public class MainController implements Initializable {

    @FXML
    BorderPane panMain;
    @FXML
    HBox boxMenu;
    @FXML
    Button btnLogout;
    private MainApplication application;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logoutSystem();
        showLogin();
        //btnLogout.setVisible(false);
    }

    @FXML
    public void logoutSystem() {
        boxMenu.setVisible(false);
        showLogin();
    }

    @FXML
    public void showLogin() {
        try {
            URL url = MainApplication.class.getResource("views/login.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panMain.setCenter(pane);
            LoginController controller = loader.getController();
            controller.setApplication(application);
            controller.setLoginCallback(new LoginController.LoginCallback() {
                @Override
                public void onLoginSucceed() {
                    panMain.setCenter(null);
                    boxMenu.setVisible(true);
                }

                @Override
                public void onLoginFailed() {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showDictionary() {
        try {
            URL url = MainApplication.class.getResource("views/dictionary.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            GridPane pane = loader.load();
            panMain.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showTraining() {
        try {
            URL url = MainApplication.class.getResource("views/training.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panMain.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showExtraction() {
        try {
            URL url = MainApplication.class.getResource("views/extraction.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            GridPane pane = loader.load();
            panMain.setCenter(pane);
            ExtractionController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }
}
