package Dec21;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class JsonServlet3
 */
@WebServlet("/json3") 
public class JsonServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();

		JSONObject totaObject = new JSONObject();
		// 배열을 최종적으로 저장할 JSONObject 객체를 생성
		JSONArray membersArray = new JSONArray();
		//JSON만들기
		JSONObject memberInfo = new JSONObject();
		//	 name값을 JSONObject에  넣는다.
		//memberInfo에 값저장
		memberInfo.put("name", "박지성");
		memberInfo.put("age", "25");
		memberInfo.put("gender", "남자");
		memberInfo.put("nickname", "날쌘돌이");
 
		membersArray.add(memberInfo);

		memberInfo = new JSONObject();
		memberInfo.put("name", "김연아");
		memberInfo.put("age", "21");
		memberInfo.put("gender", "여자");
		memberInfo.put("nickname", "칼치");
		membersArray.add(memberInfo);
		totaObject.put("members", membersArray);

		JSONArray bookArray = new JSONArray();
		//bookArray 생성
		JSONObject bookInfo = new JSONObject();
		//값 저장할 bookInfo 생성
		bookInfo.put("title", "Html");
		bookInfo.put("writer", "아무개");
		bookInfo.put("price", "30000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8081/data/image/image1.png");
		bookArray.add(bookInfo);

		bookInfo = new JSONObject();
		bookInfo.put("title", "파이썬");
		bookInfo.put("writer", "박샘이");
		bookInfo.put("price", "12000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8081/data/image/image2.jpg");
		bookArray.add(bookInfo);

		totaObject.put("books", bookArray);

		String jsonInfo = totaObject.toJSONString();
		System.out.print(jsonInfo);
		writer.print(jsonInfo);
	}

}
