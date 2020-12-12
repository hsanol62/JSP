package ttt;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetCookieValue")
public class GetCookieValue extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //out 변수에 (printWriter 자료형) getWriter를 가져온다

        Cookie[] allValues = request.getCookies(); 
        //cookie 불러옴
        //request의 getCookies()메서드로 얻어온 쿠키들을 Cookie 클래스의 배열인 cookies에 저장
        // getCookies()메서드 호출해 브라우저에게 쿠키 정보를 요청한 후 쿠키 정보를 배열로 가져온다.
        for (int i=0; i<allValues.length; i++) {
        	//배열에 저장된 모든 쿠키의 정보를 알아내기 위해 for문 사용
            if(allValues[i].getName().equals("cookieTest")) {
            	//getName: 쿠키 이름 알아내기 위해
            	//getValue: 쿠키에 설정된 값을 문자열 형태로 알려준다.
                out.println("<h2>Cookie 값 가져오기 : "
                        + URLDecoder.decode(allValues[i].getValue(), "utf-8"));
                //배열에서 저장할때 사용한 쿠키 이름인 cookieTest로 검색해 쿠키 값을 가져옵니다.
            }
        }
    }
}