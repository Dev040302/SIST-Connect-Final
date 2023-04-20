package com.example.food_order.Data;

public class order {

    public  String name;
    public Integer qnty;
    public order()
    {

    }
    public order(String name,Integer qnty)
    {
        this.name=name;
        this.qnty=qnty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQnty() {
        return qnty;
    }

    public void setQnty(Integer qnty) {
        this.qnty = qnty;
    }

}
