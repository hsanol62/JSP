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
			// Context : JNDI�� �����ϴ� ��ü�� DataSource�� �����Ѵ�.
        	// JDNI�� �����ϱ� ���� �⺻ ���(java:/comp/env)�� ����.
			Context ctx = new InitialContext();
			//���� ȯ���� naming context ȹ��
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			// ��Ĺ context.xml�� ������ name ���� jdbc/oracle�� �̿��� ��Ĺ�� �̸� ������
            // DataSource�� �޾� ��.
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			// JNDI�� context.xml���� ������ ��ü�� ����
			//������ ã��
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		//ArrayList ����
		try {
			conn = dataFactory.getConnection();
			//data ����
			String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate" + " from t_board"
					+ " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
					+ " ORDER SIBLINGS BY articleNO DESC";
			//sql ����
			System.out.println(query);
			// preparedStatement �޼ҵ忡 sql���� ������ prepareStatement��ü�� ����
						//PreparedStatement �� sql developer���� �츮�� query�� �Է��ϴ� �Է�â�̶�� ����
						ResultSet rs = pstmt.executeQuery();
						//����� ���� ResultSet rs ���� �� ��� ���
						///executeQuery: ResultSet ��ü�� ������� ���� �� �ֽ��ϴ�
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
			//data ����
			String query = "SELECT  max(articleNO) from t_board ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			// preparedStatement �޼ҵ忡 sql���� ������ prepareStatement��ü�� ����
			ResultSet rs = pstmt.executeQuery(query);
			//����� ���� ResultSet rs ���� �� ��� ���
			///executeQuery: ResultSet ��ü�� ������� ���� �� �ֽ��ϴ�
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
			//data ����
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