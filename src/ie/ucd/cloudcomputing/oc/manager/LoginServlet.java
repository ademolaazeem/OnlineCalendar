package ie.ucd.cloudcomputing.oc.manager;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  

  
public class LoginServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
          
        String n=request.getParameter("email");    
        String p=request.getParameter("password");   
          
        HttpSession session = request.getSession(false);  
        if(session!=null)  
        session.setAttribute("name", n);  
  
        if(LoginDAO.validate(n, p)){    
            RequestDispatcher rd=request.getRequestDispatcher("online_calendar.jsp");    
            rd.forward(request,response);    
        }    
        else{  
        	request.setAttribute("errorMessage", "<p style=\"color:red\">Sorry username or password error</p>");
            //out.print("<p style=\"color:red\">Sorry username or password error</p>");    
            RequestDispatcher rd=request.getRequestDispatcher("login.jsp");    
            rd.include(request,response);    
        }    
  
        out.close();    
    }    
}   