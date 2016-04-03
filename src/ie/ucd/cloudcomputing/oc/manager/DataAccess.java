package ie.ucd.cloudcomputing.oc.manager;

import ie.ucd.cloudcomputing.oc.database.DatabaseConnections;
import ie.ucd.cloudcomputing.oc.model.Calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class DataAccess {

	public static boolean validate(String eventName, String startDate, String email, HttpServletRequest request) {          
		boolean status = false;  
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		try {  

			conn = DatabaseConnections.getDBConnection();
			//query = "select * from events where event_name like '%" + eventName "%' and start_date like '" + startDate + "%' and email='" + dEmail + "'";
			pst = conn.prepareStatement("select * from events where event_name=? and start_date like ?%  and email=?");  
			pst.setString(1, eventName);
			pst.setString(2, startDate);  
			pst.setString(3, email);  
			rs = pst.executeQuery();  
			status = rs.next();  
			request.setAttribute("errorMessage", "<p style=\"color:red\">Sorry username or password error</p>");
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
	
	
	

	public void shareNow(int id, String eventName, String startDate, String endDate, String email){
		try {
			System.out.println("id: "+ id + ", eventName: " + eventName + ", startDate: " + startDate + 
					", endDate: " + endDate + ", email: " + email);
			Connection  conn = DatabaseConnections.getDBConnection();
			System.out.println("Connected!");

			/*if(!validate(eventName, startDate, email)){
				request.setAttribute("errorMessage", "<p style=\"color:red\">Sorry username or password error</p>");
			}*/

			String sql = "INSERT INTO events (event_id, event_name, start_date, end_date, email) VALUES (null, ?, ?, ?, ?)";
			PreparedStatement ps= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, eventName);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			ps.setString(4, email);
			//ps.setInt(5, id);
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
		}

	}


	public static List<Calendar> getCalendarById(int id){
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		List<Calendar> ls = new LinkedList<>();
		String sql = "select * from events where event_id = " +id;
		try {
			conn = DatabaseConnections.getDBConnection();
			rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()){
				Calendar cal = new Calendar(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				ls.add(cal);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
		}


		return ls;
	}

}
