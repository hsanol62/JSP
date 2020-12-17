<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  request.setCharacterEncoding("utf-8");
  request.setAttribute("id","hong");
  /*   request.setAttribute 객체명 id인 객체hong인 객체를 가져온다
  Attribute에 호출명 ID로 저장*/
  request.setAttribute("pwd","1234");
  request.setAttribute("name","홍길동");
  request.setAttribute("email","hong@test.com");
  request.setAttribute("address","서울시 서대문구");
 
%>    

<html>
<head>
   <meta charset="UTF-8">
   <title>forward4</title>
</head>
<body>
   <jsp:forward page="member41.jsp"></jsp:forward>
</body>
</html>
