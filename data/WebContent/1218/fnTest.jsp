<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
    <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>       
 <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!-- Functon 태그는 자주 사용하는 유틸 함수의 기능을 제공 , function tag 선언-->
<%
  request.setCharacterEncoding("utf-8");
%>
<html><head><meta charset="UTF-8">
<title>여러 가지 문자열 함수 기능</title></head>
<body>
	<c:set var="title1" value="hello world!" />
	<!-- - 
value 속성 :값을 설정할 EL변수의 이름
target 속성 : 프로퍼티를 설정할 빈 또는 맵
property 속성 : 설정할 프로퍼티
var 속성 : 값이 저장되는 변수명
scope 속성 : 변수가 저장되는 영역 page, request, session, application 을 가질 수 있고, 기본값은 page 입니다.

변수명 title1, hello world 변수의값 설정 -->
	<c:set var="title2" value="쇼핑몰 중심 JSP입니다!" />
	<c:set  var="str1" value="중심" />
	<h2>여러 가지 문자열 함수 기능</h2>
	title1="hello world"<br>
	title2="쇼핑몰 중심 JSP 입니다.!"<br>
	str1="중심"<br><br>
	
	fn:length(title1)=${fn:length(title1) } <br>
	<!--빈칸 포함 12자  -->
	fn:toUpperCase(title1)=${fn:toUpperCase(title1)}<br>
	<!-- 대문자로  -->
	fn:toLowerCase(title1)=${fn:toLowerCase(title1)}<br><br>
	<!-- 소문자론 -->
	fn:substring(title1,3,6)=${fn:substring(title1,3,6)}<br>
	<!--title1 3부터 6까지 lo  -->
	fn:trim(title1)=${fn:trim(title1)}<br>
	<!-- String 문자열앞뒤의 공백을 모두 제거한후 반환 -->
	fn:replace(title1," ","/")=${fn:replace(title1," ","/")}<br><br>
	<!-- 스페이스공백을 / 을 바꾼다. -->
	fn:indexOf(title2,str1)=${fn:indexOf(title2,str1) }<br>
	<!-- fn:indexOf("내용","찾는 문자열")  title2에 str1이 몇번째 index인지 리턴-->
	fn:contains(title1,str1)=${fn:contains(title1,str1) }<br>
	<!-- title1에 str1이 포함되어있는지 -->

	fn:contains(title2,str1)=${fn:contains(title2,str1) }<br>
</body></html>
