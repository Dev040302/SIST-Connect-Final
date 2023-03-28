package com.example.fyp.Data;

public class feeds {

    public  String regno;
    public String imgurl;
    public String content;
    public Integer likes;
    
    public feeds()
    {

    }
    public feeds(String regno, String imgurl, String content, Integer likes)
    {
        this.regno=regno;
        this.imgurl=imgurl;
        this.content=content;
        this.likes=likes;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getLikes() {
        return likes;
    }
}
