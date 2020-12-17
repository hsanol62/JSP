<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setAttribute("name", "이순신");
    request.setAttribute("addr", "서울시 강남구");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫번째 JSP</title>
</head>
<body>
<%
RequestDispatcher dis = request.getRequestDispatcher("request2.jsp");
dis.forward(request, response);
%>
</body>
</html>