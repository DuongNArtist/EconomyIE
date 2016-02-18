package economyie.controllers;

import economyie.controllers.business.DictBusiness;
import economyie.controllers.business.TagBusiness;
import economyie.models.DictModel;
import economyie.models.TagModel;
import economyie.models.UrlModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Duong on 17/02/2016.
 */
public class DictController extends BaseController implements BaseController.ManagerCallback {

    @FXML
    private TextField txtKeyword;
    @FXML
    private TableView<DictModel> tblData;
    @FXML
    private TableColumn fldDictId;
    @FXML
    private TableColumn fldTagId;
    @FXML
    private TableColumn fldDictValue;
    @FXML
    private TextField txtDictId;
    @FXML
    private ComboBox<TagModel> cboTagValue;
    @FXML
    private TextField txtDictValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cboTagValue.setItems(TagBusiness.select("", 0, 1000));
        cboTagValue.setConverter(new StringConverter<TagModel>() {
            @Override
            public String toString(TagModel model) {
                return model.getTagValue();
            }

            @Override
            public TagModel fromString(String string) {
                return null;
            }
        });
        fldDictId.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("dictId"));
        fldTagId.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("tagId"));
        fldDictValue.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("dictValue"));
        onSelect();
    }

    @FXML
    private void onClicked() {
        DictModel model = tblData.getSelectionModel().getSelectedItem();
        if (model != null) {
            txtDictId.setText(String.valueOf(model.getDictId()));
            for (TagModel aModel : cboTagValue.getItems()) {
                if (aModel.getTagId() == model.getTagId()) {
                    cboTagValue.setValue(aModel);
                    break;
                }
            }
            txtDictValue.setText(model.getDictValue());
        }
    }

    private DictModel getModel() {
        DictModel model = new DictModel();
        model.setDictId(Integer.parseInt(txtDictId.getText().trim()));
        model.setTagId(cboTagValue.getSelectionModel().getSelectedItem().getTagId());
        model.setDictValue(txtDictValue.getText().trim());
        return model;
    }

    @Override
    public void onInsert() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (DictBusiness.insert(getModel()) > 0) {
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
                if (DictBusiness.update(getModel()) > 0) {
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
                if (DictBusiness.delete(getModel()) > 0) {
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
                tblData.setItems(DictBusiness.select(txtKeyword.getText().trim(), 0, 100));
                System.out.println("Table size = " + tblData.getItems().size());
            }
        }).start();
    }
}
