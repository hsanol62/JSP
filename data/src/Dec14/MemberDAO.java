package Dec14;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	//Connection ��ü ���� ��������
	private PreparedStatement pstmt;
	// sql ������ ������ PreparedStatement��ü�� ���� ���� ���� 
	private DataSource dataFactory;
//Ŀ�ؼ�Ǯ ������ �ϴ� ��ü�� ������ ���� ���� 
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			//<Resource /> �±׿� ����� DB���� ������ ���� DataSource Ŀ�ؼ�Ǯ ��ü�� ����
//���� ���� ��������Ʈ�� ��� ������ ������ �ִ� InitialContext() ��ü�� ����
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			//<Resource /> �±� ������ ���� �⺻ ���(java:/comp/env) ���� 
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			//��Ĺ context.xml�� ������ <Resource /> �±��� name �Ӽ����� "jdbc/oracle"��
			  // JNDI �̸��� �̿��� ��Ĺ�� �̸� DB�� ������ Connection ��ü�� �����ϰ� �ִ� 
			   // DataSource(Ŀ�ؼ�Ǯ)�� �޾ƿ�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List listMembers() {
		//�˻� ���� ���� listMembers() �޼ҵ� ����
		List list = new ArrayList();
		try {
			con = dataFactory.getConnection();
			//DataSource(Ŀ�ؼ�Ǯ)�� �̿��� DB ����

			String query = "select * from t_member order by joinDate desc ";
			//SQL ���� �����
			System.out.println("prepareStatememt: " + query);
			// PrepareStatement ��ü �����ϱ� (sql������ DB�� �����ϴ� ����)
			pstmt = con.prepareStatement(query);
			// PrepareStatement ��ü �����ϱ� (sql������ DB�� �����ϴ� ����)
			//: Statement ��ü�� executeQuery() �޼ҵ带 �̿��Ͽ� query������ �����Ͽ� 
//���̺� �������� ������� �޾ƿ� ��, ResultSet ��ü�� ���� ���̺� ���·� �����͸� ������ �����Ѵ�.
			ResultSet rs = pstmt.executeQuery();
			//�� executeQuery() �޼ҵ� : query������ �����ϴ� DB�˻������ �޼ҵ�μ�, �˻������ ResultSetŸ��(���̺� ����)���� ��ȯ�Ѵ�.
			//ResultSet ��ü�� ������ ���� rs�� ���� �������� �����ϴ� ���̺� ������ ������ �����Ѵ�

			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberBean vo = new MemberBean();
				//memberVO ��ü ���� �� ������ ������ ���� 1�� ������ vo��ü ���ο� ����
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addMember(MemberBean memberBean) {
		try {
			Connection con = dataFactory.getConnection();
			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			String query = "insert into t_member";
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
