package economyie.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Duong on 17/02/2016.
 */
public class InfoModel {
    private IntegerProperty infoId;
    private StringProperty infoValue;

    public InfoModel() {
        this(0, null);
    }

    public InfoModel(int infoId, String infoValue) {
        this.infoId = new SimpleIntegerProperty(infoId);
        this.infoValue = new SimpleStringProperty(infoValue);
    }

    public int getInfoId() {
        return infoId.get();
    }

    public void setInfoId(int infoId) {
        this.infoId.set(infoId);
    }

    public IntegerProperty infoIdProperty() {
        return infoId;
    }

    public String getInfoValue() {
        return infoValue.get();
    }

    public void setInfoValue(String infoValue) {
        this.infoValue.set(infoValue);
    }

    public StringProperty infoValueProperty() {
        return infoValue;
    }
}
