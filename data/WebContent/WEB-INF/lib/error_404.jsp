<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
    <!--에러 페이지를 만들겟다-->
    <% response.setStatus(200);%>
    <!--정상적으로 실행하겠따라는 구분-->
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>404 예외 처리 페이지</title>
</head>
<body>
<h1>요청한 페이지는 존재하지 않습니다.</h1>

</body>
</html>
