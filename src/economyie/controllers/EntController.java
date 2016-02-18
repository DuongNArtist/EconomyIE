package economyie.controllers;

import economyie.controllers.business.EntBusiness;
import economyie.controllers.business.UrlBusiness;
import economyie.models.EntModel;
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
    private TableView<EntModel> tblData;
    @FXML
    private TableColumn fldEntId;
    @FXML
    private TableColumn fldEntName;
    @FXML
    private TableColumn fldEntOwner;
    @FXML
    private TableColumn fldEntAddress;
    @FXML
    private TableColumn fldEntProduct;
    @FXML
    private TableColumn fldEntProfit;
    @FXML
    private TableColumn fldEntExport;
    @FXML
    private TableColumn fldEntImport;
    @FXML
    private TextField txtEntId;
    @FXML
    private TextField txtEntName;
    @FXML
    private TextField txtEntOwner;
    @FXML
    private TextField txtEntAddress;
    @FXML
    private TextField txtEntProduct;
    @FXML
    private TextField txtEntProfit;
    @FXML
    private TextField txtEntExport;
    @FXML
    private TextField txtEntImport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fldEntId.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("entId"));
        fldEntName.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("entName"));
        fldEntOwner.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("entOwner"));
        fldEntAddress.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("entAddress"));
        fldEntProduct.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("entProduct"));
        fldEntProfit.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("entProfit"));
        fldEntExport.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("entExport"));
        fldEntImport.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("entImport"));
        onSelect();
    }

    @FXML
    private void onClicked() {
        EntModel model = tblData.getSelectionModel().getSelectedItem();
        if (model != null) {
            txtEntId.setText(String.valueOf(model.getEntId()));
            txtEntName.setText(model.getEntName());
            txtEntOwner.setText(model.getEntOwner());
            txtEntAddress.setText(model.getEntAddress());
            txtEntProduct.setText(model.getEntProduct());
            txtEntProfit.setText(model.getEntProfit());
            txtEntExport.setText(model.getEntExport());
            txtEntImport.setText(model.getEntImport());
        }
    }

    private EntModel getModel() {
        EntModel model = new EntModel();
        model.setEntId(Integer.parseInt(txtEntId.getText().trim()));
        model.setEntName(txtEntName.getText().trim());
        model.setEntOwner(txtEntOwner.getText().trim());
        model.setEntAddress(txtEntAddress.getText().trim());
        model.setEntProduct(txtEntProduct.getText().trim());
        model.setEntProfit(txtEntProfit.getText().trim());
        model.setEntExport(txtEntExport.getText().trim());
        model.setEntImport(txtEntImport.getText().trim());
        return model;
    }

    @Override
    public void onInsert() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (EntBusiness.insert(getModel()) > 0) {
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
                if (EntBusiness.update(getModel()) > 0) {
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
                if (EntBusiness.delete(getModel()) > 0) {
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
                tblData.setItems(EntBusiness.select(txtKeyword.getText().trim(), 0, 100));
                System.out.println("Table size = " + tblData.getItems().size());
            }
        }).start();
    }
}
