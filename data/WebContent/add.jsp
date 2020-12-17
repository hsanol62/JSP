<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage="./addException.jsp"%>
    <!-- errorPage : 예외처리할 JSP 페이지 지정 -->
    <%
    int num = Integer.parseInt(request.getParameter("num"));
  //num을 요청 받아와 num에 integer 스타일로 저장

    int sum = 0;
    for(int i = 1 ; i<=num ; i++){
    	sum += i;
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>합 구하기</title>
</head>
<body>
	<h2>합계</h2>
	<h1>1부터 <%=num %>까지의 합 : <%=sum %></h1>
</body>
</html>