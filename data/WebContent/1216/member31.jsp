<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
<%
   request.setCharacterEncoding("UTF-8");
%>   

 <jsp:useBean id="m" class ="Dec14.MemberBean" scope="page"/>
 <!-- Dec14.MemberBean q불러옴  -->
  <%--자바 인스턴스 꺼내거나 자바 인스턴스 새로 만들어 보관소에 저장하는 코드를 생성한다. --%>
 <jsp:setProperty name="m" property="*"/>
 <!-- 모든프로퍼티를 적용시키기 위해 property=""적용시키기 -->
 
<html>
<head>
<!-- ${빈이름.속성이름} -->
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
    <!--스크립트 방식  -->
       <td><%=m.getId() %> </td>
       <td><%=m.getPwd() %></td>
       <td><%=m.getName() %></td>
       <td><%=m.getEmail() %></td>
   </tr>
<tr align=center>
<!--EL 방식  -->
       <td>${m.id } </td>
       <td>${m.pwd} </td>
       <td>${m.name } </td>
       <td>${m.email }</td>
   </tr>
</table>
</body>
</html>
