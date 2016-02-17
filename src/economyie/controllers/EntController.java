package economyie.controllers;

import economyie.controllers.business.UrlBusiness;
import economyie.models.UrlModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Duong on 17/02/2016.
 */
public class EntController extends BaseController implements BaseController.ManagerCallback{

    @FXML
    private TextField txtKeyword;
    @FXML
    private TableView<UrlModel> tblData;
    @FXML
    private TableColumn fldUrlId;
    @FXML
    private TableColumn fldUrlMain;
    @FXML
    private TableColumn fldUrlFormat;
    @FXML
    private TableColumn fldUrlTag;
    @FXML
    private TableColumn fldUrlStart;
    @FXML
    private TableColumn fldUrlEnd;
    @FXML
    private TableColumn fldUrlStep;
    @FXML
    private TextField txtUrlId;
    @FXML
    private TextField txtUrlMain;
    @FXML
    private TextField txtUrlFormat;
    @FXML
    private TextField txtUrlTag;
    @FXML
    private TextField txtUrlStart;
    @FXML
    private TextField txtUrlEnd;
    @FXML
    private TextField txtUrlStep;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fldUrlId.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("urlId"));
        fldUrlMain.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("urlMain"));
        fldUrlFormat.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("urlFormat"));
        fldUrlTag.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("urlTag"));
        fldUrlStart.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("urlStart"));
        fldUrlEnd.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("urlEnd"));
        fldUrlStep.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("urlStep"));
        onSelect();
    }

    @FXML
    private void onClicked() {
        UrlModel model = tblData.getSelectionModel().getSelectedItem();
        if (model != null) {
            txtUrlId.setText(String.valueOf(model.getUrlId()));
            txtUrlMain.setText(model.getUrlMain());
            txtUrlFormat.setText(model.getUrlFormat());
            txtUrlTag.setText(model.getUrlTag());
            txtUrlStart.setText(String.valueOf(model.getUrlStart()));
            txtUrlEnd.setText(String.valueOf(model.getUrlEnd()));
            txtUrlStep.setText(String.valueOf(model.getUrlStep()));
        }
    }

    private UrlModel getModel() {
        UrlModel model = new UrlModel();
        model.setUrlId(Integer.parseInt(txtUrlId.getText().trim()));
        model.setUrlMain(txtUrlMain.getText().trim());
        model.setUrlFormat(txtUrlFormat.getText().trim());
        model.setUrlTag(txtUrlTag.getText().trim());
        model.setUrlStart(Integer.parseInt(txtUrlStart.getText().trim()));
        model.setUrlEnd(Integer.parseInt(txtUrlEnd.getText().trim()));
        model.setUrlStep(Integer.parseInt(txtUrlStep.getText().trim()));
        return model;
    }

    @Override
    public void onInsert() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (UrlBusiness.insert(getModel()) > 0) {
                    onSelect();
                }
            }
        }).start();
    }

    @Override
    public void onUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (UrlBusiness.update(getModel()) > 0) {
                    onSelect();
                }
            }
        }).start();
    }

    @Override
    public void onDelete() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (UrlBusiness.delete(getModel()) > 0) {
                    onSelect();
                }
            }
        }).start();
    }

    @Override
    public void onSelect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                tblData.setItems(UrlBusiness.select(txtKeyword.getText().trim(), 0, 100));
                System.out.println("Table size = " + tblData.getItems().size());
            }
        }).start();
    }
}
