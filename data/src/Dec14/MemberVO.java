package Dec14;

import java.sql.Date;

public class MemberVO {
   private String id;
   private String pwd;
   private String name;
   private String email;
   private Date joinDate;
   //�������� ����
   public MemberVO() {
      System.out.println("MemberVO ������ ȣ��");
      //MemberVO�� �ҷ����� �߻�޼ҵ� ���� 
   }

   public String getId() {
      //id�� ��û�ϸ� id ��ȯ����
      return id;
   }

   public void setId(String id) {
      //setid�� �־�޶��ϸ� id�� ��ȯ����
      this.id = id;
   }

   public String getPwd() {
      //id�� ��û�ϸ� id ��ȯ����
      return pwd;
   }

   public void setPwd(String pwd) {
      //setPwd�� �־�޶��ϸ� pwd�� ��ȯ����
      this.pwd = pwd;
   }

   public String getName() {
      //id�� ��û�ϸ� id ��ȯ����
      return name;
   }

   public void setName(String name) {
      //setName�� �־�޶��ϸ� name�� ��ȯ����
      this.name = name;
   }


   public String getEmail() {
      //id�� ��û�ϸ� id ��ȯ����
      return email;
   }

   public void setEmail(String email) {
      //setEmail�� �־�޶��ϸ� email�� ��ȯ����
      this.email = email;
   }

   public Date getJoinDate() {
      //id�� ��û�ϸ� id ��ȯ����
      return joinDate;
   }

   public void setJoinDate(Date joinDate) {
      //setJoinDate�� �־�޶��ϸ� joinDate�� ��ȯ����
      this.joinDate = joinDate;
   }

   
}