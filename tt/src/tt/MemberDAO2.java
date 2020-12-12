package tt;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO2 {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	// ¿À¶óÅ¬À» ±â¹İÀ¸·Î ÇÏ´Â µå¶óÀÌ¹ö¸¦ ¼±¾ğ
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	// ¿¬µ¿ÇÒ DBÀÇ ÁÖ¼Ò¸¦ ¼±¾ğ
	private static final String user = "scott";
	// DBÀÇ userID¸¦ »ó¼ö·Î ¼±¾ğ
	private static final String pwd = "tiger";
	// DBÀÇ ºñ¹Ğ¹øÈ£¸¦ »ó¼ö·Î ¼±¾ğ
	private Connection con;
	// DBÁ¢¼Ó¿¡ »ç¿ëÇÏ´Â con º¯¼ö
	private Statement stmt;
	// »óÅÂ¸¦ ÇÇµå¹éÇÏ´Â stmt º¯¼ö

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		////MemberVOÀ» Á¦³×¸¯À¸·Î ¹Ş¾Æ¼­ ArrayList ÀÎ list °´Ã¼¸¦ »ı¼º
		try {
			connDB();
			//connDB ¸Ş¼­µå È£­„
			
			String query = "select * from t_member ";
			// ÁúÀÇ¹®À» ÀúÀåÇÑ´Ù.
			System.out.println(query);
			// ÁúÀÇ¹® Ãâ·Â 
			ResultSet rs = stmt.executeQuery(query);
			// 1. ¼öÇà°á°ú·Î ResultSet °´Ã¼ÀÇ °ªÀ» ¹İÈ¯ÇÕ´Ï´Ù.
			// 2. SELECT ±¸¹®À» ¼öÇàÇÒ ¶§ »ç¿ëµÇ´Â ÇÔ¼öÀÔ´Ï´Ù.
			while (rs.next()) {
				// next()
				//°ø¹éÀ» ±âÁØÀ¸·Î ÀÔ·ÂÀ» ¹Ş´Â´Ù. Áï, ¶ç¾î¾²±â(=\\s)À» ±âÁØÀ¸·Î ÀÔ·ÂÀ» ¹Ş´Â´Ù.
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				// id,pwd,name,email,joinData¸¦ °ÔÅÍ¸¦ ÅëÇØ ¹Ş¾Æ¿È
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				//°´Ã¼ÀÇ ¼¼ÅÍ ¸Ş¼­µåµîÀ» ½ÇÇà
				list.add(vo);
				// ¸®½ºÆ®ÀÇ vo¸¦ add
			}
			rs.close();
			// ¼Â¸®¼³Æ® Á¾·á
			stmt.close();
			// ½ºÅ×ÀÌÆ®¸ÕÆ® Á¾·á
			con.close();
			// con Á¾·á
		} catch (Exception e) {
			e.printStackTrace();
			// ¿¹¿ÜÃ³¸®¹®
		}
		return list;
			// Á¤»ó ½ÇÇà½Ã list ¼öÇà 
	}

	private void connDB() {
		try {
			Class.forName(driver);
			//ojdbc ½ÇÇà
			System.out.println("Oracle µå¶óÀÌ¹ö ·Îµù ¼º°ø");
			// µå¶óÀÌ¹ö ·Îµù ¼º°ø½Ã Ãâ·Â
			con = DriverManager.getConnection(url, user, pwd);
			// ÀÌ getConnection ¸Ş¼­µå´Â javax.sql.DataSource ÀÎÅÍÆäÀÌ½ºÀÇ
			//getConnection ¸Ş¼­µå¿¡ ÀÇÇØ ÁöÁ¤µÈ´Ù.
			//URL,USERNAME,PASSWORD¸¦ ¸Å°Üº¯¼ö·Î ¹Ş´Â´Ù.
			System.out.println("Connection »ı¼º ¼º°ø");
			//¿¬µ¿ ¼º°ø½Ã Ãâ·Â
			stmt = con.createStatement();
			//Statement °´Ã¼¸¦ »ı¼ºÇÏ¸é Statement °´Ã¼ÀÇ executeQuery() ¸Ş¼Òµå¸¦ È£ÃâÇÏ¿© 
			//SQL ÁúÀÇ¸¦ ½ÇÇà½ÃÅ³ ¼ö ÀÖ´Ù. ¸Ş¼ÒµåÀÇ ÀÎ¼ö·Î´Â SQL ÁúÀÇ ¹®ÀåÀ» ´ãÀº String °´Ã¼¸¦ Àü´ŞÇÑ´Ù.
			//Statement °´Ã¼´Â ´Ü¼øÇÑ ÁúÀÇ¹®À» »ç¿ëÇÒ °æ¿ì¿¡ ÁÁ´Ù.
			System.out.println("Statement »ı¼º ¼º°ø");
			//ÁúÀÇ ¼º°ø½Ã Ãâ·Â
		} catch (Exception e) {
			e.printStackTrace();
			//¿¹¿Ü Ã³¸®¹®
		}
	}
}
