package ttt;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetCookieValue")
public class SetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();
	Date d =new Date(0);
	//빈칸() 으로 비워두니 에러발생 0 추가
	//날짜 객체 d 생성
	Cookie c = new Cookie("cookieTest",
			URLEncoder.encode("JSP프로그래밍입니다.", "utf-8"));
	//	URLEncoder.encode("JSP프로그래밍입니다.", "utf-8")를 cookietest란 이름으로 쿠키에 저장해 두기 위해 쿠키 객체 생성
	c.setMaxAge(24 * 60 * 60);
	//생성된 쿠키 객체 (info)안에 유효기간 결정하는 메소드 setMaxAge() 사용
//하루
	//c.setMaxAge(-1); //세션 쿠기를 생성합니다.
	response.addCookie(c);
	out.println("현재시간: " +d );
	out.println("<br> 문자열을 Cookie 에 저장합니다.");

}
}