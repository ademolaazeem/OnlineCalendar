<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="./codebase/imgs/favicon.ico" type="image/x-icon" />
<title>::.Online Calendar | Share Event Page.::</title>
<style type="text/css">
body,td,th {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
}

body {
	/* background-color: #0389C9; */
	text-align: center;
	margin: 9px;
	margin: 80px 80px 100px 100px;
}

div#fixedheader {
	position: fixed;
	top: 0px;
	left: 0px;
	width: 100%;
	color: #CCC;
	background: #16396F;
	padding: 1px;
	height: 70px;
}

div#fixedfooter {
	position: fixed;
	bottom: 0px;
	left: 0px;
	width: 100%;
	color: #CCC;
	background: #16396F;
	padding: 8px;
}

div#righhead {
	margin-top: -100px;
	margin-bottom: 20px;
	margin-left: 800px;
}

a:visited {
	color: blue;
}
</style>
</head>
<body>
	<div id="fixedheader">
		<div align="left">
			<img src="codebase/imgs/logo.png" width="332" height="89">
		</div>
		<div id="righhead">
			<h4>
				Hello,
				<%=session.getAttribute("name")%>
				| <a href='logout.jsp'>[Log out]</a>
			</h4>
		</div>
	</div>
	<div>
		<h1>Share Calendar</h1>
	</div>
	<div style="width: 900px; margin-left: auto; margin-right: auto">
		<c:forEach items="${getCalendarById}" var="p">

			<form action="manage_share_calendar.jsp" method="post">

				<table align="center">
				<tr>
				<td colspan=2 align="left" style="background-color:lightgrey">
                    <b><a href="online_calendar.jsp">Go Back</a> </b></td>
				</tr>
					<tr>
						<td colspan="2"><input type="hidden" name="id"
							value="${p.id}"> <%String selectedLink = request.getParameter("id"); %>
						</td>
					</tr>
					<tr>
						<td>Event name:</td>
						<td><input type="text" value="${p.eventName}"
							name="eventName" style="width: 300px" readonly></td>
					</tr>
					<tr>
						<td>Start Date:</td>
						<td><input type="text" name="startDate"
							value="${p.startDate}" style="width: 300px" readonly></td>
					</tr>
					<tr>
						<td>End Date:</td>
						<td><input type="text" name="endDate" value="${p.endDate}"
							style="width: 300px" readonly></td>
					</tr>
					<tr>
						<td>Shared Email:</td>
						<td><input type="text" name="sharedEmail"
							style="width: 300px" ></td>
					</tr>
					<tr>
						<td colspan="2"> <input type="submit" value="Submit"></td>
					</tr>
					<tr>
				<td colspan=2 align="left" style="background-color:lightgrey">
                    &nbsp;</td>
				</tr>

				</table>

			</form>
		</c:forEach>
		<!--Complete Interface Addnew.-->
	</div>
</body>
</html>