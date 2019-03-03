package com.example.geetinder.efficientfarmingapp;

import java.util.ArrayList;

public class Item {

    private String productid;
    private String ownerid;
    private String type;
    private int deposit;
    private String Productinfo;
    private String mImageUrl;
    private int payPerDay;
    private String fromdate;
    private String todate;
    private int productstartotal;
    private int productstarcount;
    private ArrayList<String> bookdate;


    public Item() {
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getProductinfo() {
        return Productinfo;
    }

    public void setProductinfo(String productinfo) {
        Productinfo = productinfo;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public int getPayPerDay() {
        return payPerDay;
    }

    public void setPayPerDay(int payPerDay) {
        this.payPerDay = payPerDay;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public int getProductstartotal() {
        return productstartotal;
    }

    public void setProductstartotal(int productstartotal) {
        this.productstartotal = productstartotal;
    }

    public int getProductstarcount() {
        return productstarcount;
    }

    public void setProductstarcount(int productstarcount) {
        this.productstarcount = productstarcount;
    }

    public ArrayList<String> getBookdate() {
        return bookdate;
    }

    public void setBookdate(ArrayList<String> bookdate) {
        this.bookdate = bookdate;
    }
}
