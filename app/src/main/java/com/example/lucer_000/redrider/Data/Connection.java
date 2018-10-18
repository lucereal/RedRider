package com.example.lucer_000.redrider.Data;

import java.sql.*;

public class Connection {

    //public static void main(String[] args) {


    public void method(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://75.111.163.91:3306/codemalone", "general", "password");
            System.out.print("worked\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cities");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  ");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
//}