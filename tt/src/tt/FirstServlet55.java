package tt;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/66") 
public class FirstServlet55 extends HttpServlet { 
	private static final long serialVersionUID = 1L;           
	public FirstServlet55() {    
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
request.setCharacterEncoding("utf-8"); 
response.setContentType("text/html;charset=utf-8"); 
request.setAttribute("address", "����� ���Ǳ� ���μ�ȯ�� 1811"); 
// response.sendRedirect("SecondServlet55"); 
//sendRedirect�� ���� ���ϱ⿡ ����ġ�� ������� 
RequestDispatcher dispatch1 = request.getRequestDispatcher("55"); 
dispatch1.forward(request, response); }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }
}
