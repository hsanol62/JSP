package sec03.brd01;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService;
	ArticleVO articleVO;

	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		//�������� ��ü nextPage ����
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// Ŭ�󸮾�Ʈ�� request�� ���� ���� URL ���õ� �ΰ����� ��� ������ �����Ѵ�. �ΰ����� ��� ������ ���� ���
		//��û���� ������
		String action = request.getPathInfo();
		//Ŭ���̾�Ʈ�� request�� ���� ���� URL ���õ� �ΰ����� ��� ������ ����
		System.out.println("action:" + action);
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				articlesList = boardService.listArticles();
				//action���� ���� ��ȸ
				request.setAttribute("articlesList", articlesList);
				
				nextPage = "/board01/listArticles.jsp";
				///board01/listArticles.jsp����
			} else if (action.equals("/listArticles.do")) {
				//action�� listArticles�϶� 
				articlesList = boardService.listArticles();
				
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board01/listArticles.jsp";
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}