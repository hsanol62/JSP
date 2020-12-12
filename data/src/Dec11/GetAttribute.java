
package Dec11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/GetAttribute")
public class GetAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
  
        ServletContext ctx = getServletContext();
        HttpSession session = request.getSession();
        
        String ctxMesg = (String) ctx.getAttribute("context");
		String sesMesg = (String) session.getAttribute("session");
        String reqMesg =(String )request.getAttribute("request");
 //각 서블릿 API 에서 바인딩된 속성의 값을 가져옵니다.
        out.print("context값: " + ctxMesg + "<br>");
        out.print("session값: " + sesMesg + "<br>");
        out.print("request값: " + reqMesg + "<br>");
    }
}

