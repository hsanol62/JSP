<%@ page language="java" contentType="text/html; charset=UTF-8"
     import="java.util.*"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
     <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!--c:set 변수에 값을 저장한다. 
  JSP의 setAttribute()와 같은 역할
  <c:set var="변수명" 
            value="변수에 넣을 값"
            property="자바빈 객체 or Map 객체 값을 설정할 프로퍼티 명"
            scope="변수 공유 범위" />
            
            변수명 contextPath,  pageContext.request.contextPath 넣는다.
 -->
<c:url var="url1"  value="/1217/member1.jsp"  >
<!-- url 주소를 생성해준다. 1217폴더에 있는 member1 연결 
 <c:url var="생성한 URL이 저장될 변수 명"
            value="생성할 URL" 
            scope="변수 공유 범위" />
-->
  <c:param  name="id" value="hong" />
  <!-- 파라미터 추가
<c:param name="파라미터 명" value="값" />
파라미터 id 명 hong을 저장
 -->
  <c:param  name="pwd" value="1234" />
  <c:param  name="name" value="홍길동" />
  <c:param  name="email" value="hong@test.com" />
</c:url>
<html>
<head>
<meta charset=”UTF-8">
<title> c:url  태그 실습</title>
</head>
<body>
  <%-- <a href='${contextPath }/1217/member1.jsp'>회원정보출력</a> --%>
<a href='${url1}'>회원정보출력</a>
</body>
</html>
