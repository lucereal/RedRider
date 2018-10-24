package com.example.lucer_000.redrider.Data;

import java.sql.*;
import com.example.lucer_000.redrider.Data.Driver;
import com.example.lucer_000.redrider.Data.Rider;


public class DBmanager {
	ConnectionMaker forcreation = new ConnectionMaker();
	//java.sql.Connection temp=test.MakeConnection();
	
	
	//Used to login to the software an move onto the screen
	//The users email and password needs to be passed
	//The return will be the users profile
	//If the profile could not be found a string will be thrown stating "Profile not found"
	Profile login(String email,String password) throws IllegalArgumentException {
		java.sql.Connection con = forcreation.MakeConnection();
		Profile foundaccount = new Profile();
		boolean found=false;
		try {
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from profile");	
		while(rs.next()) {
			if (rs.getString("Email").equals(email) && rs.getString("Password").equals(password)) {
				foundaccount.idProfile=rs.getInt("idProfile");
				foundaccount.name=rs.getString("Name");
				foundaccount.major=rs.getString("Major");
				foundaccount.age=rs.getInt("Age");
				foundaccount.email=rs.getString("Email");
				foundaccount.rating=rs.getInt("Rating");
				found=true;
			}
		}
		rs.close();
		stmt.close();
		con.close();
		} catch (Exception e) {
            System.out.println(e);
    		try {
				con.close();
			} catch (SQLException e1) {
				System.out.println("Close does not work");
				e1.printStackTrace();
			}
		}
		
		if (found)
			return foundaccount;
		
		throw new IllegalArgumentException("Profile not found");
	}
	
	//checks weather profile exists
	private boolean profileexists(String email) throws IllegalArgumentException {
		java.sql.Connection con = forcreation.MakeConnection();
		try {
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from profile");	
		while(rs.next())
			if(rs.getString("Email").equals(email)) {
			return true;
		}
		} catch (Exception e) {
            System.out.println(e);
		}
		return false;
	}

	//Used to signup a profile
	//An object of type profile needs to be passed and filled out
	//If the profile exist a string exception is thrown stating "Profile already exists"
	//If a profile could not be created throws exception "Could not be created"
	int signup(Profile input) throws IllegalArgumentException {
		
		boolean checkforaccount= profileexists(input.email);
		if (checkforaccount)
			throw new IllegalArgumentException("Profile already exist");
		
		java.sql.Connection con = forcreation.MakeConnection();
		try {
			String query = "insert into profile( Name, Major, Age, Sex, Email, Password) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, input.name);
			stmt.setString(2, input.major);
			stmt.setInt(3, input.age);
			stmt.setString(4, input.sex);
			stmt.setString(5, input.email);
			stmt.setString(6, input.password);
			stmt.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Profile checkifinserted= login(input.email,input.password);
			con.close();
			return checkifinserted.idProfile;
			}catch(Exception message) {
				throw new IllegalArgumentException("Could not be created");
			}
		
	}
	
	
	int makepost(Driver input) {
		String query="insert into driverpost(DriverID, Vehicle, DestinationID, Time, Date, Seats) values(?,?,?,?,?,?)";
		java.sql.Connection con = forcreation.MakeConnection();
		int postID=getdrivepostID(input);
		
		if(postID > 0)
			return postID;
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, input.driverId);
			stmt.setString(2, input.vehicle);
			stmt.setString(3, input.destination);
			stmt.setString(4, input.time);
			stmt.setString(5, input.date);
			stmt.setInt(6,input.seats);
			stmt.execute();
			//System.out.println("exe");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		postID=getdrivepostID(input);
		
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("makedrivepost failed to close connection");
			e.printStackTrace();
		}
		
		return postID;
	}
	
	
	
	
	
	int makepost(Rider input) throws IllegalArgumentException {
		String query="insert into riderpost(RiderID, DestinationID, Time, Date) values(?,?,?,?)";
		java.sql.Connection con = forcreation.MakeConnection();
		
		try {
			Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("select * from driverpost");
	        while(rs.next()) {
	        	if(rs.getInt("RiderID")==input.riderId && rs.getString("DestinationID").equals(input.destination) && rs.getString("Date").equals(input.date)){
	        		con.close();
	        		throw new IllegalArgumentException("Post Already Exists");
	        		}
	        }
		}catch(Exception e) {
			
		}
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, input.riderId);
			stmt.setString(2, input.destination);
			stmt.setString(3, input.time);
			stmt.setString(4, input.date);
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("makedrivepost failed to close connection");
			e.printStackTrace();
		}
		
		return 1;
	}
	
	
private int getdrivepostID(Driver input) {
	java.sql.Connection con = forcreation.MakeConnection();
	
	try {
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from driverpost");
        while(rs.next()) {
        	if(rs.getInt("DriverID")==input.driverId && rs.getString("Date").equals(input.date)){
        		return rs.getInt("TripID");
        	}
        }
	}catch(Exception e) {
		
	}
	try {
		con.close();
	} catch (SQLException e) {
		System.out.println("getDrivepostID failed to close connection");
		e.printStackTrace();
	}
	return -1;
}




}
