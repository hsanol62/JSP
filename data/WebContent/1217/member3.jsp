<%@ page language="java" contentType="text/html; charset=UTF-8"  import="java.util.*, Dec14.*" 
    pageEncoding="UTF-8"
    isELIgnored="false"%>
     <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
    <%
    request.setCharacterEncoding("UTF-8");
    %>
    <c:set var="id" value="hong" scope="page"/>
    <!-- 변수에 값을 저장한다. setAttribute와 같은 역할 
    id에 hong을 저장 -->
    <c:set var="pwd" value="1234" scope="page"/>
    <c:set var="name" value="${'홍길동'}" scope="page"/>
    <c:set var="age" value="${22}" scope="page"/>
    <c:set var="height" value="${177}" scope="page"/>
    
    <c:remove var="age" />
    <!-- age 삭제  -->
       <c:remove var="height" />
       <!-- height 삭제 나오지 않음 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력 창</title>
</head>
<body>
<table align="center" border=1>
<tr align="center" bgcolor="lightgreen">
<!--형식  -->
<td width="7%"><b>아이디</b></td>
<td width="7%"><b>비밀번호</b></td>
<td width="7%"><b>이름</b></td>
<td width="7%"><b>나이</b></td>
<td width="7%"><b>키</b></td>
</tr>
<tr align="center">
<!--불러옴  -->
<td>${id}</td>
<!-- id 불러옴  -->
<td>${pwd}</td>
<td>${name}</td>
<td>${age}</td>
<td>${height}</td>
</tr>
</table>

</body>
</html>