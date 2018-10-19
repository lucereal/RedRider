package com.example.lucer_000.redrider.Data;

public class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Profile testing = new Profile();
		testing.setAge(15);
		testing.setEmail("wall@ttu.edu");
		testing.setMajor("Computer thing");
		testing.setName("Guy");
		testing.setSex("Male");
		testing.setPassword("Password");
		
		try {
		DBmanager tester=new DBmanager();
		System.out.println(tester.signup(testing));
		}catch(Exception message) {
			System.out.println(message);
		}
		
	}

}
