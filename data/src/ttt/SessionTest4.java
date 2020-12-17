package ttt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionTest4")
public class SessionTest4 extends HttpServlet {
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
    	  //session 내장 객체의 isNew() 메소드는 세션이 처음 생성되었을 경우 "true"를 반환하지만, 이미 생성되어 있을 경우에는 "false"를 반환
         if (user_id != null) {
            session.setAttribute("user_id", user_id);
    //        세션값 설정하기  
     //session.setAttribute("설정한 세션아이디", 세션에 넣을 값);   

            out.println("<a href = 'SessionTest4'>로그인 상태 확인</a>");
         } else {
            out.print("<a href='login2.html'>다시 로그인하세요!!</a>");
            session.invalidate();
         }
      } else {
         user_id = (String) session.getAttribute("user_id");
         //세션에 저장된 값 가져오기  
         
         if (user_id != null && user_id.length() != 0) {
            out.print("안녕하세요 " + user_id + "님 !!!");
            

         } else {
            out.print("<a href='login2.html'>다시 로그인하세요!!</a>");
            session.invalidate();
         }
      }
   }

}