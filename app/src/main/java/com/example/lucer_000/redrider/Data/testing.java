package com.example.lucer_000.redrider.Data;

public class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Driver test= new Driver();
	test.setDate("02/28/1996");
	test.setDestination("Dallas");
	test.setDriverId(1234);
	test.setSeats(4);
	test.setTime("10:30");
	test.setVehicle("Big Truck");
	
	DBmanager testing= new DBmanager();
	System.out.println(testing.makepost(test));
	
	}
}
