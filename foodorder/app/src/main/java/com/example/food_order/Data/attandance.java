package com.example.food_order.Data;

public class attandance {

    // variables for our first name,
    // last name, email and avatar
    private String name;
    private String registerno;

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getregisterno() {
        return registerno;
    }

    public void setregisterno(String registerno) {
        this.registerno = registerno;
    }


    public attandance(String name, String registerno) {
        this.name = name;
        this.registerno = registerno;
    }
}
