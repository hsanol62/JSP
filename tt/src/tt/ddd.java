package tt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CheckboxServlet")
//������ ���� �ʿ��� �۾��̴�.
//Ŭ������� �����ϴ°� �ٸ� ���ΰ� ������ �ʾƼ� �����ϴ�.

public class ddd extends HttpServlet {
   //HttpServlet Ŭ������ ��ӹ���.
   private static final long serialVersionUID = 1L;
   // serialVersionUID�� �������� ������ �����Ϸ��� ����� ���� �ο��Ѵ�.
   // �׷��� �Ǹ� �����Ϸ��� ���� �Ҵ�Ǵ� ���� �ٸ� ���� �ִٴ� ��.
   // �����Ϸ��� Serializable Class Ȥ�� Outer Class�� �����Ͽ� ����⿡
   // ���� Ŭ������ ������ �մٸ� serialVersionUID�� ������ ������ �ִ�.
       
    public ddd() {
        super();     
    }
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // GET : URL������ ������ ���۵ȴ�.
      // html�� form �±��� method�Ӽ��� get�� ��� ȣ��ȴ�.
      // ���������� �ּ�â URL�� �̿��Ͽ� servlet�� ��û�� ��쿡�� ȣ��ȴ�.
      // doGet �޼ҵ�� �Ű������� HttpServletRequset��
       // HttpServletResponse�� �޴´�.
      // �� ���������� ��û�ϸ� doGet() �޼ҵ尡 ȣ��ǰ�
       // Requset ��ü�� Response ��ü�� ȣ���ؼ�
      // ���ڷ� �����Ѵ�.
      // doGet �޼ҵ� ȣ��� response.setContentType�� ���� �������� �����Ѵ�.

      response.setContentType("text/html;charset=UTF-8");
      //�ѱ� ���ڵ��� ���� �����ϴ� �޼����̴�.
      PrintWriter out = response.getWriter();
      //���� ��Ʈ���� �ؽ�Ʈ�� ����ϱ� ���ؼ� ȣ���Ѵ�.
      out.print("<html><body>");
      String items[] = request.getParameterValues("item");
      // getParameterValues �޼ҵ带 ���� item�� �ִ�  �������� items �迭��
      // �ϳ��� �����Ѵ�.
      if (items == null) {
      // items �迭�� ����� value���� ������ ����Ǵ� ���ǹ��̴�.
      out.print("������ �׸��� �����ϴ�.");
      } else {
       // items �迭�� ����� value���� �ΰ��� �ƴ� ��� ���� 
      out.println("����� ������ �׸��Դϴ�.<hr>");
      for (String item : items) {
      // Ȯ��� FOR���̴�. items�� �ε������� �̿��Ѵ�.
      out.print(item + " ");
      }
      }
      out.println("<br><a href='javascript:history.go(-1)'>�ٽ�</a>");
      // histroy.go()
      //���� �Ǵ� ���� �������� �̵��� �����մϴ�. ������� ������ ���ڿ� ���ڸ� �־� 
      //�̵��ϰ� �ȴ�. 1 �Ǵ� -1 ���� ����Ͽ� ���� �Ǵ� ���ķ� �̵��� �� �ִ�.

      out.print("</body></html>");
      out.close();
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
   }

}
   