package tt;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

@WebServlet("/second4")
public class SecondServlet4 extends HttpServlet { 
	protected void doGet(HttpServletRequest request, HttpServletResponse 
			response)
					throws ServletException, IOException {  

response.setContentType("text/html;charset=utf-8"); 
PrintWriter out = response.getWriter();  
out.println("<html><body>");  
String name=request.getParameter("name");  
out.println("¿Ã∏ß : " + name); out.println("<br>");
out.println("</body></html>"); 
} 
}