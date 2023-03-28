package com.example.fyp.Data;

public class items {

    public  String name;
    public Integer rate;
    public items()
    {

    }
    public items(String name,Integer rate)
    {
        this.name=name;
        this.rate=rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
    
}
