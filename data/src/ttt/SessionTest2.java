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
      out.println("세션 아이디 : " + session.getId() + "<br>");
   //세션을 가리키는 고유 id 값
      out.println("최초 세션 생성 시각 : " + new Date(session.getCreationTime()) + "<br>");
      //세션이 만들어진 시간을 반환
      out.println("최근 세션 접근 시각 : " + new Date(session.getLastAccessedTime()) + "<br>");
      //해당 세션이 클라이언트가 마지막으로 request를 보낸 시간을 long 형으로 반환합니다
      out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>");
      session.setMaxInactiveInterval(5);
      out.println("세션 유효 시간:" +session.getMaxInactiveInterval() + "<br>");
//      사용자가 다음 요청을 보낼때까지 세션 유지를하는 최대 시간(초단위) 설정
      if (session.isNew()) {
         out.print("새 세션이 만들어졌습니다.");
      }
   }

}

// 톰캣 컨테이너의 web.xml에 세션 기본 유효 시간이 설정된 것을 확인
// <session-config>
// <session-timeout>30</session-timeout>
// </session-config>