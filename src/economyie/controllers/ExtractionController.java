package economyie.controllers;

import economyie.MainApplication;
import economyie.utils.DOMParser;
import gate.util.Out;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Duong on 20/02/2016.
 */
public class ExtractionController implements Initializable {

    @FXML
    ListView<String> lstFiles;
    @FXML
    TextArea txtContent;
    @FXML
    TextField txtFileName;
    @FXML
    TextField txtUrl;
    private ObservableList<String> fileList = FXCollections.observableArrayList();
    private String documentsHome = MainApplication.rootHome + "\\res\\documents";
    private MainApplication application;
    private GateController gateController = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                gateController = GateController.getInstance(MainApplication.gateHome);
            }
        }).start();
        initFiles();
    }

    private void initFiles() {
        lstFiles.setItems(fileList);
        File root = new File(documentsHome);
        if (root.exists()) {
            File[] files = root.listFiles();
            for (File file : files) {
                fileList.add(file.getName());
            }
        }
    }

    @FXML
    void clickDocument() {
        String fileName = lstFiles.getSelectionModel().getSelectedItem();
        txtContent.clear();
        File file = new File(documentsHome + File.separator + fileName);
        if (file != null) {
            txtFileName.setText(fileName);
            txtContent.setText(readDocumentFromFile(file));
        }
    }

    @FXML
    void insertDocument() {
        String fileName = txtFileName.getText().trim();
        if (fileName.length() > 0 && fileList.indexOf(fileName) < 0) {
            writeDocumentToFile(txtContent.getText(), fileName);
            fileList.add(fileName);
            FXCollections.sort(fileList);
        } else {
            Out.prln("File existed!");
        }
    }

    @FXML
    void updateDocument() {
        String fileName = txtFileName.getText().trim();
        if (fileName.length() > 0 && fileList.indexOf(fileName) >= 0) {
            writeDocumentToFile(txtContent.getText(), fileName);
        } else {
            Out.prln("File not found!");
        }
    }

    @FXML
    void deleteDocument() {
        int selected = lstFiles.getSelectionModel().getSelectedIndex();
        String fileName = lstFiles.getSelectionModel().getSelectedItem();
        File file = new File(documentsHome + File.separator + fileName);
        if (file.exists() && selected >= 0) {
            if (file.delete()) {
                fileList.remove(selected);
                txtFileName.clear();
                txtContent.clear();
            }
        }
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
        gateController.createDocumentFromString(txtContent.getText());
        gateController.executeAnnie();
        ArrayList<String> result = new ArrayList<String>();
        gateController.getResult(result);
        DOMParser domParser = new DOMParser();
        domParser.importXMLDocument(result.get(0));
        for (String s : domParser.getNodeValuesByTagName("Sentence")) {
            Out.prln(s);
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
