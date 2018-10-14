import java.sql.*;

public class Connection {

	public static void main(String[] args) {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://75.111.163.91:3306/codemalone","general","password");  
			//here sonoo is database name, root is username and password   
			System.out.print("worked");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from cities");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
		}catch(Exception e){ System.out.println(e);}  
			}  
	}