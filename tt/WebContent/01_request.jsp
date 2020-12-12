<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
컨텍스트 패스: <%= request.getContextPath() %><br>
 <!-- 함수 = 프로젝트 Path-->
요청방식: <%= request.getMethod() %><br>
<!-- get인지 post인지 확인 -->
요청한URL : <%= request.getRequestURL() %><br>
<!--  함수 = 전체 경로 -->
요청한URI : <%= request.getRequestURI() %><br>
<!-- 함수 = 프로젝트 + 파일경로 -->
서버의 이름: <%= request.getServerName() %><br>
<!-- 서버의 도메인 이름을 리턴 -->
프로토콜:  <%= request.getProtocol() %><br>
 <!--http : HTTP/1.1 / https : SSLv3
-->
</body>
</html>