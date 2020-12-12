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
        //out ������ (printWriter �ڷ���) getWriter�� �����´�

        Cookie[] allValues = request.getCookies(); 
        //cookie �ҷ���
        //request�� getCookies()�޼���� ���� ��Ű���� Cookie Ŭ������ �迭�� cookies�� ����
        // getCookies()�޼��� ȣ���� ���������� ��Ű ������ ��û�� �� ��Ű ������ �迭�� �����´�.
        for (int i=0; i<allValues.length; i++) {
        	//�迭�� ����� ��� ��Ű�� ������ �˾Ƴ��� ���� for�� ���
            if(allValues[i].getName().equals("cookieTest")) {
            	//getName: ��Ű �̸� �˾Ƴ��� ����
            	//getValue: ��Ű�� ������ ���� ���ڿ� ���·� �˷��ش�.
                out.println("<h2>Cookie �� �������� : "
                        + URLDecoder.decode(allValues[i].getValue(), "utf-8"));
                //�迭���� �����Ҷ� ����� ��Ű �̸��� cookieTest�� �˻��� ��Ű ���� �����ɴϴ�.
            }
        }
    }
}