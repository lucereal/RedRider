package com.example.lucer_000.redrider.Data;



public class Driver extends Post{
	String date;
	String destination;
	int driverId;
	int seats;
	String time;
	String vehicle;

	public Driver(){}
	//dest,date,1234,1,time,vehicle
	public Driver(String dest, String date, int driverId, int seats, String time, String vehicle){
	    super(date);

		destination = dest;
		this.driverId = driverId;
		this.seats = seats;
		this.time = time;
		this.vehicle = vehicle;
	}


	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
}
