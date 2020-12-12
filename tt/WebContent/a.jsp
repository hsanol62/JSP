<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%! int age; %>
<% 
 String str = request.getParameter("age");
 age = Integer.parseInt(str);
 %>
 당신은 <%=age %>세 입니다.
 당신은 성인 입니다... 사이트를 이용하실 수 있습니다..<br>
 <a href = "input_age.html"> 처음으로</a>
</body>
</html>