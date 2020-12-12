package Dec11;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginTest2s1211")
public class LoginTest2s1211 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginTest2s1211(){
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");

        LoginImpl loginUser = new LoginImpl(user_id,user_pw);
        //이벤트 핸들러를 생성한 후 세션에 저장합니다.
        if(session.isNew()) {
        	//isNew() 메소드는 getSession()에서 반환받은 세션객체가
        	//새로 생성된 객체인지 기존의 세션 객체가 반환된 것인지 판단한다.
            session.setAttribute("loginUser", loginUser);
            //세션 객체에 loginuser 라는 이름의 세션 데이터를 얻어온다.
        }햣 채
        //세션 바인딩 시 loginimpl의 valueBound() 메서드를 호출합니다.
        out.println("<head>");
        out.println("<script type='text/javascript'>");
        out.println("setTimeout('history.go(0);',5000)");
        //자바스크립트 setTimeout()함수를 이용해 5초마다 서블릿에 재요청하여 현재 접속자수를 표시합니다.
        out.println("</script>");
        out.println("</head>");
        out.println("<html><body>");
        out.println("아이디는 : "+loginUser.user_id+"<br>");
        out.println("총 접속자수는 :" +LoginImpl.total_user+"<br>");
        out.println("</body></html>");


    }

}