package economyie.controllers;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Duong on 17/02/2016.
 */
public class LoginController extends BaseController {

    private LoginCallback loginCallback;

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

    interface LoginCallback {
        void onLoginSucceed();
        void onLoginFailed();
    };

}
