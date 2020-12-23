package sec03.brd01;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
		//생성자 호출시 BoardDAO 객체를 생성
	}
	//DB연결을 위한 인스턴스 생성

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
}