package tt;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

@WebServlet("/first") 
public class FirstServlet extends HttpServlet { 
private static final long serialVersionUID = 1L;  
protected void doGet(HttpServletRequest request, HttpServletResponse resp)
		throws ServletException,IOException {  
resp.setContentType("text/html");  
PrintWriter out=resp.getWriter(); 
resp.sendRedirect("second");  
}
}
//�ٸ� �������� second�� ���û�Ѵ�.  
//   
//refresh�� �̿��� ������ ���� redirect ó�� �� �������� ���ļ� ��û�� ���� Ŭ���̾�Ʈ�� �� ���������� ù ��° ������ ��û ù ��° ���� addHeader() �޼��带 �̿��� �� ��° ������ �� �������� ���ؼ� ��û �� �������� addHeader() �޼��尡 ������ �ι�° ������ �ٽÿ�û   
//forward�� �̿��� ������ ��ȯ ����  

