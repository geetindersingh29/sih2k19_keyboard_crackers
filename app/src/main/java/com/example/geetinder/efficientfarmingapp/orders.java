package com.example.geetinder.efficientfarmingapp;

public class orders {
    private String oid,bid,sid,pid,datestart,dateend;
    private int amount,buyerrating,sellerrating,productrating,orderstatus;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(int orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDatestart() {
        return datestart;
    }

    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }

    public String getDateend() {
        return dateend;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBuyerrating() {
        return buyerrating;
    }

    public void setBuyerrating(int buyerrating) {
        this.buyerrating = buyerrating;
    }

    public int getSellerrating() {
        return sellerrating;
    }

    public void setSellerrating(int sellerrating) {
        this.sellerrating = sellerrating;
    }

    public int getProductrating() {
        return productrating;
    }

    public void setProductrating(int productrating) {
        this.productrating = productrating;
    }
}
