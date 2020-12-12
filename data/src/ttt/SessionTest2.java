package ttt;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sess2")
public class SessionTest2 extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();
      HttpSession session = request.getSession();
      out.println("���� ���̵� : " + session.getId() + "<br>");
   //������ ����Ű�� ���� id ��
      out.println("���� ���� ���� �ð� : " + new Date(session.getCreationTime()) + "<br>");
      //������ ������� �ð��� ��ȯ
      out.println("�ֱ� ���� ���� �ð� : " + new Date(session.getLastAccessedTime()) + "<br>");
      //�ش� ������ Ŭ���̾�Ʈ�� ���������� request�� ���� �ð��� long ������ ��ȯ�մϴ�
      out.println("���� ��ȿ �ð� : " + session.getMaxInactiveInterval() + "<br>");
      session.setMaxInactiveInterval(5);
      out.println("���� ��ȿ �ð�:" +session.getMaxInactiveInterval() + "<br>");
//      ����ڰ� ���� ��û�� ���������� ���� �������ϴ� �ִ� �ð�(�ʴ���) ����
      if (session.isNew()) {
         out.print("�� ������ ����������ϴ�.");
      }
   }

}

// ��Ĺ �����̳��� web.xml�� ���� �⺻ ��ȿ �ð��� ������ ���� Ȯ��
// <session-config>
// <session-timeout>30</session-timeout>
// </session-config>