package Dec21;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/json")
public class JsonServlet1 extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {
      doHandle(request, response);
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {
      doHandle(request, response);
   }

   private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      String jsonInfo = request.getParameter("jsonInfo");
      //jsonInfo 를 불러와 String jsonInfo 에 저장
      try {
         JSONParser jsonParser = new JSONParser();
         //JSONParser 객체인 jsonParser 변수 생성
         JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);
       //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
         System.out.println("회원정보");
         System.out.println(jsonObject.get("name"));
         System.out.println(jsonObject.get("age"));
         System.out.println(jsonObject.get("gender"));
         System.out.println(jsonObject.get("nickname"));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}