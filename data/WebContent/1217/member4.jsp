<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.util.*,Dec14.*"
    isELIgnored="false"%>
      <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
    <%
    request.setCharacterEncoding("UTF-8");
    %>
    
    
<c:set var="id" value="hong" scope="page"/>
<!-- 변수에 값을 저장한다. setAttribute와 같은 역할 
    id에 hong을 저장 -->
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${'홍길동' }" scope="page"/>
<c:set var="age" value="${22}" scope="page"/>
<c:set var="height" value="${177 }" scope="page"/>


<html>
<head>
<meta charset="UTF-8">
<title>조건문 실습</title>
</head>
<body>
<c:if test="${true }">
<h1>항상 참입니다.</h1>
</c:if>
<!--조건문을 수행한다 if와 동일한 역할을 한다.  -->
<c:if test="${11==11 }">
<h1>두 값은 같습니다.</h1>
</c:if>

<c:if test="${11!=31 }">
<h1>두 값은 같지 않습니다.</h1>
</c:if>

<c:if test="${(id=='hong') && (name=='홍길동') }">
<h1>아이디는 ${id}이고, 이름은 ${name}입니다.</h1>
</c:if>

<c:if test="${age==22 }">
<h1>${name }의 나이는 ${age }살입니다.</h1>
</c:if>

<c:if test="${height>160 }">
<h1>${name }의 키는 160보다 큽니다.</h1>
</c:if>

</body>
</html>