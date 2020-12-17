<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String name = (String)session.getAttribute("name");
    //name을 불러와 name 에 넣어준다.
    session.setAttribute("address", "서울시 강남구");
    //address값 set해준다.
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session 내장 객체 테스트1</title>
</head>
<body>
	이름 : <%=name %><br>
	<a href="session2.jsp">두번째 페이지로 이동</a>
</body>
</html>