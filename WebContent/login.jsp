<%@ page language="java" contentType="text/html; charset=iso-8859-1"  
    pageEncoding="ISO-8859-1"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">  
<title>::.Login into Online Calendar.::</title>
<link rel="shortcut icon" href="./codebase/imgs/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="./codebase/css/style.css" />

</head>  
<body>  

<form action="loginServlet" method="post">  
	<fieldset>
		<br />
	<legend>Log in</legend>
<div align = center style="color: #ff3333; font-size: small;"><b>${errorMessage}</b><br /></div>
	
	 <input type = "hidden" name="userType" value="Admin" />
	
          <label for="login">Email</label>
          <input type="text" name="email" required="required"  size="30" class="mO" />
			<!-- <input type="text" id="login" name="uname" size="30" class="mO"/>	 -->
		  <div class="clear"></div>
			<br/>
			<label for="password">Password</label>
			<input type="password" name="password" required="required" id="password" class="mO" size="40" />
			
<table width="350" border="0" align="right">
  <!--<tr>
    <td width="294" align="right">			<font size="-2"><?php echo $msg; ?></font></td>
    <td width="46">&nbsp;</td>
  </tr>-->
</table>

            <div class="clear"></div>
			
		  <label for="remember_me" style="padding: 0;">Remember me?</label>
			<input type="checkbox" id="remember_me" style="position: relative; top: 3px; margin: 0; " name="remember_me"/>
			<div class="clear"></div>
			
			<br />
			
			<input type="submit" style="margin: -20px 0 0 287px;" class="button" name="btnlogin" value="Log in"/>
            <br/>
            <div><a href="#" target="_blank">Forgot your Password?</a>  |  <a href="index.html">Back to Home Page</a> </div>
		</fieldset>
    </form> 
    <div class="copy">Developed by Ademola Waheed Kazeem. All Right Reserved &copy;<fmt:formatDate value="${date}" pattern="yyyy" /></div>
    

</body>  
</html> 
</html>