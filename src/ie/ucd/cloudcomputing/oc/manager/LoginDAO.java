package ie.ucd.cloudcomputing.oc.manager;

import ie.ucd.cloudcomputing.oc.database.DatabaseConnections;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;
  
public class LoginDAO {  
    public static boolean validate(String name, String pass) {          
        boolean status = false;  
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        /*Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "form";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "password";  */
        try {  
            /*Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);*/  
        	conn = DatabaseConnections.getDBConnection();
			//Statement connectionStatement = conn.createStatement();
  
            pst = conn.prepareStatement("select * from users where email=? and password=?");  
            pst.setString(1, name);  
            pst.setString(2, pass);  
  
            rs = pst.executeQuery();  
            status = rs.next();  
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return status;  
    }  
}  
