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
		// �迭�� ���������� ������ JSONObject ��ü�� ����
		JSONArray membersArray = new JSONArray();
		//JSON�����
		JSONObject memberInfo = new JSONObject();
	//JSONObject ��ü�� memberIndo ���� ����

		memberInfo.put("name", "������");
	//	 name���� JSONObject��  �ִ´�.
		//memberInfo�� ������
		memberInfo.put("age", "25");
		memberInfo.put("gender", "����");
		memberInfo.put("nickname", "���ڵ���");
       
		membersArray.add(memberInfo);
//membersArray ����
		memberInfo = new JSONObject();
		//memberInfo ����
		memberInfo.put("name", "�迬��");
		memberInfo.put("age", "21");
		memberInfo.put("gender", "����");
		memberInfo.put("nickname", "Įġ");
		membersArray.add(memberInfo);

		totalObject.put("members", membersArray);
		// ��� ������ ������ �迭�� �迭�̸� member�� membersArray�� ����.
		String jsonInfo = totalObject.toJSONString();
		// String �������� �����͸� �ٲ���.
		System.out.print(jsonInfo);
		writer.print(jsonInfo);
	}

}
