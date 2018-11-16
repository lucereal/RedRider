package com.example.lucer_000.redrider.Data;

public class MatchPost extends Post{

    private String destination;
    private String date;
    private int tripID;
    private Driver driver;
    private Rider rider;
    private Profile profile;

    public MatchPost(){
        driver = null;
        rider = null;

    }
    public void setTripID(int tripid){
        tripID = tripid;
    }
    public int getTripID(){
        return this.tripID;
    }
    public void setDestination(String dest){
        destination = dest;
    }
    public String getDestination(){
        return this.destination;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setDriver(Driver driver){
        this.driver = driver;
    }
    public Driver getDriver(){
        return this.driver;
    }
    public void setRider(Rider rider){
        this.rider = rider;
    }
    public Rider getRider(){
        return this.rider;
    }
    public void setProfile(Profile profile){
        this.profile = profile;
    }
    public Profile getProfile(){
        return this.profile;
    }
}
