package Dec11;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginTest1211")
public class LoginTest1211 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //request.setCharacterEncoding("utf-8");
            //post 방식으로 한글전송 시 인코딩을 생략한다
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
//html 출력 하는데 필요한
            String user_name = request.getParameter("user_id");
            //html 이랑 맞는지 확인
            String user_pw = request.getParameter("user_pw");
            out.println("<html><body>");
            out.println("이름는 "+user_name + "<br>");
            out.println("비밀번호는 "+user_pw + "<br>");
            out.println("</body></html>");
    }

}