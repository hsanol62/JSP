<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html><head><meta charset=UTF-8">
<title>자바 빈 객체 생성하기(useBean 액션 태그)</title></head>
<body>
<jsp:useBean id="usemember" class="Dec14.MemberBean"/>
◎ 자바 빈 객체 생성 후 저장된 정보 출력하기 <br><br>
이름 : <%= usemember.getName() %> <br>
아이디 : <%= usemember.getUserid() %>
<hr>
◎ 정보 변경한 후 변경된 정보 출력하기 <br><br>
<%
usemember.setName("박샘이");
usemember.setUserid("pink");
%>
이름 : <%= usemember.getName() %> <br>
아이디 : <%= usemember.getUserid() %>
</body></html>