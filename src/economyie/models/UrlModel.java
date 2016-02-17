package economyie.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Duong on 17/02/2016.
 */
public class UrlModel {
    private IntegerProperty urlId;
    private StringProperty urlMain;
    private StringProperty urlFormat;
    private StringProperty urlTag;
    private IntegerProperty urlStart;
    private IntegerProperty urlEnd;
    private IntegerProperty urlStep;


    public UrlModel() {
        this(0, null, null, null, 0, 0, 0);
    }

    public UrlModel(int urlId, String urlMain, String urlFormat, String urlTag, int urlStart, int urlEnd, int urlStep) {
        this.urlId = new SimpleIntegerProperty(urlId);
        this.urlMain = new SimpleStringProperty(urlMain);
        this.urlFormat = new SimpleStringProperty(urlFormat);
        this.urlTag = new SimpleStringProperty(urlTag);
        this.urlStart = new SimpleIntegerProperty(urlStart);
        this.urlEnd = new SimpleIntegerProperty(urlEnd);
        this.urlStep = new SimpleIntegerProperty(urlStep);
    }

    public int getUrlId() {
        return urlId.get();
    }

    public IntegerProperty urlIdProperty() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId.set(urlId);
    }

    public String getUrlMain() {
        return urlMain.get();
    }

    public StringProperty urlMainProperty() {
        return urlMain;
    }

    public void setUrlMain(String urlMain) {
        this.urlMain.set(urlMain);
    }

    public String getUrlFormat() {
        return urlFormat.get();
    }

    public StringProperty urlFormatProperty() {
        return urlFormat;
    }

    public void setUrlFormat(String urlFormat) {
        this.urlFormat.set(urlFormat);
    }

    public String getUrlTag() {
        return urlTag.get();
    }

    public StringProperty urlTagProperty() {
        return urlTag;
    }

    public void setUrlTag(String urlTag) {
        this.urlTag.set(urlTag);
    }

    public int getUrlStart() {
        return urlStart.get();
    }

    public IntegerProperty urlStartProperty() {
        return urlStart;
    }

    public void setUrlStart(int urlStart) {
        this.urlStart.set(urlStart);
    }

    public int getUrlEnd() {
        return urlEnd.get();
    }

    public IntegerProperty urlEndProperty() {
        return urlEnd;
    }

    public void setUrlEnd(int urlEnd) {
        this.urlEnd.set(urlEnd);
    }

    public int getUrlStep() {
        return urlStep.get();
    }

    public IntegerProperty urlStepProperty() {
        return urlStep;
    }

    public void setUrlStep(int urlStep) {
        this.urlStep.set(urlStep);
    }
}
