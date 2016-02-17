package economyie.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Duong on 17/02/2016.
 */
public class DocModel {
    private IntegerProperty docId;
    private StringProperty docUrl;
    private StringProperty docTitle;
    private StringProperty docBody;

    public DocModel() {
        this(0, null, null, null);
    }

    public DocModel(int docId, String docUrl, String docTitle, String docBody) {
        this.docId = new SimpleIntegerProperty(docId);
        this.docUrl =new SimpleStringProperty(docUrl);
        this.docTitle = new SimpleStringProperty(docTitle);
        this.docBody = new SimpleStringProperty(docBody);
    }

    public int getDocId() {
        return docId.get();
    }

    public IntegerProperty docIdProperty() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId.set(docId);
    }

    public String getDocUrl() {
        return docUrl.get();
    }

    public StringProperty docUrlProperty() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl.set(docUrl);
    }

    public String getDocTitle() {
        return docTitle.get();
    }

    public StringProperty docTitleProperty() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle.set(docTitle);
    }

    public String getDocBody() {
        return docBody.get();
    }

    public StringProperty docBodyProperty() {
        return docBody;
    }

    public void setDocBody(String docBody) {
        this.docBody.set(docBody);
    }
}
