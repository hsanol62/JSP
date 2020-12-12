package D1208;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//회원 정보 조회 SQL문을 실행하여 조회한 레코드들의 컬럼값을 다시 MemberVO 객체의 속성에 설정한 다음 ArrayList에 저장하고 호출한 곳으로 반환.
public class MemberDAO {
	
//	private static final String driver= "oracle.jdbc.driver.OracleDriver";
//	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	private static final String user ="scott";
//	private static final String pwd = "tiger";  //더 이상 사용되지 않으므로 주석처리.
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	//private Statement stmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env"); //JNDI에 접근하기 위해 기본 경로(java:/comp/env)를 지정.
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle"); //톰캣 context.xml에 설정한 name값인 jdbc/oracle을 이용해
																		//톰캣이 미리 연결한 DataSource를 받아옴.
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List listMembers() {
		List list = new ArrayList();
		try {
			//connDB(); //네가지 정보로 데이터베이스를 연결.
			con = dataFactory.getConnection(); //DataSource를 이용해 데이터베이스에 연결.
			String query = "select * from t_member ";
			System.out.println(query);
			//ResultSet rs = stmt.executeQuery(query); //SQL문으로 회원 정보 조회
			pstmt = con.prepareStatement(query); //prepareStatement() 메서드에 SQL문을 전달해 PrepareStatement 객체를 생성.
			ResultSet rs = pstmt.executeQuery(); //executeQuery() 메서드를 호출해 미리 설정한 SQL문을 실행.
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate"); //조회한 레코드의 각 컬럼 값을 받아옴.
				
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate); //각 컬럼 값을 다시 MemberVO 객체의 속성에 설정.
				list.add(vo); //설정된 MemberVO 객체를 다시 ArrayList에 저장.
			}
			
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //조회한 레코드의 개수만큼 MemberVO객체를 저장한 ArrayList를 반환.
	}
}
	
//	private void connDB() {
//		try {
//			Class.forName(driver);
//			System.out.println("Oracle 드라이버 로딩 성공");
//			con = DriverManager.getConnection(url, user, pwd);
//			System.out.println("Connection 생성 성공");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

