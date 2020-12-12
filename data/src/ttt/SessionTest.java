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


@WebServlet("/sess")
public class SessionTest extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();
 //out ������ (printWriter �ڷ���) getWriter�� �����´�
  
      HttpSession session = request.getSession();
      //Servlet���� HttpSession ��ü ��� ���Ͽ� request.getSession() �Լ��� �̿��Ѵ�.
   // ������ �����´�. (������ ������ ���ٸ� �����Ѵ�.)
      out.println("���� ���̵� : " + session.getId() + "<br>");
      //getId ����Ÿ�� java.lang.String :���ǿ� �Ҵ�� ���� �ĺ��ڸ� String Ÿ������ �����Ѵ�. 
      out.println("���� ���� ���� �ð� : " + new Date(session.getCreationTime()) + "<br>");
      //getCreationTime : long ����Ÿ�� :1970�� 1�� 1�� 0�� 0�ʸ� �������� �Ͽ� ���� ������ ������ �ð����� ����� �ð��� ����Ͽ� 1/1000�� ������ �����Ѵ�. 
      out.println("�ֱ� ���� ���� �ð� : " + new Date(session.getLastAccessedTime()) + "<br>");
      out.println("���� ��ȿ �ð� : " + session.getMaxInactiveInterval() + "<br>");
      //getMaxInactiveInterval() int ����Ÿ�� : ���� ������ ������ �����ϱ� ���� ������ ���� �����ð��� int������ �����Ѵ�.

      if (session.isNew()) {
         out.print("�� ������ ����������ϴ�.");
      }
   }

}

// ��Ĺ �����̳��� web.xml�� ���� �⺻ ��ȿ �ð��� ������ ���� Ȯ��
// <session-config>
// <session-timeout>30</session-timeout>
// </session-config>