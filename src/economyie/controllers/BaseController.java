package economyie.controllers;

import economyie.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * Created by Duong on 17/02/2016.
 */
public abstract class BaseController implements Initializable{
    protected MainApplication application;

    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }

    interface ManagerCallback {
        void onInsert();
        void onUpdate();
        void onDelete();
        void onSearch();
    }
}
