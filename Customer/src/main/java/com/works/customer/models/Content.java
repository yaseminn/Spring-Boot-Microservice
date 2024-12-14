package com.works.customer.models;

public class Content {
    private long pid;
    private String title;
    private String description;
    private long price;
    public long getPID() { return pid; }
    public void setPID(long value) { this.pid = value; }
    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }
    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }
    public long getPrice() { return price; }
    public void setPrice(long value) { this.price = value; }
}