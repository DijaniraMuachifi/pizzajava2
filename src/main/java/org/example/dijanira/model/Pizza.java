package org.example.dijanira.model;

public class Pizza {
    private String pname;
    private String categoryname;
    private Boolean vegetarian;

    // Construtores, getters e setters

    public Pizza(String pname, String categoryname, Boolean vegetarian) {
        this.pname = pname;
        this.categoryname = categoryname;
        this.vegetarian = vegetarian;

    }

    // Getters and Setters

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }


}
