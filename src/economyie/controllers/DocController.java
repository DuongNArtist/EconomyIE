package economyie.controllers;

import economyie.controllers.business.DocBusiness;
import economyie.controllers.business.UrlBusiness;
import economyie.models.DocModel;
import economyie.models.UrlModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Duong on 17/02/2016.
 */
public class DocController  extends BaseController implements BaseController.ManagerCallback{

    @FXML
    private TextField txtKeyword;
    @FXML
    private TableView<DocModel> tblData;
    @FXML
    private TableColumn fldDocId;
    @FXML
    private TableColumn fldDocUrl;
    @FXML
    private TableColumn fldDocTitle;
    @FXML
    private TableColumn fldDocBody;
    @FXML
    private TextField txtDocId;
    @FXML
    private TextField txtDocUrl;
    @FXML
    private TextField txtDocTitle;
    @FXML
    private TextArea txtDocBody;
    @FXML
    private TextArea txtLog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fldDocId.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("docId"));
        fldDocUrl.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("docUrl"));
        fldDocTitle.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("docTitle"));
        fldDocBody.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("docBody"));
        onSelect();
    }

    @FXML
    private void onClicked() {
        DocModel model = tblData.getSelectionModel().getSelectedItem();
        if (model != null) {
            txtDocId.setText(String.valueOf(model.getDocId()));
            txtDocUrl.setText(model.getDocUrl());
            txtDocTitle.setText(model.getDocTitle());
            txtDocBody.setText(model.getDocBody());
        }
    }

    private DocModel getModel() {
        DocModel model = new DocModel();
        model.setDocId(Integer.parseInt(txtDocId.getText().trim()));
        model.setDocUrl(txtDocUrl.getText().trim());
        model.setDocTitle(txtDocTitle.getText().trim());
        model.setDocBody(txtDocBody.getText().trim());
        return model;
    }

    @Override
    public void onInsert() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (DocBusiness.insert(getModel()) > 0) {
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
                if (DocBusiness.update(getModel()) > 0) {
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
                if (DocBusiness.delete(getModel()) > 0) {
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
                tblData.setItems(DocBusiness.select(txtKeyword.getText().trim(), 0, 100));
                System.out.println("Table size = " + tblData.getItems().size());
            }
        }).start();
    }
}
