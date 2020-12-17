<%@ page language="java" contentType="text/html; charset=UTF-8"
     import="java.util.*"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  List dataList=new ArrayList();
/* list dataList를 저장 */
  dataList.add("hello");
  dataList.add("world");
  dataList.add("안녕하세요!!");
%>
<c:set  var="list"  value="<%=dataList  %>" />
<html>
<head>
<meta charset=”UTF-8">
<title>반복문 실습</title>
</head>
<body>
   <c:forEach  var="i" begin="1" end="10" step="1"  varStatus="loop">
   <!--  배열(Array)나 컬렉션(collection) 또는 맵(map)등과 같은 집합체에 저장되어 있는 값들을 순차적으로 처리할때 사용할 수 있는 태그 
   범위 안에 반복문 수행 for와 동일한 역할 
   begin:1 시작값 end:10마지막값 step:1 증가단위 
   i라는 변수를 사용하여 1부터 10까지 1씩 증가하며 반복문 수행-->
    i=  ${i}   &nbsp;&nbsp;&nbsp;  반복횟수: ${loop.count} <br>
   </c:forEach>
 <br>
   <c:forEach  var="i" begin="1" end="10" step="2" >
   <!--1부터 2씩증가해 10이하까지 1,3,5,7,9  -->
        5 * ${i} = ${5*i}<br>
   </c:forEach>
<br>
   <c:forEach  var="data" items="${list}" >
   <!-- list에 저장했던 값을 불러온다. -->
       ${data } <br> 
   </c:forEach>
<br>
<c:set var="fruits" value="사과, 파인애플, 바나나, 망고, 귤"  />
<c:forTokens  var="token" items="${fruits}" delims="," >
<%-- java.util.StringTokenizer
문자열 구분자로 분리, 하나씩 추출
 <c:forTokens var=”토큰을 저장할 변수” items”토큰으로 나눌 문자열” delims=”구분자”
 과일을 나눈다. ,을 구분으로해서> --%>
    ${token} <br> 
</c:forTokens>
</body>
</html>
