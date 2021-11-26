package com.istiaq66.bonik;

public class inventModel {
    String productname,description,unit;
    int quantity,per_unit_price;

    public inventModel(String productname, String description, int quantity,String unit, int per_unit_price) {
        this.productname = productname;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.per_unit_price = per_unit_price;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPer_unit_price() {
        return per_unit_price;
    }

    public void setPer_unit_price(int per_unit_price) {
        this.per_unit_price = per_unit_price;
    }
}
