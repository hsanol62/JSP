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
	//Connection 객체 저장 변수선언
	private PreparedStatement pstmt;
	// sql 구문을 실행할 PreparedStatement객체를 담을 변수 선언 
	private DataSource dataFactory;
//커넥션풀 역할을 하는 객체를 저장할 변수 선언 
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			//<Resource /> 태그에 저장된 DB접속 정보에 대한 DataSource 커넥션풀 객체를 가져
//오기 위해 웹프로젝트의 모든 정보를 가지고 있는 InitialContext() 객체를 생산
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			//<Resource /> 태그 접근을 위한 기본 경로(java:/comp/env) 지정 
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			//톰캣 context.xml에 설정한 <Resource /> 태그의 name 속성값이 "jdbc/oracle"인
			  // JNDI 이름을 이용해 톰캣이 미리 DB와 연결한 Connection 객체를 저장하고 있는 
			   // DataSource(커넥션풀)을 받아옴
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List listMembers() {
		//검색 역할 동작 listMembers() 메소드 생성
		List list = new ArrayList();
		try {
			con = dataFactory.getConnection();
			//DataSource(커넥션풀)을 이용한 DB 접속

			String query = "select * from t_member order by joinDate desc ";
			//SQL 구문 만들기
			System.out.println("prepareStatememt: " + query);
			// PrepareStatement 객체 생성하기 (sql구문을 DB로 전송하는 역할)
			pstmt = con.prepareStatement(query);
			// PrepareStatement 객체 생성하기 (sql구문을 DB로 전송하는 역할)
			//: Statement 객체의 executeQuery() 메소드를 이용하여 query구문을 실행하여 
//테이블 형식으로 결과물을 받아온 뒤, ResultSet 객체를 통해 테이블 형태로 데이터를 변수에 저장한다.
			ResultSet rs = pstmt.executeQuery();
			//※ executeQuery() 메소드 : query구문을 실행하는 DB검색기능의 메소드로서, 검색결과는 ResultSet타입(테이블 형식)으로 반환한다.
			//ResultSet 객체로 선언한 변수 rs는 쿼리 실행결과를 저장하는 테이블 형태의 구조로 저장한다

			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberBean vo = new MemberBean();
				//memberVO 객체 생성 후 변수에 저장한 값을 1행 단위로 vo객체 내부에 저장
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
