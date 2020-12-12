package tt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

public class forwardServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;        
public forwardServlet() {        
	super();    
	}   

protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException { 
	response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		response.addHeader("Refresh","1;url=second2"); }  
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException { 
	doGet(request, response);
	}
}