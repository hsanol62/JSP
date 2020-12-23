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
			// Context : JNDI를 수행하는 객체로 DataSource를 제공한다.
        	// JDNI에 접근하기 위해 기본 경로(java:/comp/env)를 지정.
			Context ctx = new InitialContext();
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

	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			//데이터베이스 연결
			String query = "select * from  t_member order by joinDate desc";
			/* System.out.println(query); */
			System.out.println("prepareStatement: " + query);
			pstmt = conn.prepareStatement(query);
			//위 query에 담겨있는 쿼리에 가변하는 변수(id)를 세팅하는 부분
			// preparedStatement 메소드에 sql문을 전달해 prepareStatement객체를 생성.
						//선언한 conn을 사용
			ResultSet rs = pstmt.executeQuery();
			//결과를 담을 ResultSet 생성 후 결과 담기
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
			// preparedStatement 메소드에 sql문을 전달해 prepareStatement객체를 생성.
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
			// preparedStatement 메소드에 sql문을 전달해 prepareStatement객체를 생성.
						//선언한 conn을 사용
			pstmt.setString(1, _id);
			//?에 id 넣음
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			//결과를 담을 ResultSet 생성 후 결과 담기
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
			// preparedStatement 메소드에 sql문을 전달해 prepareStatement객체를 생성.
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
			//data 연결
			String query = "delete from t_member where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			// preparedStatement 메소드에 sql문을 전달해 prepareStatement객체를 생성.
			pstmt.setString(1,id);
			//첫번째 ?에 id 넣음
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}