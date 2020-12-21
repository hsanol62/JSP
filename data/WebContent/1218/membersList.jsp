<%@ page language="java" contentType="text/html; charset=UTF-8"
    import ="java.util.*,Dec14.*"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
  <!-- fmt 포멧은 지역, 메시지 형식, 숫자 및 날짜형식 등 표시할 때 사용
 -->
 <%
 request.setCharacterEncoding("UTF-8");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
<table align="center" border="1">
<tr align="center" bgcolor="lightgreen">
<td width="7%"><b>아이디</b></td>
<td width="7%"><b>비밀번호</b></td>
<td width="7%"><b>이름</b></td>
<td width="7%"><b>이메일</b></td>
<td width="7%"><b>가입일</b></td>
</tr>
<c:choose>

<%--  Java 언어의 switch~ case와 비슷하게 여러 조건중에 하나를 선택한다. 
<c:choose>는 switch에 해당되고 
<c:when>는 case에 해당되며, 
<c:otherwise>는 default 

ArrayList list = request.getAttribute("membersList");
 --%>
 
 <c:when test="${membersList==null }">
 <!-- membersList 가 없을 때 출력 -->
 <tr>
 <td colspan=5>
 <b>등록된 회원이 없습니다.</b>
 </td>
 </tr>
 </c:when>
 <c:when test="${membersList!=null}">
 <!-- membersList가 있을때  -->
 <c:forEach var="mem" items="${membersList}">
 <!-- List, 배열 요소를 순서대로 반복해서 처리할 수 있는 태그
 mem에 membersList를 넣는다. -->
 <tr align="center">
 <td>${mem.id}</td>
 <td>${mem.pwd}</td>
 <td>${mem.name}</td>
 <td>${mem.email}</td>
 <td>${mem.joinDate}</td>
 </tr>
 </c:forEach>
 </c:when>
</c:choose>
</table>
</body>
</html>