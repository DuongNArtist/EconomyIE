package economyie.controllers;

import economyie.MainApplication;
import gate.util.Out;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Hien on 06/03/2016.
 */
public class TrainingController implements Initializable {

    @FXML
    ListView<String> lstName;
    @FXML
    ListView<String> lstOwner;
    @FXML
    ListView<String> lstAddress;
    @FXML
    ListView<String> lstProduct;
    @FXML
    ListView<String> lstDoc;
    @FXML
    TextField txtUrl;
    @FXML
    TextArea txtDoc;
    @FXML
    Button btnDissect;
    ObservableList<String> names = FXCollections.observableArrayList();
    ObservableList<String> owners = FXCollections.observableArrayList();
    ObservableList<String> addresses = FXCollections.observableArrayList();
    ObservableList<String> products = FXCollections.observableArrayList();
    ObservableList<String> docs = FXCollections.observableArrayList();
    private GateController gateController = null;
    private String trainersHome = MainApplication.rootHome + "\\res\\trainers";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                btnDissect.setDisable(true);
                gateController = null;
                GateController.setMainContent(GateController.training);
                gateController = GateController.getInstance(MainApplication.gateHome);
                btnDissect.setDisable(false);
            }
        }).start();
        lstName.setItems(names);
        lstOwner.setItems(owners);
        lstAddress.setItems(addresses);
        lstProduct.setItems(products);
        lstDoc.setItems(docs);
        loadDoc();
    }

    void loadDoc() {
        docs.clear();
        File file = new File(trainersHome);
        if (file != null && file.exists()) {
            for (String str : file.list()) {
                docs.add(str);
            }
        }
    }

    private void writeDocumentToFile(String content, String fileName) {
        try {
            File file = new File(trainersHome + File.separator + fileName);
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            printWriter.println(content);
            printWriter.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    private String readDocumentFromFile(File file) {
        String newLine = System.getProperty("line.separator");
        StringBuffer buffer = new StringBuffer();
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                for (String line; (line = bufferedReader.readLine()) != null; ) {
                    buffer.append(line);
                    buffer.append(newLine);
                }
                bufferedReader.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        }
        return buffer.toString();
    }

    private void writeFromListToFile(String name, ObservableList<String> data) {
        FXCollections.sort(data);
        try {
            File file = new File(DictionaryController.gazetteerHome + File.separator + name);
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

    private void parseObjectFromXml(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));
            document.getDocumentElement().normalize();
            NodeList parentNode = document.getElementsByTagName("Sentence");
            names.clear();
            owners.clear();
            addresses.clear();
            products.clear();
            for (int i = 0; i < parentNode.getLength(); i++) {
                Node node = parentNode.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;

                    NodeList nameNode = e.getElementsByTagName("EnterpriseName");
                    for (int j = 0; j < nameNode.getLength(); j++) {
                        names.add(nameNode.item(j).getTextContent());
                    }

                    NodeList ownerNode = e.getElementsByTagName("EnterpriseOwner");
                    for (int j = 0; j < ownerNode.getLength(); j++) {
                        owners.add(ownerNode.item(j).getTextContent());
                    }

                    NodeList addressNode = e.getElementsByTagName("EnterpriseAddress");
                    for (int j = 0; j < addressNode.getLength(); j++) {
                        addresses.add(addressNode.item(j).getTextContent());
                    }

                    NodeList productNode = e.getElementsByTagName("EnterpriseProduct");
                    for (int j = 0; j < productNode.getLength(); j++) {
                        products.add(productNode.item(j).getTextContent());
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException | IOException ex) {

        }
    }

    @FXML
    void dissect() {
        String text = txtDoc.getText();
        if (text.length() > 0) {
            gateController.createDocumentFromString(text);
            gateController.executeAnnie();
            ArrayList<String> result = new ArrayList<String>();
            gateController.getResult(result);
            parseObjectFromXml(result.get(0));
        }
    }

    @FXML
    void selectDoc() {
        String name = lstDoc.getSelectionModel().getSelectedItem();
        File file = new File(trainersHome + File.separator + name);
        if (file != null && file.exists()) {
            txtUrl.setText(name);
            txtDoc.setText(readDocumentFromFile(file));
        }
    }

    @FXML
    void openDoc() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn một tệp văn bản...");
        fileChooser.setInitialDirectory(new File(MainApplication.userHome));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            txtUrl.setText(file.getName());
            txtDoc.setText(readDocumentFromFile(file));
        }
    }

    @FXML
    void addDoc() {
        String fileName = txtUrl.getText().trim();
        if (fileName.length() > 0) {
            writeDocumentToFile(txtDoc.getText(), fileName);
            loadDoc();
        }
    }

    @FXML
    void delDoc() {
        String fileName = lstDoc.getSelectionModel().getSelectedItem();
        if (fileName.length() > 0) {
            File file = new File(trainersHome + File.separator + fileName);
            if (file != null && file.exists()) {
                if (file.delete()) {
                    txtUrl.clear();
                    txtDoc.clear();
                    loadDoc();
                }
            }
        }
    }

    @FXML
    void download() {
        txtDoc.clear();
        String url = txtUrl.getText().trim();
        if (url.length() > 0) {
            try {
                org.jsoup.nodes.Document document = Jsoup.connect(url).timeout(30000).get();
                txtDoc.setText(document.text());
            } catch (IOException e) {
                e.printStackTrace();
                txtDoc.setText("Lỗi tải trang!");
            }
        }
    }

    @FXML
    void addName() {
        if (names.size() > 0) {
            writeFromListToFile(DictionaryController.enterpriseName, names);
        }
    }

    @FXML
    void delName() {
        int index = lstName.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            names.remove(index);
        }
    }

    @FXML
    void addOwner() {

    }

    @FXML
    void delOwner() {
        int index = lstOwner.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            owners.remove(index);
        }
    }

    @FXML
    void addAddress() {

    }

    @FXML
    void delAddress() {
        int index = lstAddress.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            addresses.remove(index);
        }
    }

    @FXML
    void addProduct() {

    }

    @FXML
    void delProduct() {
        int index = lstProduct.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            products.remove(index);
        }
    }
}
