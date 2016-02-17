package economyie.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Duong on 17/02/2016.
 */
public class DictModel {
    private IntegerProperty dictId;
    private IntegerProperty tagId;
    private StringProperty dictValue;

    public DictModel() {
        this(0, 0, null);
    }

    public DictModel(int dictId, int tagId, String dictValue) {
        this.dictId = new SimpleIntegerProperty(dictId);
        this.tagId = new SimpleIntegerProperty(tagId);
        this.dictValue = new SimpleStringProperty(dictValue);
    }

    public int getDictId() {
        return dictId.get();
    }

    public void setDictId(int dictId) {
        this.dictId.set(dictId);
    }

    public IntegerProperty dictIdProperty() {
        return dictId;
    }

    public int getTagId() {
        return tagId.get();
    }

    public void setTagId(int tagId) {
        this.tagId.set(tagId);
    }

    public IntegerProperty tagIdProperty() {
        return tagId;
    }

    public String getDictValue() {
        return dictValue.get();
    }

    public void setDictValue(String dictValue) {
        this.dictValue.set(dictValue);
    }

    public StringProperty dictValueProperty() {
        return dictValue;
    }
}
