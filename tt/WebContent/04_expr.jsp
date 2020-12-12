<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%!int global_cnt =0; %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!String str = "안녕하세요!";
    int a = 5, b = -5;
    public int abs(int n){
    if (n<0){
    n = -n;
    }
    return n;
    }%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=str %><br>
<%=a%>
의 절대값:
<%=abs(a)%><br>
 <!--abs 는 입력받은 숫자의 부호를 제거하고 그 결과를 리턴해주는 함수입니다.-->
<%=b %>
의 절대값 :
<%= abs(b) %><br>
<!--  간단한 인삿말 출력하기 -->
<%-- 인삿말을 String 변수에 저장한다. --%>
<% String name="Angel";%>
Hello <%= name %><br>

<%
Calendar date=Calendar.getInstance();
//getInstance()는 Calendar 클래스를 구현한 클래스의 인스턴스를 반환

SimpleDateFormat today=new SimpleDateFormat("yyyy년 MM월 dd일");
SimpleDateFormat now=new SimpleDateFormat("hh시 mm분 ss초");
%>
오늘은 <b> <%= today.format(date.getTime()) %></b>입니다. <br>
지금 시작은 <b> <%= now.format(date.getTime()) %></b> 입니다.

<%
int local_cnt=0;
out.print("<br> local_cnt : ");
out.print(++local_cnt);
out.print("<br> global_cnt : ");
out.print(++global_cnt);
%><br>
<%
int count=0;
out.print("count : ");
out.println(++count);
%>
</body>
</html>