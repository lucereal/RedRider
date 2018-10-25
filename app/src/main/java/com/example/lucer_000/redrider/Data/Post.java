package com.example.lucer_000.redrider.Data;

abstract public class Post {
    String date;

    public Post(){}
    public Post(String date){
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        date = date;
    }
}