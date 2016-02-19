package economyie.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Duong on 17/02/2016.
 */
public class LoginController implements Initializable {

    private LoginCallback loginCallback;
    private Application application;

    public LoginCallback getLoginCallback() {
        return loginCallback;
    }

    public void setLoginCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void login() {
        loginCallback.onLoginSucceed();
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    interface LoginCallback {
        void onLoginSucceed();

        void onLoginFailed();
    }

    ;

}
