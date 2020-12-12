package Dec11;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet("*.do")
//확장자만 일치하는 패턴
//@WebServlet("/*")*/
// 모든 요청 URL 패턴
@WebServlet("/first/TestServlet3")

public class TestServlet3 extends HttpServlet {
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {

request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=utf-8"); 

PrintWriter out =response.getWriter();
String context = request.getContextPath(); 
//컨텍스트 이름 가져온다
String url =request.getRequestURL().toString(); 
//전체 url 을 가져온다
String mapping =request.getServletPath(); 
//서블릿 매핑 이름 가져옴
String uri = request.getRequestURI();
//uri 를 가져온다.
out.println("<html>"); 
out.println("<head>");
out.println("<title>Test Servlet3</title>");
out.println("</head>");
out.println("<body bgcolor='red'>");
out.println("<b>TestServlet3입니다. </b><br>");
out.println("<b>컨텍스트명 : "+context+"</b><br>");
out.println("<b>전체경로 : "+url+"</b><br>");
out.println("<b>매핑명 :"+mapping+"</b><br>");
out.println("<b>URI : "+uri+"</b>"); out.println("</body>");
out.println("</html>"); out.close();



}

protected void doPost(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException { }

}