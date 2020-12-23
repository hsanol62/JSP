package sec03.brd03;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;

	public BoardDAO() {
		try {
			// Context : JNDI를 수행하는 객체로 DataSource를 제공한다.
        	// JDNI에 접근하기 위해 기본 경로(java:/comp/env)를 지정.
			Context ctx = new InitialContext();
			//현재 환경의 naming context 획득
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			// 톰캣 context.xml에 설정한 name 값인 jdbc/oracle을 이용해 톰캣이 미리 연결한
            // DataSource를 받아 옴.
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			// JNDI로 context.xml으로 설정된 객체에 접근
			//데이터 찾기
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		//ArrayList 설정
		try {
			conn = dataFactory.getConnection();
			//data 연결
			String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate" + " from t_board"
					+ " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
					+ " ORDER SIBLINGS BY articleNO DESC";
			//sql 생성
			System.out.println(query);
			// preparedStatement 메소드에 sql문을 전달해 prepareStatement객체를 생성
						//PreparedStatement 는 sql developer에서 우리가 query를 입력하는 입력창이라고 생각
						ResultSet rs = pstmt.executeQuery();
						//결과를 담을 ResultSet rs 생성 후 결과 담기
						///executeQuery: ResultSet 객체에 결과값을 담을 수 있습니다
			while (rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}

	private int getNewArticleNO() {
		try {
			conn = dataFactory.getConnection();
			//data 설정
			String query = "SELECT  max(articleNO) from t_board ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			// preparedStatement 메소드에 sql문을 전달해 prepareStatement객체를 생성
			ResultSet rs = pstmt.executeQuery(query);
			//결과를 담을 ResultSet rs 생성 후 결과 담기
			///executeQuery: ResultSet 객체에 결과값을 담을 수 있습니다
			if (rs.next())
				return (rs.getInt(1) + 1);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();
		try {
			conn = dataFactory.getConnection();
			//data 연결
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES (?, ? ,?, ?, ?, ?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articleNO;
	}

}