<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*" import="Dec14.*"
    isELIgnored="false"  %>
<%
   request.setCharacterEncoding("UTF-8");
%>   

 <jsp:useBean id="m1" class ="Dec14.MemberBean" scope="page"/>
 <%-- 자바 인스턴스 꺼내거나 자바 인스턴스 새로 만들어 보관소에 저장하는 코드를 생성한다.  --%>
 <jsp:setProperty name="m1" property="*"/>
 <jsp:useBean id="membersList" class ="java.util.ArrayList"/>
  <%--memberBean 객체를 저장할 ArrayList 객체를 생성한다. --%>
  <jsp:useBean id="membersMap" class ="java.util.HashMap"/>
 <% 
 /* hashMap에 key/value 회원정보를 저장한다. */
 membersMap.put("id", "park2");
 membersMap.put("pwd","4321");
 membersMap.put("name","박지성");
 membersMap.put("email","park@test.com");
 
 
 MemberBean m2 = new MemberBean("son","1234" , "손흥민","son@test.com");
 /*회원정보를 저장하는 MemberBean 객체를 생성한다.  */
 membersList.add(m1);
 /*전송된 회원 정보와 자바 코드로 생성한 회원 정보를 ArrayList에 저장한다.  */
 membersList.add(m2);
 membersMap.put("membersList",membersList);
 /*회원 정보가 저장된 memberList를 memberList라는 key hashMap에 저장한다.  */
%>
<!DOCTYPE html>
<html>
<head>
<%--${HashMap객체이름.키이름 } --%>
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
       <td>${membersMap.id } </td>
       <td>${membersMap.pwd} </td>
       <td>${membersMap.name } </td>
       <td>${membersMap.email }</td>
   </tr>
<tr align=center>
       <td>${membersList[0].id } </td>
       <td>${membersList[0].pwd} </td>
       <td>${membersList[0].name } </td>
       <td>${membersList[0].email }</td>
   </tr>
<tr align=center>
       <td>${membersList[1].id } </td>
       <td>${membersList[1].pwd } </td>
       <td>${membersList[1].name } </td>
       <td>${membersList[1].email }</td>
   </tr>
</table>
</body>
</html>
