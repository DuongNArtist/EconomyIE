package economyie.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Duong on 17/02/2016.
 */
public class TagModel {
    private IntegerProperty tagId;
    private StringProperty tagValue;

    public TagModel() {
        this(0, null);
    }

    public TagModel(int tagId, String tagValue) {
        this.tagId = new SimpleIntegerProperty(tagId);
        this.tagValue = new SimpleStringProperty(tagValue);
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

    public String getTagValue() {
        return tagValue.get();
    }

    public void setTagValue(String tagValue) {
        this.tagValue.set(tagValue);
    }

    public StringProperty tagValueProperty() {
        return tagValue;
    }
}
