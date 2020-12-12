<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String name = "홍길동";
		request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	//request에서 name 이 id인 값을 string으로 가져옴 
	String pwd = request.getParameter("pwd");

	if (id.equals("abcd") && pwd.equals("1234")) {
		response.sendRedirect("04_LoginOK.jsp?name" + URLEncoder.encode(name, "UTF-8"));
	 //이동할 페이지
	} else {
		response.sendRedirect("04_loginForm.jsp");
	}
	%>
</body>
</html>