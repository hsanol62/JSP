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

	public List selectAllArticles() {
		List articlesList = new ArrayList();
//		List �������̽��� �������� ����ü�� ����� �� �ִ� �����̰�,
//ArrayList�� List �������̽��� ����ü �� �ϳ� ,list�� �� Ŀ�� list�� ����

		try {
			conn = dataFactory.getConnection();
			//����
			String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate" 
			             + " from t_board"
					     + " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
					     + " ORDER SIBLINGS BY articleNO DESC";
//			SQL �� ����
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			// preparedStatement �޼ҵ忡 sql���� ������ prepareStatement��ü�� ����.
			//����Ŭ������
			ResultSet rs = pstmt.executeQuery();
			//����� ���� ResultSet ���� �� ��� ���
			//������� ���� �� list�� ÷��
			while (rs.next()) {
				//���྿ �̵��ϸ� ������ ����
				int level = rs.getInt("level");
				//int level�� level �Է�
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