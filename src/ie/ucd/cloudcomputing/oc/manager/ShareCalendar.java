package ie.ucd.cloudcomputing.oc.manager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShareCalendar extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		String idTemp = request.getParameter("id");
		int id = Integer.parseInt(idTemp);
		request.setAttribute("getCalendarById", DataAccess.getCalendarById(id));
		RequestDispatcher rd = request.getRequestDispatcher("share_calendar.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException ex) {
			Logger.getLogger(ShareCalendar.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
