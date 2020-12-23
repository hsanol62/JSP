package Dec21;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//컨트롤러가 브라우저로부터 어떤 요청을 받았는지 알아내야 한다.
//요청에 해당하는 모델을 선택해 작업 요청해야 하는데, 커맨트 패턴이라고 합니다.
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
		// addMember.do를 받아온다. 회원 가입창
		System.out.println("action:" + action);
		if (action == null || action.equals("/listMembers.do")) {
			List<MemberVO> membersList = memberDAO.listMembers();
			// listMember.jsp로 포워딩한다.
			request.setAttribute("membersList", membersList);
			nextPage = "/1221/listMembers.jsp";
		} else if (action.equals("/addMember.do")) {
			// action값이 addMember.do면 전송된 회원 정보를 가져와서 테이블에 추가
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			// 회원 등록 후 회원 추가
			nextPage = "/member/listMembers.do";

		} else if (action.equals("/memberForm.do")) {
			//회원 가입 창일경우
			nextPage = "/1221/memberForm.jsp";
			// action값이 memberForm.do면 화면에 출력
		} else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/1221/listMembers1.jsp";
			// nextPage에 지정한 요청명으로 다시 서블릿에 요청
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}