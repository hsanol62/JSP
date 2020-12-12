package tt;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

@WebServlet("/first") 
public class FirstServlet extends HttpServlet { 
private static final long serialVersionUID = 1L;  
protected void doGet(HttpServletRequest request, HttpServletResponse resp)
		throws ServletException,IOException {  
resp.setContentType("text/html");  
PrintWriter out=resp.getWriter(); 
resp.sendRedirect("second");  
}
}
//다른 서블릿으로 second로 재요청한다.  
//   
//refresh를 이용한 포워딩 역시 redirect 처럼 웹 브라우저를 거쳐서 요청을 수행 클라이언트의 웹 브라우저에서 첫 번째 서블릿에 요청 첫 번째 서블릿 addHeader() 메서드를 이용해 두 번째 서블릿을 웹 브라우저를 통해서 요청 웹 브라우저는 addHeader() 메서드가 지정한 두번째 서블릿을 다시요청   
//forward를 이용한 페이지 전환 예제  

