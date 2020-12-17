<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
       <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
<%
   request.setCharacterEncoding("UTF-8");
%>   
<html>
<head>
<meta charset=”UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
<table border="1"   align="center">
    <tr align="center" bgcolor="#99ccff">
       <td width="20%"><b>아이디</b></td>
       <td width="20%"><b>비밀번호</b></td>
       <td width="20%" ><b>이름</b></td>
       <td width="20%"><b>이메일</b></td>
       <td width="20%" ><b>주소</b></td>
    </tr>
<tr align=center>
       <td>${param.id } </td>
       <!--param 객체는 EL의 내장 객체이며, 파라미터 값을 얻어올 수 있다. 
       위 코드는 "id"에 해당하는 name 속성의 파라미터 값을 얻어올 수 있으며, 
       JSP 스크립트 요소 안의 request.getAttribute("id")와 같다.  -->
       <td>${param.pwd} </td>
       <td>${param.name } </td>
       <td>${param.email }</td>
       <!--<td>${requestScope.address}</td>  -->
   </tr>
</table>
</body>
</html>
