package economyie.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Duong on 17/02/2016.
 */
public class RuleModel {
    private IntegerProperty ruleId;
    private IntegerProperty tagId;
    private StringProperty rulePrefix;
    private StringProperty ruleSuffix;

    public RuleModel() {
        this(0, 0, null, null);
    }

    public RuleModel(int ruleId, int tagId, String rulePrefix, String ruleSuffix) {
        this.ruleId = new SimpleIntegerProperty(ruleId);
        this.tagId = new SimpleIntegerProperty(tagId);
        this.rulePrefix = new SimpleStringProperty(rulePrefix);
        this.ruleSuffix = new SimpleStringProperty(ruleSuffix);
    }

    public int getRuleId() {
        return ruleId.get();
    }

    public void setRuleId(int ruleId) {
        this.ruleId.set(ruleId);
    }

    public IntegerProperty ruleIdProperty() {
        return ruleId;
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

    public String getRulePrefix() {
        return rulePrefix.get();
    }

    public void setRulePrefix(String rulePrefix) {
        this.rulePrefix.set(rulePrefix);
    }

    public StringProperty rulePrefixProperty() {
        return rulePrefix;
    }

    public String getRuleSuffix() {
        return ruleSuffix.get();
    }

    public void setRuleSuffix(String ruleSuffix) {
        this.ruleSuffix.set(ruleSuffix);
    }

    public StringProperty ruleSuffixProperty() {
        return ruleSuffix;
    }
}
