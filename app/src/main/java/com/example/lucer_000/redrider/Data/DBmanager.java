package com.example.lucer_000.redrider.Data;

import java.sql.*;


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
	//If a profile could not be created thros exception "Could not be created"
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
	
}
