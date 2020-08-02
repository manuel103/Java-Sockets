package sample.Model;

import java.io.Serializable;

public class ToyMerchant implements Serializable {
    private String Code;
    private String Name;
    private String Description;
    private int Price;
    private String DOM;
    private String BatchNumber;

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    private String customMessage;
    private Manufacturer manufacturer;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getDOM() {
        return DOM;
    }

    public void setDOM(String DOM) {
        this.DOM = DOM;
    }

    public String getBatchNumber() {
        return BatchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        BatchNumber = batchNumber;
    }

    public Manufacturer getToyManufacturer() {
        return manufacturer;
    }

    public void setToyManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
