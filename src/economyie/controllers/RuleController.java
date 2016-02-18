package economyie.controllers;

import economyie.controllers.business.RuleBusiness;
import economyie.controllers.business.TagBusiness;
import economyie.controllers.business.UrlBusiness;
import economyie.models.RuleModel;
import economyie.models.TagModel;
import economyie.models.UrlModel;
import gate.jape.Rule;
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
public class RuleController extends BaseController implements BaseController.ManagerCallback{

    @FXML
    private TextField txtKeyword;
    @FXML
    private TableView<RuleModel> tblData;
    @FXML
    private TableColumn fldRuleId;
    @FXML
    private TableColumn fldTagId;
    @FXML
    private TableColumn fldRulePrefix;
    @FXML
    private TableColumn fldRuleSuffix;
    @FXML
    private TextField txtRuleId;
    @FXML
    private ComboBox<TagModel> cboTagValue;
    @FXML
    private TextField txtRulePrefix;
    @FXML
    private TextField txtRuleSuffix;

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
        fldRuleId.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("ruleId"));
        fldTagId.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("tagId"));
        fldRulePrefix.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("rulePrefix"));
        fldRuleSuffix.setCellValueFactory(new PropertyValueFactory<UrlModel, String>("ruleSuffix"));
        onSelect();
    }

    @FXML
    private void onClicked() {
        RuleModel model = tblData.getSelectionModel().getSelectedItem();
        if (model != null) {
            txtRuleId.setText(String.valueOf(model.getRuleId()));
            for (TagModel aModel : cboTagValue.getItems()) {
                if (aModel.getTagId() == model.getTagId()) {
                    cboTagValue.setValue(aModel);
                    break;
                }
            }
            txtRulePrefix.setText(model.getRulePrefix());
            txtRuleSuffix.setText(model.getRuleSuffix());
        }
    }

    private RuleModel getModel() {
        RuleModel model = new RuleModel();
        model.setRuleId(Integer.parseInt(txtRuleId.getText().trim()));
        model.setTagId(cboTagValue.getSelectionModel().getSelectedItem().getTagId());
        model.setRuleSuffix(txtRulePrefix.getText().trim());
        model.setRulePrefix(txtRuleSuffix.getText().trim());
        return model;
    }

    @Override
    public void onInsert() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (RuleBusiness.insert(getModel()) > 0) {
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
                if (RuleBusiness.update(getModel()) > 0) {
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
                if (RuleBusiness.delete(getModel()) > 0) {
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
                tblData.setItems(RuleBusiness.select(txtKeyword.getText().trim(), 0, 100));
                System.out.println("Table size = " + tblData.getItems().size());
            }
        }).start();
    }
}
