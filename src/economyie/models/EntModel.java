package economyie.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Duong on 17/02/2016.
 */
public class EntModel {
    private IntegerProperty entId;
    private StringProperty entName;
    private StringProperty entOwner;
    private StringProperty entAddress;
    private StringProperty entProduct;
    private StringProperty entProfit;
    private StringProperty entImport;
    private StringProperty entExport;


    public EntModel() {
        this(0, null, null, null, null, null, null, null);
    }

    public EntModel(int entId, String entName, String entOwner, String entAddress, String entProduct, String entProfit, String entImport, String entExport) {
        this.entId = new SimpleIntegerProperty(entId);
        this.entName = new SimpleStringProperty(entName);
        this.entOwner = new SimpleStringProperty(entOwner);
        this.entAddress = new SimpleStringProperty(entAddress);
        this.entProduct = new SimpleStringProperty(entProduct);
        this.entProfit = new SimpleStringProperty(entProfit);
        this.entImport = new SimpleStringProperty(entImport);
        this.entExport = new SimpleStringProperty(entExport);
    }

    public int getEntId() {
        return entId.get();
    }

    public void setEntId(int entId) {
        this.entId.set(entId);
    }

    public IntegerProperty entIdProperty() {
        return entId;
    }

    public String getEntName() {
        return entName.get();
    }

    public void setEntName(String entName) {
        this.entName.set(entName);
    }

    public StringProperty entNameProperty() {
        return entName;
    }

    public String getEntOwner() {
        return entOwner.get();
    }

    public void setEntOwner(String entOwner) {
        this.entOwner.set(entOwner);
    }

    public StringProperty entOwnerProperty() {
        return entOwner;
    }

    public String getEntAddress() {
        return entAddress.get();
    }

    public void setEntAddress(String entAddress) {
        this.entAddress.set(entAddress);
    }

    public StringProperty entAddressProperty() {
        return entAddress;
    }

    public String getEntProduct() {
        return entProduct.get();
    }

    public void setEntProduct(String entProduct) {
        this.entProduct.set(entProduct);
    }

    public StringProperty entProductProperty() {
        return entProduct;
    }

    public String getEntProfit() {
        return entProfit.get();
    }

    public void setEntProfit(String entProfit) {
        this.entProfit.set(entProfit);
    }

    public StringProperty entProfitProperty() {
        return entProfit;
    }

    public String getEntImport() {
        return entImport.get();
    }

    public void setEntImport(String entImport) {
        this.entImport.set(entImport);
    }

    public StringProperty entImportProperty() {
        return entImport;
    }

    public String getEntExport() {
        return entExport.get();
    }

    public void setEntExport(String entExport) {
        this.entExport.set(entExport);
    }

    public StringProperty entExportProperty() {
        return entExport;
    }
}