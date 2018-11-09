package com.example.lucer_000.redrider.Data;

import java.sql.*;
import java.util.*;

public class DBmanager {
	ConnectionMaker forcreation = new ConnectionMaker();

	// Used to login to the software an move onto the screen
	// The users email and password needs to be passed
	// The return will be the users profile
	// If the profile could not be found a string will be thrown stating "Profile
	// not found"
	public Profile login(String email, String password) throws IllegalArgumentException {
		java.sql.Connection con = forcreation.MakeConnection();
		Profile foundaccount = new Profile();
		boolean found = false;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from profile");
			while (rs.next()) {
				if (rs.getString("Email").equals(email) && rs.getString("Password").equals(password)) {
					foundaccount.idProfile = rs.getInt("idProfile");
					foundaccount.name = rs.getString("Name");
					foundaccount.major = rs.getString("Major");
					foundaccount.age = rs.getInt("Age");
					foundaccount.email = rs.getString("Email");
					foundaccount.rating = rs.getInt("Rating");
					found = true;
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

	// checks weather profile exists
	private boolean profileexists(String email) throws IllegalArgumentException {
		java.sql.Connection con = forcreation.MakeConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from profile");
			while (rs.next())
				if (rs.getString("Email").equals(email)) {
					return true;
				}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	// Used to signup a profile
	// An object of type profile needs to be passed and filled out
	// If the profile exist a string exception is thrown stating "Profile already
	// exists"
	// If a profile could not be created throws exception "Could not be created"
	int signup(Profile input) throws IllegalArgumentException {

		boolean checkforaccount = profileexists(input.email);
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
			Profile checkifinserted = login(input.email, input.password);
			con.close();
			return checkifinserted.idProfile;
		} catch (Exception message) {
			throw new IllegalArgumentException("Could not be created");
		}

	}

	// **Overloaded**
	// Used to create a drivers post and push to database
	// Value of type driver needs to be sent
	// The method will check if the driver post already exists and will throw "Post
	// Already Exists" if it does
	// If the post does not exists already then it will add to database then return
	// the postID
	int makepost(Driver input) throws IllegalArgumentException {
		String query = "insert into driverpost(DriverID, Post, DestinationID, Time, Date, Seats) values(?,?,?,?,?,?)";
		java.sql.Connection con = forcreation.MakeConnection();
		int postID = getdrivepostID(input);

		if (postID > 0)
			throw new IllegalArgumentException("Post Already Exists");
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, input.driverId);
			stmt.setString(2, input.vehicle);
			stmt.setString(3, input.destination);
			stmt.setString(4, input.time);
			stmt.setString(5, input.getDate());
			stmt.setInt(6, input.seats);
			stmt.execute();
			// System.out.println("exe");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		postID = getdrivepostID(input);

		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("makedrivepost failed to close connection");
			e.printStackTrace();
		}

		return postID;
	}

	// **Overloaded**
	// Used to create a drivers post and push to database
	// Value of type driver needs to be sent
	// The method will check if the driver post already exists and if it does it
	// will return the postID
	// If the post does not exists already then it will add to database then return
	// the postID

	// **Overloaded**
	// Used to create a drivers post and push to database
	// Value of type driver needs to be sent
	// The method will check if the driver post already exists and if it does it
	// will return the postID
	// If the post does not exists already then it will add to database then return
	// the postID


	int makepost(Rider input) throws IllegalArgumentException {
		String query = "insert into riderpost(RiderID, DestinationID, Time, Date) values(?,?,?,?)";
		java.sql.Connection con = forcreation.MakeConnection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from driverpost");
			while (rs.next()) {
				if (rs.getInt("RiderID") == input.riderId && rs.getString("DestinationID").equals(input.destination)
						&& rs.getString("Date").equals(input.date)) {
					con.close();
					throw new IllegalArgumentException("Post Already Exists");
				}
			}
		} catch (Exception e) {

		}
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, input.riderId);
			stmt.setString(2, input.destination);
			stmt.setString(3, input.time);
			stmt.setString(4, input.date);
			stmt.execute();
		} catch (SQLException e) {
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

	// Used to check if a drive post already exists and returns the id if it does
	private int getdrivepostID(Driver input) {
		java.sql.Connection con = forcreation.MakeConnection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from driverpost");
			while (rs.next()) {
				if (rs.getInt("DriverID") == input.driverId && rs.getString("Date").equals(input.getDate())) {
					return rs.getInt("TripID");
				}
			}
		} catch (Exception e) {

		}
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("getDrivepostID failed to close connection");
			e.printStackTrace();
		}
		return -1;
	}

	
	ArrayList<Post> getposts(int userID) {
		java.sql.Connection con = forcreation.MakeConnection();
		ArrayList<Post> returninglist = new ArrayList<Post>();
		String query = "select * from driverpost where DriverID=" + userID;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Driver temp=new Driver();
				temp.date=rs.getString("Date");
				temp.destination=rs.getString("DestinationID");
				temp.driverId=userID;
				temp.seats=rs.getInt("Seats");
				temp.time=rs.getString("Time");
				temp.vehicle=rs.getString("Vehicle");
				returninglist.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("get post failed");
			e.printStackTrace();
		}
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Rider temp=new Rider();
				temp.date=rs.getString("Date");
				temp.destination=rs.getString("DestinationID");
				temp.riderId=userID;
				temp.time=rs.getString("Time");
				returninglist.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("get post failed");
			e.printStackTrace();
		}
		
		query = "select * from riderpost where RiderID="+ userID;
		
		returninglist = quickSort(returninglist);
		Collections.reverse(returninglist);
		return returninglist;
	}

	// used to sort the arraylist
	private ArrayList<Post> quickSort(ArrayList<Post> list) {
		if (list.size() <= 1)
			return list; // Already sorted

		ArrayList<Post> sorted = new ArrayList<Post>();
		ArrayList<Post> lesser = new ArrayList<Post>();
		ArrayList<Post> greater = new ArrayList<Post>();
		Post pivot = list.get(list.size() - 1); // Use last Post as pivot
		for (int i = 0; i < list.size() - 1; i++) {
			// int order = list.get(i).compareTo(pivot);
			if (list.get(i).date.compareTo(pivot.date) < 0)
				lesser.add(list.get(i));
			else
				greater.add(list.get(i));
		}

		lesser = quickSort(lesser);
		greater = quickSort(greater);

		lesser.add(pivot);
		lesser.addAll(greater);
		sorted = lesser;

		return sorted;
	}

}
