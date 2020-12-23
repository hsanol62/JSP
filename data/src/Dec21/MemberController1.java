package Dec21;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//��Ʈ�ѷ��� �������κ��� � ��û�� �޾Ҵ��� �˾Ƴ��� �Ѵ�.
//��û�� �ش��ϴ� ���� ������ �۾� ��û�ؾ� �ϴµ�, Ŀ��Ʈ �����̶�� �մϴ�.
/*@WebServlet("/member/*")*/
public class MemberController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	public void init() throws ServletException {
		memberDAO = new MemberDAO();
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
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		// addMember.do�� �޾ƿ´�. ȸ�� ����â
		System.out.println("action:" + action);
		if (action == null || action.equals("/listMembers.do")) {
			List<MemberVO> membersList = memberDAO.listMembers();
			// listMember.jsp�� �������Ѵ�.
			request.setAttribute("membersList", membersList);
			nextPage = "/1221/listMembers.jsp";
		} else if (action.equals("/addMember.do")) {
			// action���� addMember.do�� ���۵� ȸ�� ������ �����ͼ� ���̺��� �߰�
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			// ȸ�� ��� �� ȸ�� �߰�
			nextPage = "/member/listMembers.do";

		} else if (action.equals("/memberForm.do")) {
			//ȸ�� ���� â�ϰ��
			nextPage = "/1221/memberForm.jsp";
			// action���� memberForm.do�� ȭ�鿡 ���
		} else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/1221/listMembers1.jsp";
			// nextPage�� ������ ��û������ �ٽ� �������� ��û
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}