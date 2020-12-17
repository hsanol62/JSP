<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*, Dec14.*"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
   request.setCharacterEncoding("UTF-8");
   List membersList = new ArrayList();	
   /*list객체인 memberList 변수 생성 ArrayList 객체를 생성 문자열을 저장 */
   MemberBean m1 = new MemberBean("son","1234", "손흥민", "son@test.com");
   /*MemberBean객체인 m1변수에  생성 정보 넣음  */
   MemberBean m2 = new MemberBean("ki","4321", "기성용", "ki@test.com");
   MemberBean m3 = new MemberBean("park", "1212", "박지성", "park@test.com");
   /* MemberBean에 membersList . add ( m2 ) ; 저장한 후 다시 ArrayList에 저장 */
   membersList.add(m1);
   /* memberList에 m1 추가 */
   membersList.add(m2);
   membersList.add(m3);
%>
<c:set var="membersList" value="<%= membersList%>" />
<html>
<head>
<meta charset="UTF-8">
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
 <c:forEach var="member" items="${membersList}" >	
 <!-- items속성에 memberList 할당 실행, 자동으로 var member에 membersList의 memberBean 객체가 차례대로 할당 -->
   <tr align="center">
      <td>${member.id}</td>
      <td>${member.pwd}</td>
      <td>${member.name}</td>
      <td>${member.email}</td>
   </tr>
 </c:forEach>
</table>
</body>
</html>
