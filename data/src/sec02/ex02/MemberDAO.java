package sec02.ex02;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			// Context : JNDI�� �����ϴ� ��ü�� DataSource�� �����Ѵ�.
        	// JDNI�� �����ϱ� ���� �⺻ ���(java:/comp/env)�� ����.
			Context ctx = new InitialContext();
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

	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			//�����ͺ��̽� ����
			String query = "select * from  t_member order by joinDate desc";
			/* System.out.println(query); */
			System.out.println("prepareStatement: " + query);
			pstmt = conn.prepareStatement(query);
			//�� query�� ����ִ� ������ �����ϴ� ����(id)�� �����ϴ� �κ�
			// preparedStatement �޼ҵ忡 sql���� ������ prepareStatement��ü�� ����.
						//������ conn�� ���
			ResultSet rs = pstmt.executeQuery();
			//����� ���� ResultSet ���� �� ��� ���
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
				membersList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}

	public void addMember(MemberVO m) {
		try {
		Connection	conn = dataFactory.getConnection();
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			String query = "insert into t_member";
				query += "(id, pwd, name, email)";
				query +=" VALUES(?, ? ,? ,?)";
				/* System.out.println(query); */
				System.out.println("preparedStatement: " +query);
			pstmt = conn.prepareStatement(query);
			// preparedStatement �޼ҵ忡 sql���� ������ prepareStatement��ü�� ����.
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public MemberVO findMember(String _id) {
		MemberVO memInfo = null;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from  t_member where id=?";
			pstmt = conn.prepareStatement(query);
			// preparedStatement �޼ҵ忡 sql���� ������ prepareStatement��ü�� ����.
						//������ conn�� ���
			pstmt.setString(1, _id);
			//?�� id ����
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			//����� ���� ResultSet ���� �� ��� ���
			rs.next();
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate");
			memInfo = new MemberVO(id, pwd, name, email, joinDate);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memInfo;
	}

	public void modMember(MemberVO memberVO) {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		try {
			conn = dataFactory.getConnection();
			String query = "update t_member set pwd=?,name=?,email=?  where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			// preparedStatement �޼ҵ忡 sql���� ������ prepareStatement��ü�� ����.
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delMember(String id) {
		try {
			conn = dataFactory.getConnection();
			//data ����
			String query = "delete from t_member where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			// preparedStatement �޼ҵ忡 sql���� ������ prepareStatement��ü�� ����.
			pstmt.setString(1,id);
			//ù��° ?�� id ����
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}