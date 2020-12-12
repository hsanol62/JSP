package tt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

@WebServlet("/FirstServlet2") 
public class FirstServlet2 
extends HttpServlet { 
	private static final long serialVersionUID = 1L;   

protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException { 
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter(); 
	response.sendRedirect("second4?name=shin");  
	}
}