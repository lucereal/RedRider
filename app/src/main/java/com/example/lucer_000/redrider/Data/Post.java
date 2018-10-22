package com.example.lucer_000.redrider.Data;

abstract public class Post {

    private String destination;
    private String date;
    private String time;

    Post(String dest, String date){
        this.destination = dest;
        this.date = date;
    }

    public String getDestination(){
        return this.destination;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }
    public String getDate(){
        return this.destination;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getTime(){
        return this.destination;
    }
    public void setTime(String time){
        this.time = time;
    }

}

