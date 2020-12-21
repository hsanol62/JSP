<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
     <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
    <c:set var="contextPath" value="${pageContext.request.contextPath }" />
    <%
    request.setCharacterEncoding("utf-8");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="file1" value="${param.param1 }" />
	<!-- - 
value 속성 :값을 설정할 EL변수의 이름
target 속성 : 프로퍼티를 설정할 빈 또는 맵
property 속성 : 설정할 프로퍼티
var 속성 : 값이 저장되는 변수명
scope 속성 : 변수가 저장되는 영역 page, request, session, application 을 가질 수 있고, 기본값은 page 입니다.

변수명 file1, value 저장 -->
<c:set var="file2" value="${param.param2 }" />
<title>이미지 파일 출력하기</title>
</head>
<body>
파라미터 1 :<c:out value="${file1}" /> <br>
파라미터 2 :<c:out value="${file2}" /> <br>
<!--  변수 내용을 출력할 때 사용되는 태그 -->
<c:if test="${not empty file1}">
<!-- 배열이 비어 있지 않을 경우, 문자열이 값이 있을 경우 참을 반환-->
<img src="${contextPath}/download.do?fileName=${file1}"
width=300 height=300 /><br>
</c:if>
<br>
<c:if test="${not empty file2}">
<img src="${contextPath}/download.do?fileName=${file2}"
width=300 height=300 /><br>
</c:if>
파일 내려받기 : <br>
<a href="${contextPath}/download.do?fileName=${file1}">파일 내려받기</a><br>
<a href="${contextPath}/download.do?fileName=${file2}">파일 내려받기</a><br>
</body>
</html>