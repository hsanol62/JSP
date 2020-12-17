<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*, Dec14.*" pageEncoding="UTF-8"
   isELIgnored="false"%>
     <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
<%
   session.setAttribute("address","수원시 팔달구");
/* address에 수원시 팔당구 저장
세션 속성명이 name인 속성에 속성값으로 value를 할당한다. */
%>
<!-- session에서 바인딩한 주소가 출력되는 것을 확인할 수 있습니다. -->


<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
   <table border=1 align="center">
      <tr align="center" bgcolor="#99cccff">
         <td width="7%"><b>아이디</b></td>
         <td width="7%"><b>비밀번호</b></td>
         <td width="5%"><b>이름</b></td>
         <td width="5%"><b>이메일</b></td>
         <td width="5%"><b>주소</b></td>
      </tr>
      <tr align="center">
         <td>${id }</td>
         <!-- id 가져오기 -->
         <td>${pwd }</td>
         <td>${name }</td>
         <td>${email }</td>
         <td>${address }</td>
      </tr>
      
   </table>
</body>
</html>