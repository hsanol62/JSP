package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
//@WebServlet("/member.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		//url 요청을 가져옴
		System.out.println("action:" + action);
		if (action == null || action.equals("/listMembers.do")) {
			//action이 null 이거나 listMembers.do 랑 같으면 
			List<MemberVO> membersList = memberDAO.listMembers();
			//요청에 대한 회원정보를 조회
			request.setAttribute("membersList", membersList);
			//조회한 정보를 request에 바인딩
			nextPage = "/test03/listMembers.jsp";
		} else if (action.equals("/addMember.do")) {
	//action 값이 addMember.do 면 가져와 테이블에 추가 
			String id = request.getParameter("id");
			//id 값 불러옴
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			//MemberVo 객체 memberVO 변수에 값 저장
			memberDAO.addMember(memberVO);
			// memberVO에 값 추가
			request.setAttribute("msg", "addMember");
			//추가 된것 적용
			nextPage = "/member/listMembers.do";
			///member/listMembers.do 호출
		} else if (action.equals("/memberForm.do")) {
			//action이 memberForm.do 일떄
			//회원 정보 수정
			nextPage = "/test03/memberForm.jsp";
			//test03/memberForm.jsp 연결
		}else if(action.equals("/modMemberForm.do")){
			//action 이 modMemberForm.do 일떄
		     String id=request.getParameter("id");
		     //id 불러오고
		     MemberVO memInfo = memberDAO.findMember(id);
		     //id 찾아 memInfo 에 저장
		     request.setAttribute("memInfo", memInfo);
		     //memInfo에 memInfo 저장
		     nextPage="/test03/modMemberForm.jsp";
		     //test03/modMemberForm.jsp 연결 
		}else if(action.equals("/modMember.do")){
			// action이 modMember.do일떄
		     String id=request.getParameter("id");
		     //id 값 불러옴
		     String pwd=request.getParameter("pwd");
		     String name= request.getParameter("name");
	         String email= request.getParameter("email");
		     MemberVO memberVO = new MemberVO(id, pwd, name, email);
		     //memberVO 에 값 저장
		     memberDAO.modMember(memberVO);
		     //memberVo 를 modMember에 저장
		     request.setAttribute("msg", "modified");
		     //수정되면
		     nextPage="/member/listMembers.do";
		}else if(action.equals("/delMember.do")){
			//delMember.do 일때
		     String id=request.getParameter("id");
		     //id 불러와
		     memberDAO.delMember(id);
		     //id를 delMember에 저장
		     request.setAttribute("msg", "deleted");
		     // 삭제 완료 메세지 전달
		     nextPage="/member/listMembers.do";
		  // nextPage에 지정한 요청명으로 다시 서블릿에 요청s
		}else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			// 바인딩하여 수정전의 회원리스트를 전달
			// membersList에  membersList 저장
			nextPage = "/test03/listMembers.jsp";
			// nextPage에 지정한 요청명으로 다시 서블릿에 요청
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
        //parameter를 post타입으로 전달하기 위해서 Dispatcher를 사용
        //getRequestDispatcher()가 실행되는 순간 지정된 페이지를 읽어들여서 리다이렉트 시킨다
	}

}