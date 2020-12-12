package ttt;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ShowMember")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowMember() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = "", pwd = "";
		Boolean isLogon=false;
		HttpSession session = request.getSession(false);
		
		if ( session != null ) {
			isLogon=(Boolean)session.getAttribute("isLogon");
				if(isLogon==true) {
					id = (String)session.getAttribute("login_id");
                    pwd = (String)session.getAttribute("login_pwd");
					
					out.print("<html><body>");
					out.print("���̵� : " + id + "<br>");
					out.print("��й�ȣ : " + pwd + "<br>");
					out.print("</body></html");
				}else {
					response.sendRedirect("login3.html");
				}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
