package ttt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionTest5")
public class SessionTest5 extends HttpServlet {
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doHandle(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doHandle(request, response);
   }

   private void doHandle(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();
      HttpSession session = request.getSession();
      String user_id = request.getParameter("user_id");
      String user_pw = request.getParameter("user_pw");
      if (session.isNew()) {
         if (user_id != null) {
            session.setAttribute("user_id", user_id);
            String url = response.encodeURL("login");
            //���� url�� encodeURL()�� �̿��Ͽ� ����� �̸� jsessionid�� �����Ѵ�.
            
            out.println("<a href = 'SessionTest4'>�α��� ���� Ȯ��</a>");
            //�α��� ���� Ȯ�ν� jsessionid�� �������� �ٽ� �����Ѵ�.
         } else {
            out.print("<a href='login2.html'>�ٽ� �α����ϼ���!!</a>");
            session.invalidate();
         }
      } else {
         user_id = (String) session.getAttribute("user_id");
         
         if (user_id != null && user_id.length() != 0) {
            out.print("�ȳ��ϼ��� " + user_id + "�� !!!");
            

         } else {
            out.print("<a href='login2.html'>�ٽ� �α����ϼ���!!</a>");
            session.invalidate();
         }
      }
   }

}
//������ jessionId ��Ű ���� ������ �α��� ���¸� �����մϴ�.

//JSESSIONID��?
//- ��Ĺ �����̳ʿ��� ������ �����ϱ� ���� �߱��ϴ� Ű
//- HTTP ���������� stateless(������?)�ϴ�. 
//��û�ø��� ���ο� ������ �����ǰ� ������ ������ ����� �ǹǷ� ���¸� ������ �� ����.
//- ����, ���¸� �����ϱ� ���ؼ� ��Ĺ�� JSESSIONID ��Ű�� Ŭ���̾�Ʈ���� �߱����ְ� �� ���� ���� ������ ������ �� �ֵ��� �Ѵ�.