package ttt;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login2")
public class LoginServlet2 extends HttpServlet{
   public void init() {
      System.out.println("init 메서드 호출");
//      서블릿 실행시 1번만 호출
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      doPost방식
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();
      String user_id = request.getParameter("user_id");
      String user_pw = request.getParameter("user_pw");
      String user_address = request.getParameter("user_address");
      String user_email = request.getParameter("user_email");
      String user_hp = request.getParameter("user_hp");
//      user_~~ 에 대한 정보를 얻어옴
      
      String data = "안녕하세요!<br> 로그인 하셨습니다.<br><br>";
      data += "<html><body>";
      data += "아이디 : " + user_id;
      data += "<br>";
      data += "비밀번호 : " + user_pw;
      data += "<br>";
      data += "주소 : " + user_address;
      data += "<br>";
      data += "email : " + user_email;
      data += "<br>";
      data += "휴대 전화 : " + user_hp;
      data += "</body></html>";
      out.print(data);
//      데이터 출력
      
      user_address = URLEncoder.encode(user_address, "utf-8");
      out.print("<a href = 'second2?user_id=" + user_id
    		  +"&user_pw="+user_pw
    		  + "&user_address=" + user_address
    		  + "'>두번째 서블릿으로 보내기</a>");
      data = "</body></html>";
      out.print(data);
   }

   public void destroy() {
      System.out.println("destroy 메서드 호출");
//      끝날때 destroy 메서드 호출 출력
   }
}