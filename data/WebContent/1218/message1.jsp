<%@ page language="java" contentType="text/html; charset=UTF-8"
    import ="java.util.*,Dec14.*"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
     
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
<%
request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 다국어 기능</title>
</head>
<body>
<fmt:setLocale value="en_US" />
<%-- <fmt:setLocale value="ko_KR" /> 
<fmt:setLocale value="언어코드_국가코드"/>
날짜와 시간 수치를 표기하는 방법은 국가별로 다를 수 있기 때문에 국가별로 다르게 포맷을 설정

--%>

<h1>
회원정보<br><br>
<fmt:bundle basename="resources.member">
이름:<fmt:message key="mem.name" /><br>

주소:<fmt:message key="mem.address" /><br>
직업:<fmt:message key="mem.job" /><br>
</fmt:bundle>
</h1>
</body>
</html>
