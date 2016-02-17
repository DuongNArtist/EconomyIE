package economyie.controllers;

import economyie.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Duong on 17/02/2016.
 */
public class MainController extends BaseController {

    @FXML
    private javafx.scene.control.Menu mnuTraining;
    @FXML
    private javafx.scene.control.Menu mnuExtraction;
    @FXML
    private javafx.scene.control.MenuItem mnuLogin;
    @FXML
    private javafx.scene.control.MenuItem mnuLogout;
    @FXML
    private BorderPane panMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logout();
        showLogin();
    }

    @FXML
    public void logout() {
        mnuLogin.setVisible(true);
        mnuLogout.setVisible(false);
        mnuTraining.setVisible(false);
        mnuExtraction.setVisible(false);
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
                    mnuLogin.setVisible(false);
                    mnuLogout.setVisible(true);
                    mnuTraining.setVisible(true);
                    mnuExtraction.setVisible(true);
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
    public void showDoc() {
        try {
            URL url = MainApplication.class.getResource("views/doc.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panMain.setCenter(pane);
            DocController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showEnt() {
        try {
            URL url = MainApplication.class.getResource("views/ent.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panMain.setCenter(pane);
            EntController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showUrl() {
        try {
            URL url = MainApplication.class.getResource("views/url.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panMain.setCenter(pane);
            UrlController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showTag() {
        try {
            URL url = MainApplication.class.getResource("views/tag.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panMain.setCenter(pane);
            TagController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showDict() {
        try {
            URL url = MainApplication.class.getResource("views/dict.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panMain.setCenter(pane);
            DictController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showRule() {
        try {
            URL url = MainApplication.class.getResource("views/rule.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panMain.setCenter(pane);
            RuleController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
