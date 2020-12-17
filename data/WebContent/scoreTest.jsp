<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    request.setCharacterEncoding("utf-8");
    int score = Integer.parseInt(request.getParameter("score"));
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>안녕하세요 스터디 중심 JSP 시작입니다</h1><br>
<%--     <%@ include file="duke_image.jsp" %><br> 하면 오류남 --%>

    <h1>안녕하세요 스터디 중심 JSP 끝입니다</h1>
    <h1>시험 점수 <%=score %>점</h1><br>
    <%
    if(score >= 90){
    %>
    <h1>A학점 입니다</h1>
    <%
    }else if(score >= 80 && score < 90){
    %>
    <h1>B학점 입니다</h1>
    <%
    }else if(score >= 70 && score < 80){
    %>
    <h1>C학점 입니다</h1>
    <%
    }else if(score >= 60 && score < 70){
    %>
    <h1>D학점 입니다</h1>
    <%
    }else{
    %>
    <h1>F학점 입니다</h1>
    <%
    }
    %>
    <br>
    <a href="scoreTest.html">시험 점수 입력하러 가기</a>
</body>
</html>
