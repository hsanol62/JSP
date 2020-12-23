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
		//포워딩할 객체 nextPage 생성
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 클라리언트가 request를 보낼 때의 URL 관련된 부가적인 경로 정보를 리턴한다. 부가적인 경로 정보는 서블릿 경로
		//요청명을 가져옴
		String action = request.getPathInfo();
		//클라이언트가 request를 보낼 때의 URL 관련된 부가적인 경로 정보를 리턴
		System.out.println("action:" + action);
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				articlesList = boardService.listArticles();
				//action값에 따라 조회
				request.setAttribute("articlesList", articlesList);
				
				nextPage = "/board01/listArticles.jsp";
				///board01/listArticles.jsp연결
			} else if (action.equals("/listArticles.do")) {
				//action이 listArticles일때 
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