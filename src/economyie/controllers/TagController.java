package economyie.controllers;

import economyie.controllers.business.TagBusiness;
import economyie.models.TagModel;
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
public class TagController extends BaseController implements BaseController.ManagerCallback {

    @FXML
    private TextField txtKeyword;
    @FXML
    private TableView<TagModel> tblData;
    @FXML
    private TableColumn fldTagId;
    @FXML
    private TableColumn fldTagValue;
    @FXML
    private TextField txtTagId;
    @FXML
    private TextField txtTagValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fldTagId.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("tagId"));
        fldTagValue.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("tagValue"));
        onSelect();
    }

    @FXML
    private void onClicked() {
        TagModel model = tblData.getSelectionModel().getSelectedItem();
        if (model != null) {
            txtTagId.setText(String.valueOf(model.getTagId()));
            txtTagValue.setText(model.getTagValue());
        }
    }

    private TagModel getModel() {
        TagModel model = new TagModel();
        model.setTagId(Integer.parseInt(txtTagId.getText().trim()));
        model.setTagValue(txtTagValue.getText().trim());
        return model;
    }

    @Override
    public void onInsert() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (TagBusiness.insert(getModel()) > 0) {
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
                if (TagBusiness.update(getModel()) > 0) {
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
                if (TagBusiness.delete(getModel()) > 0) {
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
                tblData.setItems(TagBusiness.select(txtKeyword.getText().trim(), 0, 100));
                System.out.println("Table size = " + tblData.getItems().size());
            }
        }).start();
    }
}
