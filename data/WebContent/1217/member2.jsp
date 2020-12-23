<%@ page language="java" contentType="text/html; charset=UTF-8"  import="java.util.*, Dec14.*" 
    pageEncoding="UTF-8"
    isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
 
    <%
    request.setCharacterEncoding("UTF-8");
    %>
    
    <jsp:useBean id="membersList" class="java.util.ArrayList" />
     <%--useBean 자바 인스턴스 꺼내거나 자바 인스턴스 새로 만들어 보관소에 저장하는 코드를 생성한다. --%>
    
    <jsp:useBean id="membersMap" class="java.util.HashMap" />
    <!--HashMap은 Map을 구현한다. Key와 value를 묶어 하나의 entry로 저장한다는 특징을 갖는다. 그리고 hashing을 사용하기 때문에 많은양의 데이터를 검색하는데 뛰어난 성능을 보인다.
 -->
<%
   membersMap.put("id", "park2");
/*  HashMap ( "Key", value) */
   membersMap.put("pwd", "4321");
   membersMap.put("name", "박지성");
   membersMap.put("email", "park2@test.com");
   MemberBean m1 = new MemberBean("son", "1234", "손흥민", "son@test.com");
   /* memberbean m1생성 정보 넣음 */
   MemberBean m2 = new MemberBean("ki", "4321", "기성용", "ki@test.com");
   membersList.add(m1);
   /*membersList 추가  */
   membersList.add(m2);
   membersMap.put("membersList", membersList);
   /*memberList키값에 memberList 값을 넣는다.  */
%>    
<c:set var="membersList" value="${membersMap.membersList}" />
<!-- 변수에 값을 저장한다. setAttribute와 같은 역할  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력 창</title>
</head>
<body>
<table border="1" align="center">
<tr align = center bgcolor="#99ccff">
<td width="20%"><b>아이디</b></td>
<td width="20%"><b>비밀번호</b></td>
<td width="20%"><b>이름</b></td>
<td width="20%"><b>이메일</b></td>
</tr>
<tr align="center">
<td>${membersMap.id}</td>
<td>${membersMap.pwd}</td>
<td>${membersMap.name}</td>
<td>${membersMap.email}</td>
</tr>
<tr align="center">
<td>${membersList[0].id}</td>
<td>${membersList[0].pwd}</td>
<td>${membersList[0].name}</td>
<td>${membersList[0].email}</td>
</tr>

</table>
</body>
</html>