package ie.ucd.cloudcomputing.oc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnections {
	public static Connection getDBConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/online_calendar";
			String user = "oc_user";
			String password = "oc_password";
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return connection;


	}
	
	/*
	public static Connection getDBConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://us-cdbr-azure-west-c.cloudapp.net:3306/online_calendar";
			String user = "b9a352cc110b55";
			String password = "264591ae";
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return connection;


	}
	*/

}
