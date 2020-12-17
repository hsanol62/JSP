<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
    <%
    request.setCharacterEncoding("UTF-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과창</title>
</head>
<body>
<c:if test="${empty param.userID }">
<!-- useID가 빈칸이면 아래 출력 -->
아이디를 입력하세요.<br>
<a href = "login.jsp">로그인 창</a>
</c:if>
<c:if test="${not empty param.userID }">
<!--조건문 수행 =if userID가 빈칸이 아니면  실행  -->
<h1>환영합니다.<c:out value="${param.userID }"/>님!!!</h1>
<!-- c:out 변수값 출력 -->
</c:if>
</body>
</html>