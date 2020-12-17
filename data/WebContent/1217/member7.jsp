<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*, Dec14.*"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
   request.setCharacterEncoding("UTF-8");
   List membersList = new ArrayList();	
   /*  ArrayList 객체를 생성 문자열을 저장 */
   MemberBean m1 = new MemberBean("son","1234", "손흥민", "son@test.com");
   /*MemberBean m1 생성 정보 넣음  */
   MemberBean m2 = new MemberBean("ki","4321", "기성용", "ki@test.com");
   MemberBean m3 = new MemberBean("park", "1212", "박지성", "park@test.com");
   membersList.add(m1);
   /* memberList m1를 추가 리스트에 담는다.*/
   membersList.add(m2);
   membersList.add(m3);
%>
<c:set var="membersList" value="<%= membersList%>" />
<html>
<head>
<meta charset=UTF-8">
<title>회원 정보 출력 창</title>
</head>
<body>
<table border="1"  align="center" >
    <tr align="center" bgcolor="lightgreen">
      <td width="7%"><b>아이디</b></td>
      <td width="7%"><b>비밀번호</b></td>
      <td width="5%" ><b>이름</b></td>
      <td width="5%"><b>이메일</b></td>
</tr>
 <c:forEach var="i" begin="0" end="2" step="1" >	
 <!-- forEach태그를 이ㅣ용해 ArrayList에 저장된 회원정보를 출력
 반복 변수 i 를 ArrayList의 인덱스로 사용, 차례대로 출려
 0,1,2 출력  -->
   <tr align="center">
   <!-- 회원가입 페이지로부터 받은 회원 정보 -->
      <td>${membersList[i].id}</td>
      <td>${membersList[i].pwd}</td>
      <td>${membersList[i].name}</td>
      <td>${membersList[i].email}</td>
   </tr>
 </c:forEach>
</table>
</body>
</html>
