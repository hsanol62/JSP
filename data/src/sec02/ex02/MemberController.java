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
		//url ��û�� ������
		System.out.println("action:" + action);
		if (action == null || action.equals("/listMembers.do")) {
			//action�� null �̰ų� listMembers.do �� ������ 
			List<MemberVO> membersList = memberDAO.listMembers();
			//��û�� ���� ȸ�������� ��ȸ
			request.setAttribute("membersList", membersList);
			//��ȸ�� ������ request�� ���ε�
			nextPage = "/test03/listMembers.jsp";
		} else if (action.equals("/addMember.do")) {
	//action ���� addMember.do �� ������ ���̺� �߰� 
			String id = request.getParameter("id");
			//id �� �ҷ���
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			//MemberVo ��ü memberVO ������ �� ����
			memberDAO.addMember(memberVO);
			// memberVO�� �� �߰�
			request.setAttribute("msg", "addMember");
			//�߰� �Ȱ� ����
			nextPage = "/member/listMembers.do";
			///member/listMembers.do ȣ��
		} else if (action.equals("/memberForm.do")) {
			//action�� memberForm.do �ϋ�
			//ȸ�� ���� ����
			nextPage = "/test03/memberForm.jsp";
			//test03/memberForm.jsp ����
		}else if(action.equals("/modMemberForm.do")){
			//action �� modMemberForm.do �ϋ�
		     String id=request.getParameter("id");
		     //id �ҷ�����
		     MemberVO memInfo = memberDAO.findMember(id);
		     //id ã�� memInfo �� ����
		     request.setAttribute("memInfo", memInfo);
		     //memInfo�� memInfo ����
		     nextPage="/test03/modMemberForm.jsp";
		     //test03/modMemberForm.jsp ���� 
		}else if(action.equals("/modMember.do")){
			// action�� modMember.do�ϋ�
		     String id=request.getParameter("id");
		     //id �� �ҷ���
		     String pwd=request.getParameter("pwd");
		     String name= request.getParameter("name");
	         String email= request.getParameter("email");
		     MemberVO memberVO = new MemberVO(id, pwd, name, email);
		     //memberVO �� �� ����
		     memberDAO.modMember(memberVO);
		     //memberVo �� modMember�� ����
		     request.setAttribute("msg", "modified");
		     //�����Ǹ�
		     nextPage="/member/listMembers.do";
		}else if(action.equals("/delMember.do")){
			//delMember.do �϶�
		     String id=request.getParameter("id");
		     //id �ҷ���
		     memberDAO.delMember(id);
		     //id�� delMember�� ����
		     request.setAttribute("msg", "deleted");
		     // ���� �Ϸ� �޼��� ����
		     nextPage="/member/listMembers.do";
		  // nextPage�� ������ ��û������ �ٽ� ������ ��ûs
		}else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			// ���ε��Ͽ� �������� ȸ������Ʈ�� ����
			// membersList��  membersList ����
			nextPage = "/test03/listMembers.jsp";
			// nextPage�� ������ ��û������ �ٽ� ������ ��û
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
        //parameter�� postŸ������ �����ϱ� ���ؼ� Dispatcher�� ���
        //getRequestDispatcher()�� ����Ǵ� ���� ������ �������� �о�鿩�� �����̷�Ʈ ��Ų��
	}

}