package Dec21;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
   private Connection conn;
   ; // connection:db에접근하게 해주는 객체.
   private PreparedStatement pstmt;
   private DataSource dataFactory;

   public MemberDAO() {
	// 생성자 실행될때마다 자동으로 db연결이 이루어 질 수 있도록함

      try {
         Context ctx = new InitialContext();
         Context envContext = (Context) ctx.lookup("java:/comp/env");
         dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
         /* JDBC 드라이버를 불러오는 부분들 */
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      //오류 출력
   }

   public List<MemberVO> listMembers() {
      List<MemberVO> membersList = new ArrayList();
      //리스트 객체 생성 
      try {
         conn = dataFactory.getConnection();
         //data랑 연결
         String query = "select * from t_member order by joinDate desc ";
         System.out.println(query);
         pstmt = conn.prepareStatement(query);
         //pstmt : prepared statement 정해진 sql문장을 db에 삽입하는 형식으로 인스턴스가져옴
         ResultSet rs = pstmt.executeQuery();
         // executeQuery:수행결과로 ResultSet 객체의 값을 반환합니다.
         //SELECT 구문을 수행할 때 사용되는 함수입니다.
         //ResultSet 객체에 결과값을 담을 수 있다.
         while (rs.next()) {
            String id = rs.getString("id");
            String pwd = rs.getString("pwd");
            String name = rs.getString("name");
            String email = rs.getString("email");
            Date joinDate = rs.getDate("joinDate");
            MemberVO vo = new MemberVO(id, pwd, email,name, joinDate);
            membersList.add(vo);
         }
         rs.close();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return membersList;
   }

   public void addMember(MemberVO m) {
      //멤버 추가하는 부분 
	   //요청명에 대해 MemberDAO 의 addMember()메서드를 호출
      try {
         conn = dataFactory.getConnection();
         String id = m.getId();
         String pwd = m.getPwd();
         String name = m.getName();
         String email = m.getEmail();
         String query = "insert into t_member(id,pwd,name,email) VALUES(?, ?, ?, ?)";
         System.out.println("prepareStatememt: " + query);
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, id);
         pstmt.setString(2, pwd);
         pstmt.setString(3, name);
         pstmt.setString(4, email);
         pstmt.executeUpdate();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}