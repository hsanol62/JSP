package tt;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
@WebServlet("/55") 
public class SecondServlet55 extends HttpServlet { 
	private static final long serialVersionUID = 1L;       
	public SecondServlet55() {     
		super();
}
protected void doGet(
		HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException { request.setCharacterEncoding("utf-8"); 
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter(); 
				String address = (String) request.getAttribute("address"); 
				out.println("<html><body>"); 
				out.println("�ּ� : "+address); out.println("<br>"); 
				out.println("redirect�� �̿��� ���ε� �ǽ��Դϴ�."); 
				out.println("</body></html>"); }
				
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException { }
}