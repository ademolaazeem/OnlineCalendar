package ie.ucd.cloudcomputing.oc.manager;

import ie.ucd.cloudcomputing.oc.database.DatabaseConnections;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();

		String dEmail = request.getSession().getAttribute("name").toString();
		System.out.println("The value of email dEmail is: " + dEmail);

		try {
			Connection  conn = DatabaseConnections.getDBConnection();
			Statement connectionStatement = conn.createStatement();
			System.out.println("Connected!");
			String eventName = request.getParameter("eventName");
			String startDate = request.getParameter("start_date");
			String endDate = request.getParameter("end_date");
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.ENGLISH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);



			ArrayList dataList = null;
			ArrayList presList = new ArrayList();
			System.out.println("EventName: " + eventName + ", startDate: " +
					startDate + ", endDate: " + endDate);

			/*String query = "select * from events where event_name like '%" + eventName + "%'"
				+ " or start_date='" + startDate + "' or end_date='" + endDate +"'";
			 */
			String query = "";

			if(eventName == null || startDate == null || endDate == null)
			{				
				query = "select * from events where email='" + dEmail + "'";
				request.setAttribute("infoMessage", "<p style=\"color:green\">Calendar Event Successfully shared!</p>");

			}
			else if(eventName.equals("") && startDate.equals("") && endDate.equals(""))
			{				
				query = "select * from events where start_date=" + null + " and email='" + dEmail + "'";

			}
			else if(!eventName.equals("") && startDate.equals("") && endDate.equals(""))
			{
				query = "select * from events where event_name like '%" + eventName + "%' and email='" + dEmail + "'";
			}
			else if(!eventName.equals("") && !startDate.equals("") && endDate.equals(""))
			{
				query = "select * from events where event_name like '%" + eventName
						+ "%' and start_date like '" + startDate + "%' and email='" + dEmail + "'";
			}
			else if(!eventName.equals("") && !startDate.equals("") && !endDate.equals(""))
			{
				//query = "select * from events where event_name like '%" + eventName + "%'"
				//		+ " and start_date between CAST('"+ startDate + "' AS DATE)" + " and CAST('" + endDate + "' AS DATE)";

				query = "select * from events where event_name like '%" + eventName + "%'"
						+ " and start_date >= CAST('"+ startDate + "' AS DATE)" + " and start_date<= CAST('" + endDate + "' AS DATE) and email='" + dEmail + "'";
			}
			else if(eventName.equals("") && !startDate.equals("") && !endDate.equals(""))
			{
				//query = "select * from events where start_date >=" + startDate + " and start_date <=" + endDate +"";
				//query = "select * from events where start_date between " + startDate + " and " + endDate +"";
				//query = "select * from events where start_date between  + DATE_FORMAT("+startDate + ", '%Y-%m-%d')" + " and DATE_FORMAT(" + endDate + ", '%Y-%m-%d')";
				query = "select * from events where start_date between CAST('"+ startDate + "' AS DATE)" + " and CAST('" + endDate + "' AS DATE) and email='" + dEmail + "'";
			}
			else if(!eventName.equals("") && startDate.equals("") && !endDate.equals(""))
			{
				query = "select * from events where event_name like '%" + eventName + "%'"
						+ "' and end_date like '" + endDate +"%' and email='" + dEmail + "'";
			}
			else if(eventName.equals("") && startDate.equals("") && !endDate.equals(""))
			{
				query = "select * from events where end_date like '" + endDate +"%' and email='" + dEmail + "'";
			}
			else if(eventName.equals("") && !startDate.equals("") && endDate.equals(""))
			{
				//Date sdfStartDate = new java.sql.Date(sdf.parse(startDate).getTime());
				//java.util.Date sdfStartDate = sdf.parse(startDate);
				System.out.println("sdfStartDate: " + startDate);
				query = "select * from events where start_date like '" + startDate +"%' and email='" + dEmail + "'";
				//query = "select * from events where start_date =" + sdfStartDate +"";
			}

			System.out.println("query " + query);
			connectionStatement = conn.createStatement();
			ResultSet rs = connectionStatement.executeQuery(query);

			while (rs.next()) {
				dataList = new ArrayList();

				//out.println(rs.getString(1));
				//out.println(rs.getString(2));
				//out.println(rs.getString(3));
				//out.println(rs.getString(4));
				dataList.add(rs.getInt(1));
				dataList.add(rs.getString(2));
				dataList.add(rs.getString(3));
				dataList.add(rs.getString(4));


				System.out.println("al :: " + dataList);
				presList.add(dataList);
			}

			request.setAttribute("piList", presList);
			RequestDispatcher view = request.getRequestDispatcher("/search_calendar.jsp");
			view.forward(request, response);
			conn.close();
			System.out.println("Disconnected!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);

	}


	/** 
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}