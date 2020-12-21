package sec01.ex01;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajaxTest2")
public class AjaxTest2 extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public AjaxTest2() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doHandle(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doHandle(request, response);
   }

private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 request.setCharacterEncoding("utf-8");
 response.setContentType("text/html; charset=utf-8");
 String result = "";
 PrintWriter writer = response.getWriter();
 result="<main><book>"+
 "<title><![CDATA[�ڹ� ���α׷���]]></title>"+
 "<writer><![CDATA[�� | �ڻ���]]></writer>"+
 
			 "<image><![CDATA[file:///C:/Users/han/git/gitTest/data/src/sec01/ex01/01.png]]>"+ 
 
 "</image>"+"</book>"+ "<book>"+"<title><![CDATA[����� ���̽�]]></title>"+
 "<writer><![CDATA[��� �� |�ִϻ�]]></writer>"+
 
 "<image><![CDATA[file:///C:/Users/han/git/gitTest/data/src/sec01/ex01/02.jpg]]>"+ 
			
 "</image>"+"</book></main>";

 System.out.println(result);
 writer.print(result);
 }
}