package ie.ucd.cloudcomputing.oc.manager;
import ie.ucd.cloudcomputing.oc.database.DatabaseConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;

public class EventsManager extends DHXEventsManager{


	//private String dEmail;

	public EventsManager(HttpServletRequest request) {
		super(request);
	}

	public Iterable getEvents() {
		DHXEventsManager.date_format = "yyyy-MM-dd HH:mm:ss";
		List evs = new ArrayList();
		try {
			Connection       conn = DatabaseConnections.getDBConnection();
			Statement connectionStatement = conn.createStatement();
			String eemail = getEmailSession(getRequest());
			String query = "SELECT event_id, event_name, start_date, end_date FROM events where email ='"+ eemail + "'";
			ResultSet rsltSet = connectionStatement.executeQuery(query);

			while (rsltSet.next()) {
				DHXEvent event = new DHXEvent();
				event.setId(Integer.parseInt(rsltSet.getString("event_id")));
				event.setText(rsltSet.getString("event_name"));
				event.setStart_date(rsltSet.getString("start_date"));
				event.setEnd_date(rsltSet.getString("end_date"));
				evs.add(event);
			}
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DHXEventsManager.date_format = "MM/dd/yyyy HH:mm";

		return evs;
	}

	
	public String getEmailSession(HttpServletRequest request){
		String dEmail = request.getSession().getAttribute("name").toString();
        System.out.println("The value of email dEmail is: " + dEmail);
		
        return dEmail;
	}
	
	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus sqlStatus) {
		Connection conn = DatabaseConnections.getDBConnection();
		PreparedStatement ps = null;
		ResultSet result = null;
	      
		try {
			String eemail = getEmailSession(getRequest());
					
			String generalQuery = null;
			String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(event.getStart_date());
			String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(event.getEnd_date());
			if (sqlStatus == DHXStatus.UPDATE) {
				System.out.println("We are in update and the event name is: " + event.getText());
				generalQuery = "UPDATE events SET event_name=?, start_date=?, end_date=?, email=? WHERE event_id=?";
				ps = conn.prepareStatement(generalQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, event.getText());
				ps.setString(2, startDate);
				ps.setString(3, endDate);
				ps.setString(4, eemail);
				//ps.setInt(4, event.getId());
				ps.setInt(5, event.getId());
				

			} else if (sqlStatus == DHXStatus.INSERT) {
				generalQuery = "INSERT INTO events (event_id, event_name, start_date, end_date, email) VALUES (null, ?, ?, ?, ?)";
				ps = conn.prepareStatement(generalQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, event.getText());
				ps.setString(2, startDate);
				ps.setString(3, endDate);
				ps.setString(4, eemail);

			} else if (sqlStatus == DHXStatus.DELETE) {
				generalQuery = "DELETE FROM events WHERE event_id=? LIMIT 1";
				ps = conn.prepareStatement(generalQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, event.getId());
			}

			if (ps!=null) {
				ps.executeUpdate();
				result = ps.getGeneratedKeys();
				if (result.next()) {
					event.setId(result.getInt(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) try { result.close(); } catch (SQLException e) {}
			if (ps != null) try { ps.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}

		return sqlStatus;
	}

	@Override
	public DHXEv createEvent(String id, DHXStatus status) {
		return new DHXEvent();
	}

	
}