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
   ; // connection:db�������ϰ� ���ִ� ��ü.
   private PreparedStatement pstmt;
   private DataSource dataFactory;

   public MemberDAO() {
	// ������ ����ɶ����� �ڵ����� db������ �̷�� �� �� �ֵ�����

      try {
         Context ctx = new InitialContext();
         Context envContext = (Context) ctx.lookup("java:/comp/env");
         dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
         /* JDBC ����̹��� �ҷ����� �κе� */
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      //���� ���
   }

   public List<MemberVO> listMembers() {
      List<MemberVO> membersList = new ArrayList();
      //����Ʈ ��ü ���� 
      try {
         conn = dataFactory.getConnection();
         //data�� ����
         String query = "select * from t_member order by joinDate desc ";
         System.out.println(query);
         pstmt = conn.prepareStatement(query);
         //pstmt : prepared statement ������ sql������ db�� �����ϴ� �������� �ν��Ͻ�������
         ResultSet rs = pstmt.executeQuery();
         // executeQuery:�������� ResultSet ��ü�� ���� ��ȯ�մϴ�.
         //SELECT ������ ������ �� ���Ǵ� �Լ��Դϴ�.
         //ResultSet ��ü�� ������� ���� �� �ִ�.
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
      //��� �߰��ϴ� �κ� 
	   //��û�� ���� MemberDAO �� addMember()�޼��带 ȣ��
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