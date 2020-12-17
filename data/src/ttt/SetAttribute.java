package ttt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dia")
public class SetAttribute extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SetAttribute() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String ctxMesg = "context에 바인딩 됩니다.";
        //ServletContext: 어플리케이션 전체에서 접근 가능
        String sesMesg = "session에 바인딩 됩니다.";
        String reqMesg = "request에 바인딩 됩니다.";

        ServletContext ctx = getServletContext();
        HttpSession session = request.getSession();
        //HttpSession: 같은 브라우저 내에서만 접근가능
        ctx.setAttribute("context", ctxMesg);
        session.setAttribute("session", sesMesg);
        request.setAttribute("request", reqMesg);
        out.print("바인딩을 수행합니다.");

    }
}