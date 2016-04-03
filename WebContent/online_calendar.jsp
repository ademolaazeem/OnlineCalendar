<html>
 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>::.Online Calendar | Main Calendar Event Page.::</title>
    <link rel="shortcut icon" href="./codebase/imgs/favicon.ico" type="image/x-icon" />

<link rel="stylesheet" href="./codebase/jquery/runnable.css" />
<link rel="stylesheet" href="./codebase/jquery/smoothness/jquery-ui.css">
<script src="./codebase/jquery/jquery-1.10.2.js"></script>
<script src="./codebase/jquery/jquery-ui.js"></script>
<script src="./codebase/jquery/script.js"></script>



<style type="text/css">
body,td,th {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
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
<br />
	<br />
	<br />
<div>
<form method="post" name="frm" action="Search">
<div><h1>Online Calendar</h1></div>
      <table border="0" width="240" align="left">
      <tr><td colspan=2 align="center">
        <h3>Search Calendar</h3></td></tr>
         <tr><td ><b>Event:</b></td>
          <td align="right"><input  type="text" name="eventName" id="eventName"></td><td>&nbsp;</td>
        </tr> 
        <tr><td ><b>Date:</b></td>
          <td align="right"> <p><input type="text" id="start_date" name="start_date"></p>
          </td><td>&nbsp;</td>
        </tr>  
        
        <tr><td ><b>To:</b></td>
          <td align="right"><input type="text" id="end_date" name="end_date"></td><td>&nbsp;</td>
        </tr> 
          <tr>
        <td>&nbsp;</td>
        <td align="right">
        <input  type="submit" name="submit" value="Search"></td><td>&nbsp;</td>
        </tr>
        <tr>
        <td>&nbsp;</td>
        <td align="right">
        </td><td>&nbsp;</td>
        </tr>
               
        <tr>
        <td>&nbsp;</td>
        <td align="right"><a href='logout.jsp'>Log out</a></a> 
        </td><td>&nbsp;</td>
        </tr>
       </table>
      
    </form>
</div>
<div> </div>


<!--<div><h4>Hello, <%=session.getAttribute("name")%>  | 
<a href='logout.jsp'>Log out</a> 
</h4>

</div>-->

	<div class="planner" id="planner"><%=getPlanner(request)%></div>
	<%@ page import="com.dhtmlx.planner.*,com.dhtmlx.planner.data.*"%>
	<%!String getPlanner(HttpServletRequest request) throws Exception {
		DHXPlanner dhxPlanner = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
		dhxPlanner.setWidth(950);
		dhxPlanner.setInitialDate(2015, 0, 23);
		dhxPlanner.load("events.jsp", DHXDataFormat.JSON);
		dhxPlanner.data.dataprocessor.setURL("events.jsp");
		return dhxPlanner.render();
	}%>
	
</body>
</html>