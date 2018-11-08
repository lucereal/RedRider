package com.example.lucer_000.redrider.Data;

public class Rider extends Post{


	int riderId;
	String time;

	public Rider(){}
	//dest,date, 1234, time
	public Rider(String destination, String date, int riderId, String time){
		this.date = date;
		this.destination = destination;
		this.riderId = riderId;
		this.time = time;
	}


	public int getRiderId() {
		return riderId;
	}
	public void setRiderId(int riderId) {
		this.riderId = riderId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
