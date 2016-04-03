<%@ page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>::.Online Calendar | Search for Event.::</title>
<link rel="shortcut icon" href="./codebase/imgs/favicon.ico"
	type="image/x-icon" />

<style type="text/css">
body,td,th {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
}

body {
	/* background-color: #0389C9; */
	text-align: center;
	color: #fff;
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
	height:70px;
	
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
	margin-top:-100px;
	margin-bottom:20px;
	margin-left:800px;
	
	
	
	
}
a:visited { 
    color: blue;
}
</style>











</head>
<body>
	<div id="fixedheader">
		<div align="left"><img src="codebase/imgs/logo.png" width="332" height="89"></div>
<div id="righhead"><h4>Hello, <%=session.getAttribute("name")%> | 
<a href='logout.jsp'>[Log out]</a></h4></div>
</div>
<!-- 	<br />
	<br />
	<br />
	<br />
	<br />
	<br /> -->

<table width="1000px" align="center" style="border:1px solid #000000;">
               
               <tr>
                <td colspan=4 align="left"
                    style="background-color:lightgrey">
                    <b><a href="online_calendar.jsp">Go Back</a> </b></td>
            </tr>
            
            <tr>
            <!-- color: #ff3333;  -->
                <td colspan=4 align="center"
                    style="background-color:#16396F">
                    <b><div align = center style="font-size: small;"><b>${infoMessage}</b></div> </b></td>
            </tr>
            
            
            <tr>
                <td colspan=4 align="center"
                    style="background-color:#16396F">
                    <b>Calendar Event</b></td>
            </tr>
            <tr style="background-color:teal;">
                <td><b>Event Name</b></td>
                <td><b>Start Date</b></td>
                <td><b>End Date</b></td>
                <td><b>Action</b></td>
            </tr> 
			<%
				int count = 0;
					//String color = "#F9EBB3";
					String color = "#16396F";

					if (request.getAttribute("piList") != null) {
						ArrayList al = (ArrayList) request.getAttribute("piList");
						System.out.println(al);
						Iterator itr = al.iterator();
						while (itr.hasNext()) {

							if ((count % 2) == 0) {
								//color = "#eeffee";
								color = "#84AAEB";
							}
							count++;
							ArrayList pList = (ArrayList) itr.next();
			%>
			<tr style="background-color:<%=color%>;">
				<%
					request.setAttribute("getIdOfZero", pList.get(0));
				%>
				<td><%=pList.get(1)%></td>
				<td><%=pList.get(2)%></td>
				<td><%=pList.get(3)%></td>

				<td><a href="shareNow?&id=<%=pList.get(0)%>"><img
						src="codebase/imgs/shareLogo.png" alt="Share this Calendar"
						width="40px" height="31px"></a></td>
				

			</tr>
			<%
				}
					}
					if (count == 0) {
			%>
			<tr>
				<td colspan=4 align="center" style="background-color: #84AAEB"><b>No
						Record Found...</b></td>
			</tr>
			<%
				}
			%>
		
	</table>



</body>
</html>