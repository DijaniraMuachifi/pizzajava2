package org.example.dijanira.model;

public class Category {
    private String cname;
    private double price;

    public Category(String cname, double price) {
        this.cname = cname;
        this.price = price;
    }

    // Getters and Setters
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
