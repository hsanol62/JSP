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
 //out 변수에 (printWriter 자료형) getWriter를 가져온다
  
      HttpSession session = request.getSession();
      //Servlet에서 HttpSession 객체 얻기 위하여 request.getSession() 함수를 이용한다.
   // 세션을 가져온다. (가져올 세션이 없다면 생성한다.)
      out.println("세션 아이디 : " + session.getId() + "<br>");
      //getId 리턴타입 java.lang.String :세션에 할당된 고유 식별자를 String 타입으로 리턴한다. 
      out.println("최초 세션 생성 시각 : " + new Date(session.getCreationTime()) + "<br>");
      //getCreationTime : long 리턴타입 :1970년 1월 1일 0시 0초를 기준으로 하여 현재 세션이 생성된 시간까지 경과한 시간을 계산하여 1/1000초 값으로 리턴한다. 
      out.println("최근 세션 접근 시각 : " + new Date(session.getLastAccessedTime()) + "<br>");
      out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>");
      //getMaxInactiveInterval() int 리턴타입 : 현재 생성된 세션을 유지하기 위해 설정된 세션 유지시간을 int형으로 리턴한다.

      if (session.isNew()) {
         out.print("새 세션이 만들어졌습니다.");
      }
   }

}

// 톰캣 컨테이너의 web.xml에 세션 기본 유효 시간이 설정된 것을 확인
// <session-config>
// <session-timeout>30</session-timeout>
// </session-config>