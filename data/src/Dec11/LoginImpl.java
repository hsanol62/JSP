package Dec11;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class LoginImpl implements HttpSessionListener{
   String user_id;
   String user_pw;
   static int total_user = 0;
   
   public LoginImpl() {
      
   }
   
   public  LoginImpl(String user_id, String user_pw) {
      this.user_id = user_id;
      this.user_pw = user_pw;
   }

   @Override
   public void sessionCreated(HttpSessionEvent arg0) {
      System.out.println("세션 생성");
      ++total_user;
   }
   //세션 생성시 이벤트를 처리합니다.
   
   @Override
   public void sessionDestroyed(HttpSessionEvent arg0) {
      System.out.println("세션 소멸");
      --total_user;
      //세션 소멸시 접속자수를 1 감소시킵니다.
   }
}