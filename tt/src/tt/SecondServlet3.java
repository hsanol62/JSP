package tt;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

@WebServlet("/second3") 
public class SecondServlet3 extends HttpServlet { 
	protected void doGet(HttpServletRequest request, HttpServletResponse 
			response) 
					throws ServletException, IOException {  

response.setContentType("text/html;charset=utf-8"); 
PrintWriter out = response.getWriter();  
out.println("<html><body>"); 
out.println("location를 이용한 redirect 실습이다."); 
out.println("</body></html>"); 
}  
}