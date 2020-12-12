package ttt;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login3")
public class LoginServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet3() {
		super();

	}

	public void init() {
		System.out.println("init �޼��� ȣ��");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");
//        System.out.println(user_id);
//        System.out.println(user_pw);
//        String user_addr = request.getParameter("user_addr");
//        String user_email = request.getParameter("user_email");
//        String user_hp = request.getParameter("user_hp");

        MemberVO memverVO = new MemberVO();
        //�޾ƿ� ���̵�� ��й�ȣ ����
        memverVO.setId(user_id);
        memverVO.setPwd(user_pw);
        MemberDAO6 dao = new MemberDAO6();
        //MemberDAO5�� isExisted�޼ҵ� ȣ��, memverVO����
        boolean result = dao.isExisted(memverVO);

        String data = "<html><body>";

//        System.out.println(result);

        if(result) {
            HttpSession session = request.getSession();
            session.setAttribute("isLogon", true);
            session.setAttribute("login_id", user_id);
            session.setAttribute("login_pwd", user_pw);

            data += "�ȳ��ϼ��� " + user_id + "��<br>";
            data += "<a href='ShowMember'>ȸ����������</a>";
        }else {
            data += "<a href='login3.html'>�ٽ� �α����ϱ�</a>";
        }
        data += "</body></html>";
        out.print(data);
		}

	public void destroy() {
		System.out.println("destroy �޼��� ȣ�� ");
	}

}


// ������ ������� �� �α���â�� ��ġ�� �ʰ� �ٷ� /show�� ��û�� ���
// �α����� ���� �ʾ����Ƿ� �ٽ� �α���â���� �����̷�Ʈ�˴ϴ�.