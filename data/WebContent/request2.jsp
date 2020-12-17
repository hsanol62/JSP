<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String name = (String)request.getAttribute("name");
    String addr = (String)request.getAttribute("addr");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>두번째 JSP</title>
</head>
<body>
<h1>이름 : <%=name %></h1>
<h1>주소 : <%=addr %></h1>
</body>
</html>