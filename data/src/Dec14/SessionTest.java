package Dec14;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SesstionTest1214")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SessionTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("name", "�̼���");
		//�̸� set ���ش�.
		out.print("<html><body>");
		out.print("<h1>���ǿ� �̸��� ���ε��մϴ�</h1><br>");
		out.print("<a href='session1.jsp'>ù��° �������� �̵�</a>");
		//��ũ ����
		out.print("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
