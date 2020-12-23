<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, Dec21.*"
    isELIgnored="false"%>
 <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
    <%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
      <!-- fmt 포멧은 지역, 메시지 형식, 숫자 및 날짜형식 등 표시할 때 사용
 -->
    <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
     <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
    <%
    request.setCharacterEncoding("utf-8");
    %>
    <!-- 
    컨트롤러는 MemberDAO객체르 초기화하고 
    MemberDAO의 listMember() 메서드를 호출하여 
    회원 정보를 ArrayList로 반환한다. 
   RequestDispatch 클래스로 JSP로 포워딩한다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    .cls1{
        font-size:40px;
        text-align:center;
    }
    .cls2{
        font-size:20px;
        text-align:center;
    }
</style>
<title>회원 정보 출력</title>
</head>
<body>
    <p class="cls1">회원정보</p>
    <table align="center" border="1">
        <tr align="center" bgcolor="lightgreen">
            <td width="7%"><b>아이디</b></td>
            <td width="7%"><b>비밀번호</b></td>
            <td width="7%"><b>이름</b></td>
            <td width="7%"><b>이메일</b></td>
            <td width="7%"><b>가입일</b></td>
        </tr>

        <c:choose>
            <c:when test="${membersList==null}">
                <tr>
                    <td colspan="5">
                        <b>등록된 회원이 없습니다</b>
                    </td>
                </tr>
            </c:when>
            <c:when test="${membersList!=null}">
                <c:forEach var="mem" items="${membersList}">
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
    <a href="#"><p class="cls2">회원 가입하기</p></a>
</body>
</html>