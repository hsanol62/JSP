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
	// ����Ŭ�� ������� �ϴ� ����̹��� ����
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	// ������ DB�� �ּҸ� ����
	private static final String user = "scott";
	// DB�� userID�� ����� ����
	private static final String pwd = "tiger";
	// DB�� ��й�ȣ�� ����� ����
	private Connection con;
	// DB���ӿ� ����ϴ� con ����
	private Statement stmt;
	// ���¸� �ǵ���ϴ� stmt ����

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		////MemberVO�� ���׸����� �޾Ƽ� ArrayList �� list ��ü�� ����
		try {
			connDB();
			//connDB �޼��� ȣ��
			
			String query = "select * from t_member ";
			// ���ǹ��� �����Ѵ�.
			System.out.println(query);
			// ���ǹ� ��� 
			ResultSet rs = stmt.executeQuery(query);
			// 1. �������� ResultSet ��ü�� ���� ��ȯ�մϴ�.
			// 2. SELECT ������ ������ �� ���Ǵ� �Լ��Դϴ�.
			while (rs.next()) {
				// next()
				//������ �������� �Է��� �޴´�. ��, ����(=\\s)�� �������� �Է��� �޴´�.
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				// id,pwd,name,email,joinData�� ���͸� ���� �޾ƿ�
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				//��ü�� ���� �޼������ ����
				list.add(vo);
				// ����Ʈ�� vo�� add
			}
			rs.close();
			// �¸���Ʈ ����
			stmt.close();
			// ������Ʈ��Ʈ ����
			con.close();
			// con ����
		} catch (Exception e) {
			e.printStackTrace();
			// ����ó����
		}
		return list;
			// ���� ����� list ���� 
	}

	private void connDB() {
		try {
			Class.forName(driver);
			//ojdbc ����
			System.out.println("Oracle ����̹� �ε� ����");
			// ����̹� �ε� ������ ���
			con = DriverManager.getConnection(url, user, pwd);
			// �� getConnection �޼���� javax.sql.DataSource �������̽���
			//getConnection �޼��忡 ���� �����ȴ�.
			//URL,USERNAME,PASSWORD�� �Űܺ����� �޴´�.
			System.out.println("Connection ���� ����");
			//���� ������ ���
			stmt = con.createStatement();
			//Statement ��ü�� �����ϸ� Statement ��ü�� executeQuery() �޼ҵ带 ȣ���Ͽ� 
			//SQL ���Ǹ� �����ų �� �ִ�. �޼ҵ��� �μ��δ� SQL ���� ������ ���� String ��ü�� �����Ѵ�.
			//Statement ��ü�� �ܼ��� ���ǹ��� ����� ��쿡 ����.
			System.out.println("Statement ���� ����");
			//���� ������ ���
		} catch (Exception e) {
			e.printStackTrace();
			//���� ó����
		}
	}
}
