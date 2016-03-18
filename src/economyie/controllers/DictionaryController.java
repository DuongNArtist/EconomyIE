package economyie.controllers;

import economyie.MainApplication;
import gate.util.Out;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by Duong on 19/02/2016.
 */
public class DictionaryController implements Initializable {

    @FXML
    TextField txtName;
    @FXML
    TextField txtOwner;
    @FXML
    TextField txtAddress;
    @FXML
    TextField txtProduct;

    @FXML
    ListView<String> lstNames;
    @FXML
    ListView<String> lstOwners;
    @FXML
    ListView<String> lstAddresses;
    @FXML
    ListView<String> lstProducts;

    public static String gazetteerHome = MainApplication.gateHome + "\\plugins\\ANNIE\\resources\\gazetteer";
    public static String enterpriseName = "enterprise_name.lst";
    public static String enterpriseOwner = "enterprise_owner.lst";
    public static String enterpriseAddress = "enterprise_address.lst";
    public static String enterpriseProduct = "enterprise_product.lst";

    private ObservableList<String> dataName = FXCollections.observableArrayList();
    private ObservableList<String> dataOwner = FXCollections.observableArrayList();
    private ObservableList<String> dataAddress = FXCollections.observableArrayList();
    private ObservableList<String> dataProduct = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Out.prln(gazetteerHome);
        initNames();
        initOwners();
        initProducts();
        initAddresses();
    }

    private void readFromFileToList(String name, ObservableList<String> data) {
        try {
            File file = new File(gazetteerHome + File.separator + name);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                data.add(line);
            }
            Out.prln(file + " has " + data.size() + " lines");
            bufferedReader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    private void writeFromListToFile(String name, ObservableList<String> data) {
        Set<String> hashSet = new HashSet<>();
        hashSet.addAll(data);
        data.clear();
        data.addAll(hashSet);
        FXCollections.sort(data);
        try {
            File file = new File(gazetteerHome + File.separator + name);
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            for (String line : data) {
                printWriter.println(line);
            }
            Out.prln(file + " has " + data.size() + " lines");
            printWriter.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    private void click(ListView<String> list, TextField text) {
        text.setText(list.getSelectionModel().getSelectedItem());
    }

    private void insert(ObservableList<String> data, TextField textField, String file) {
        data.add(textField.getText().trim());
        textField.clear();
        writeFromListToFile(file, data);
    }

    private void update(ObservableList<String> data, ListView<String> listView, TextField textField, String file) {
        int selected = listView.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            data.set(selected, textField.getText().trim());
            textField.clear();
            writeFromListToFile(file, data);
        }
    }

    private void delete(ObservableList<String> data, ListView<String> listView, TextField textField, String file) {
        int selected = listView.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            data.remove(selected);
            textField.clear();
            writeFromListToFile(file, data);
        }
    }

    private void init(String name, ObservableList<String> list, ListView<String> listView) {
        readFromFileToList(name, list);
        listView.setItems(list);
    }

    void initNames() {
        init(enterpriseName, dataName, lstNames);
    }

    void initOwners() {
        init(enterpriseOwner, dataOwner, lstOwners);
    }

    void initAddresses() {
        init(enterpriseAddress, dataAddress, lstAddresses);
    }

    void initProducts() {
        init(enterpriseProduct, dataProduct, lstProducts);
    }

    @FXML
    void clickName() {
        click(lstNames, txtName);
    }

    @FXML
    void insertName() {
        insert(dataName, txtName, enterpriseName);
    }

    @FXML
    void updateName() {
        update(dataName, lstNames, txtName, enterpriseName);
    }

    @FXML
    void deleteName() {
        delete(dataName, lstNames, txtName, enterpriseName);
    }

    @FXML
    void clickOwner() {
        click(lstOwners, txtOwner);
    }

    @FXML
    void insertOwner() {
        insert(dataOwner, txtOwner, enterpriseOwner);
    }

    @FXML
    void updateOwner() {
        update(dataOwner, lstOwners, txtOwner, enterpriseOwner);
    }

    @FXML
    void deleteOwner() {
        delete(dataOwner, lstOwners, txtOwner, enterpriseOwner);
    }

    @FXML
    void clickAddress() {
        click(lstAddresses, txtAddress);
    }

    @FXML
    void insertAddress() {
        insert(dataAddress, txtAddress, enterpriseAddress);
    }

    @FXML
    void updateAddress() {
        update(dataAddress, lstAddresses, txtAddress, enterpriseAddress);
    }

    @FXML
    void deleteAddress() {
        delete(dataAddress, lstAddresses, txtAddress, enterpriseAddress);
    }

    @FXML
    void clickProduct() {
        click(lstProducts, txtProduct);
    }

    @FXML
    void insertProduct() {
        insert(dataProduct, txtProduct, enterpriseProduct);
    }

    @FXML
    void updateProduct() {
        update(dataProduct, lstProducts, txtProduct, enterpriseProduct);
    }

    @FXML
    void deleteProduct() {
        delete(dataProduct, lstProducts, txtProduct, enterpriseProduct);
    }
}
