package com.example.lucer_000.redrider.Data;

public class DriverPost extends Post {

    private String compensation;
    private Integer numSeats;

    public DriverPost(String dest, String date, String comp, Integer seats){
        super(dest,date);
        this.compensation = comp;
        this.numSeats = seats;
    }

    public void setCompensation(String compensation){
        this.compensation = compensation;
    }
    public String getCompensation(){
        return this.compensation;
    }
    public void setNumSeats(Integer numSeats){
        this.numSeats = numSeats;
    }
    public Integer getNumSeats(){
        return this.numSeats;
    }
}
