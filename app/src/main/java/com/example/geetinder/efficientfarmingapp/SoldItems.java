package com.example.geetinder.efficientfarmingapp;

public class SoldItems {

private String name;
private String amount;
private String type;
private String companyname;

    public SoldItems()
    {

    }

    public SoldItems(String name, String priceperday, String type, String companyname) {
        this.name = name;
        this.amount = priceperday;
        this.type = type;
        this.companyname = companyname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceperday() {
        return amount;
    }

    public void setPriceperday(String priceperday) {
        this.amount = priceperday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
