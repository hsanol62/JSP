
package sec03.brd02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
		//������ ȣ��� BoardDAO ��ü�� ����
	}

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		// list ����
		return articlesList;
	}
	
	public void addArticle(ArticleVO article){
		boardDAO.insertNewArticle(article);		
	}

}