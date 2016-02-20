package economyie.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Duong on 20/02/2016.
 */
public class DocModel {

    private IntegerProperty docId;
    private StringProperty docName;

    public DocModel() {
        this(0, null);
    }

    public DocModel(int docId, String docName) {
        this.docId = new SimpleIntegerProperty(docId);
        this.docName = new SimpleStringProperty(docName);
    }

    public int getDocId() {
        return docId.get();
    }

    public void setDocId(int docId) {
        this.docId.set(docId);
    }

    public IntegerProperty docIdProperty() {
        return docId;
    }

    public String getDocName() {
        return docName.get();
    }

    public void setDocName(String docName) {
        this.docName.set(docName);
    }

    public StringProperty docNameProperty() {
        return docName;
    }
}
