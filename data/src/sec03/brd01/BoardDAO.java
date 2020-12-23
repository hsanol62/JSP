package sec03.brd01;
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

	public List selectAllArticles() {
		List articlesList = new ArrayList();
//		List 인터페이스는 여러가지 구현체로 변경될 수 있는 형태이고,
//ArrayList는 List 인터페이스의 구현체 중 하나 ,list가 더 커서 list로 선언

		try {
			conn = dataFactory.getConnection();
			//연결
			String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate" 
			             + " from t_board"
					     + " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
					     + " ORDER SIBLINGS BY articleNO DESC";
//			SQL 문 만듬
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			// preparedStatement 메소드에 sql문을 전달해 prepareStatement객체를 생성.
			//오라클로전송
			ResultSet rs = pstmt.executeQuery();
			//결과를 담을 ResultSet 생성 후 결과 담기
			//결과값을 받은 것 list에 첨부
			while (rs.next()) {
				//한행씩 이동하며 데이터 읽음
				int level = rs.getInt("level");
				//int level에 level 입력
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
}