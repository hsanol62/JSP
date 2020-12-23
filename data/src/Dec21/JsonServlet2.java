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
 * Servlet implementation class JsonServlet2
 */
@WebServlet("/json2")
public class JsonServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();

		JSONObject totalObject = new JSONObject();
		// 배열을 최종적으로 저장할 JSONObject 객체를 생성
		JSONArray membersArray = new JSONArray();
		//JSON만들기
		JSONObject memberInfo = new JSONObject();
	//JSONObject 객체인 memberIndo 변수 생성

		memberInfo.put("name", "박지성");
	//	 name값을 JSONObject에  넣는다.
		//memberInfo에 값저장
		memberInfo.put("age", "25");
		memberInfo.put("gender", "남자");
		memberInfo.put("nickname", "날쌘돌이");
       
		membersArray.add(memberInfo);
//membersArray 저장
		memberInfo = new JSONObject();
		//memberInfo 생성
		memberInfo.put("name", "김연아");
		memberInfo.put("age", "21");
		memberInfo.put("gender", "여자");
		memberInfo.put("nickname", "칼치");
		membersArray.add(memberInfo);

		totalObject.put("members", membersArray);
		// 멤버 정보를 저장한 배열을 배열이름 member로 membersArray에 저장.
		String jsonInfo = totalObject.toJSONString();
		// String 포맷으로 데이터를 바꿔줌.
		System.out.print(jsonInfo);
		writer.print(jsonInfo);
	}

}
