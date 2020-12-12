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
	//��ĭ() ���� ����δ� �����߻� 0 �߰�
	//��¥ ��ü d ����
	Cookie c = new Cookie("cookieTest",
			URLEncoder.encode("JSP���α׷����Դϴ�.", "utf-8"));
	//	URLEncoder.encode("JSP���α׷����Դϴ�.", "utf-8")�� cookietest�� �̸����� ��Ű�� ������ �α� ���� ��Ű ��ü ����
	c.setMaxAge(24 * 60 * 60);
	//������ ��Ű ��ü (info)�ȿ� ��ȿ�Ⱓ �����ϴ� �޼ҵ� setMaxAge() ���
//�Ϸ�
	//c.setMaxAge(-1); //���� ��⸦ �����մϴ�.
	response.addCookie(c);
	out.println("����ð�: " +d );
	out.println("<br> ���ڿ��� Cookie �� �����մϴ�.");

}
}