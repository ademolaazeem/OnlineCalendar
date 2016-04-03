<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="ie.ucd.cloudcomputing.oc.manager.DataAccess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <%
            String idTemp = request.getParameter("id");
            int id = Integer.parseInt(idTemp);
            String eventName = request.getParameter("eventName");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String email = request.getParameter("sharedEmail");
            
            System.out.println("id: "+ id + ", eventName: " + eventName + ", startDate: " + startDate + 
					", endDate: " + endDate + ", email: " + email);        
            DataAccess da = new DataAccess();
            da.shareNow(id, eventName, startDate, endDate, email);
            
            response.sendRedirect("/OnlineCalendar/Search");
        %>
</body>
</html>