package economyie.controllers;

import economyie.MainApplication;
import economyie.databases.DocBusiness;
import economyie.databases.EntBusiness;
import economyie.models.DocModel;
import economyie.models.EntModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * Created by Duong on 20/02/2016.
 */
public class ExtractionController implements Initializable {

    @FXML
    TableColumn fldDocId;
    @FXML
    TableColumn fldDocName;
    @FXML
    TextField txtDocId;
    @FXML
    TextField txtDocName;
    @FXML
    TableView<DocModel> tblDocs;
    @FXML
    TextArea txtContent;
    @FXML
    TextField txtUrl;
    @FXML
    TextField txtEntId;
    @FXML
    TextField txtEntName;
    @FXML
    TextField txtEntOwner;
    @FXML
    TextField txtEntAddress;
    @FXML
    TextField txtEntProduct;
    @FXML
    TextField txtEntProfit;
    @FXML
    TextField txtEntExport;
    @FXML
    TextField txtEntImport;
    @FXML
    TableColumn fldEntId;
    @FXML
    TableColumn fldEntName;
    @FXML
    TableColumn fldEntOwner;
    @FXML
    TableColumn fldEntAddress;
    @FXML
    TableColumn fldEntProduct;
    @FXML
    TableColumn fldEntProfit;
    @FXML
    TableColumn fldEntExport;
    @FXML
    TableColumn fldEntImport;
    @FXML
    TableView<EntModel> tblEnterprises;

    private String documentsHome = MainApplication.rootHome + "\\res\\documents";
    private MainApplication application;
    private GateController gateController = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fldDocId.setCellValueFactory(new PropertyValueFactory<EntModel, Integer>("docId"));
        fldDocName.setCellValueFactory(new PropertyValueFactory<EntModel, String>("docName"));
        fldEntId.setCellValueFactory(new PropertyValueFactory<EntModel, Integer>("entId"));
        fldEntName.setCellValueFactory(new PropertyValueFactory<EntModel, String>("entName"));
        fldEntOwner.setCellValueFactory(new PropertyValueFactory<EntModel, String>("entOwner"));
        fldEntAddress.setCellValueFactory(new PropertyValueFactory<EntModel, String>("entAddress"));
        fldEntProduct.setCellValueFactory(new PropertyValueFactory<EntModel, String>("entProduct"));
        fldEntProfit.setCellValueFactory(new PropertyValueFactory<EntModel, String>("entProfit"));
        fldEntExport.setCellValueFactory(new PropertyValueFactory<EntModel, String>("entExport"));
        fldEntImport.setCellValueFactory(new PropertyValueFactory<EntModel, String>("entImport"));
        loadFiles();
        new Thread(new Runnable() {
            @Override
            public void run() {
                gateController = GateController.getInstance(MainApplication.gateHome);
            }
        }).start();
    }

    private void loadFiles() {
        tblDocs.setItems(DocBusiness.select("", 0, 10000));
        System.out.print("Document count = " + tblDocs.getItems().size());
    }

    private DocModel getDocModel() {
        DocModel model = new DocModel();
        model.setDocName(txtDocName.getText().trim());
        try {
            model.setDocId(Integer.parseInt(txtDocId.getText().trim()));
        } catch (NumberFormatException e) {
            model.setDocId(0);
        } finally {
            return model;
        }
    }

    @FXML
    void clickDocument() {
        DocModel model = tblDocs.getSelectionModel().getSelectedItem();
        if (model != null) {
            loadEnterprises();
            txtDocId.setText(String.valueOf(model.getDocId()));
            txtDocName.setText(model.getDocName());
            File file = new File(documentsHome + File.separator + model.getDocName());
            if (file != null && file.exists()) {
                txtContent.setText(readDocumentFromFile(file));
            }
        }
    }

    @FXML
    void insertDocument() {
        String fileName = txtDocName.getText().trim();
        if (fileName.length() > 0 && DocBusiness.insert(getDocModel()) > 0) {
            writeDocumentToFile(txtContent.getText(), fileName);
            loadFiles();
        }
    }

    @FXML
    void updateDocument() {
        String fileName = txtDocName.getText().trim();
        if (fileName.length() > 0 && DocBusiness.update(getDocModel()) > 0) {
            writeDocumentToFile(txtContent.getText(), fileName);
            loadFiles();
        }
    }

    @FXML
    void deleteDocument() {
        String fileName = txtDocName.getText().trim();
        if (fileName.length() > 0 && DocBusiness.delete(getDocModel()) > 0) {
            File file = new File(documentsHome + File.separator + fileName);
            if (file != null && file.exists()) {
                if (file.delete()) {
                    txtDocId.clear();
                    txtDocName.clear();
                    txtContent.clear();
                }
            }
            loadFiles();
        }
    }

    private EntModel getModel() {
        EntModel model = new EntModel();
        try {
            model.setEntId(Integer.parseInt(txtEntId.getText().trim()));
        } catch (NumberFormatException e) {
            model.setEntId(0);
        }
        try {
            model.setDocId(Integer.parseInt(txtDocId.getText().trim()));
        } catch (NumberFormatException e) {
            model.setDocId(0);
        }
        model.setEntName(txtEntName.getText().trim());
        model.setEntOwner(txtEntOwner.getText().trim());
        model.setEntAddress(txtEntAddress.getText().trim());
        model.setEntProduct(txtEntProduct.getText().trim());
        model.setEntProfit(txtEntProfit.getText().trim());
        model.setEntExport(txtEntExport.getText().trim());
        model.setEntImport(txtEntImport.getText().trim());
        return model;
    }

    @FXML
    void loadEnterprises() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                tblEnterprises.setItems(EntBusiness.select(txtDocId.getText().trim(), 0, 10000));
                System.out.println("Table size = " + tblEnterprises.getItems().size());
            }
        }).start();
    }

    @FXML
    void clickEnterprise() {
        EntModel model = tblEnterprises.getSelectionModel().getSelectedItem();
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

    @FXML
    void insertEnterprise() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (EntBusiness.insert(getModel()) > 0) {
                    loadEnterprises();
                }
            }
        }).start();
    }

    @FXML
    void updateEnterprise() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (EntBusiness.update(getModel()) > 0) {
                    loadEnterprises();
                }
            }
        }).start();
    }

    @FXML
    void deleteEnterprise() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (EntBusiness.delete(getModel()) > 0) {
                    loadEnterprises();
                }
            }
        }).start();
    }

    @FXML
    void openFromWeb() {
        String url = txtUrl.getText().trim();
        if (url.length() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String content = readDocumentFromUrl(url);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            txtContent.setText(content);
                        }
                    });
                }
            }).start();
        }
    }

    @FXML
    void openFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn một tệp văn bản...");
        fileChooser.setInitialDirectory(new File(MainApplication.userHome));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            txtUrl.setText(file.getAbsolutePath());
            txtContent.setText(readDocumentFromFile(file));
        }
    }

    @FXML
    void extractInformation() {
        int selected = tblDocs.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            gateController.createDocumentFromString(txtContent.getText());
            gateController.executeAnnie();
            ArrayList<String> result = new ArrayList<String>();
            gateController.getResult(result);
            parseObjectFromXml(result.get(0));
        }
    }

    private void parseObjectFromXml(String xml) {
        try {
            EntModel ent = new EntModel();
            try {
                ent.setDocId(Integer.parseInt(txtDocId.getText().trim()));
            } catch (NumberFormatException e) {
                ent.setDocId(0);
            }
            EntBusiness.deleteByDocId(ent);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));
            document.getDocumentElement().normalize();
            NodeList parentNode = document.getElementsByTagName("Sentence");
            for (int i = 0; i < parentNode.getLength(); i++) {
                Node node = parentNode.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    EntModel entModel = new EntModel();

                    StringBuffer nameBuffer = new StringBuffer();
                    NodeList nameNode = e.getElementsByTagName("EnterpriseName");
                    for (int j = 0; j < nameNode.getLength(); j++) {
                        nameBuffer.append(nameNode.item(j).getTextContent());
                        nameBuffer.append(", ");
                    }
                    entModel.setEntName(nameBuffer.toString());

                    StringBuffer ownerBuffer = new StringBuffer();
                    NodeList ownerNode = e.getElementsByTagName("EnterpriseOwner");
                    for (int j = 0; j < ownerNode.getLength(); j++) {
                        ownerBuffer.append(ownerNode.item(j).getTextContent());
                        ownerBuffer.append(", ");
                    }
                    entModel.setEntOwner(ownerBuffer.toString());

                    StringBuffer addressBuffer = new StringBuffer();
                    NodeList addressNode = e.getElementsByTagName("EnterpriseAddress");
                    for (int j = 0; j < addressNode.getLength(); j++) {
                        addressBuffer.append(addressNode.item(j).getTextContent());
                        addressBuffer.append(", ");
                    }
                    entModel.setEntAddress(addressBuffer.toString());

                    StringBuffer productBuffer = new StringBuffer();
                    NodeList productNode = e.getElementsByTagName("EnterpriseProduct");
                    for (int j = 0; j < productNode.getLength(); j++) {
                        productBuffer.append(productNode.item(j).getTextContent());
                        productBuffer.append(", ");
                    }
                    entModel.setEntProduct(productBuffer.toString());

                    StringBuffer profitBuffer = new StringBuffer();
                    NodeList profitNode = e.getElementsByTagName("EnterpriseProfit");
                    for (int j = 0; j < profitNode.getLength(); j++) {
                        profitBuffer.append(profitNode.item(j).getTextContent());
                        profitBuffer.append(", ");
                    }
                    entModel.setEntProfit(profitBuffer.toString());

                    StringBuffer exportBuffer = new StringBuffer();
                    NodeList exportNode = e.getElementsByTagName("EnterpriseExport");
                    for (int j = 0; j < exportNode.getLength(); j++) {
                        exportBuffer.append(exportNode.item(j).getTextContent());
                        exportBuffer.append(", ");
                    }
                    entModel.setEntExport(exportBuffer.toString());

                    StringBuffer importBuffer = new StringBuffer();
                    NodeList importNode = e.getElementsByTagName("EnterpriseImport");
                    for (int j = 0; j < importNode.getLength(); j++) {
                        importBuffer.append(importNode.item(j).getTextContent());
                        importBuffer.append(", ");
                    }
                    entModel.setEntImport(importBuffer.toString());
                    try {
                        entModel.setDocId(Integer.parseInt(txtDocId.getText().trim()));
                    } catch (NumberFormatException e1) {
                        entModel.setDocId(0);
                    }
                    if (!entModel.isEmpty()) {
                        EntBusiness.insert(entModel);
                    }
                }
            }
            loadEnterprises();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException | IOException ex) {

        }
    }

    private void writeDocumentToFile(String content, String fileName) {
        try {
            File file = new File(documentsHome + File.separator + fileName);
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

    private String readDocumentFromUrl(String url) {
        String content = "";
        try {
            org.jsoup.nodes.Document document = Jsoup.connect(url).timeout(30000).get();
            content = document.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }
}
