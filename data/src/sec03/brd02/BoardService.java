
package sec03.brd02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
		//积己磊 龋免矫 BoardDAO 按眉甫 积己
	}

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		// list 积己
		return articlesList;
	}
	
	public void addArticle(ArticleVO article){
		boardDAO.insertNewArticle(article);		
	}

}