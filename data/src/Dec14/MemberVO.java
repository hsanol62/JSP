package Dec14;

import java.sql.Date;

public class MemberVO {
   private String id;
   private String pwd;
   private String name;
   private String email;
   private Date joinDate;
   //전역변수 설정
   public MemberVO() {
      System.out.println("MemberVO 생성자 호출");
      //MemberVO가 불려지면 추상메소드 실행 
   }

   public String getId() {
      //id를 요청하면 id 반환해줌
      return id;
   }

   public void setId(String id) {
      //setid를 넣어달라하면 id값 변환해줌
      this.id = id;
   }

   public String getPwd() {
      //id를 요청하면 id 반환해줌
      return pwd;
   }

   public void setPwd(String pwd) {
      //setPwd를 넣어달라하면 pwd값 변환해줌
      this.pwd = pwd;
   }

   public String getName() {
      //id를 요청하면 id 반환해줌
      return name;
   }

   public void setName(String name) {
      //setName를 넣어달라하면 name값 변환해줌
      this.name = name;
   }


   public String getEmail() {
      //id를 요청하면 id 반환해줌
      return email;
   }

   public void setEmail(String email) {
      //setEmail를 넣어달라하면 email값 변환해줌
      this.email = email;
   }

   public Date getJoinDate() {
      //id를 요청하면 id 반환해줌
      return joinDate;
   }

   public void setJoinDate(Date joinDate) {
      //setJoinDate를 넣어달라하면 joinDate값 변환해줌
      this.joinDate = joinDate;
   }

   
}