<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
b{
font-size:16pt;
}</style>
</head>
<body>
<h2>설문 조사 결과</h2>
<%
   request.setCharacterEncoding("UTF-8");
// 폼 태그에서 입력한 값을 전송할 때는 GET방식과 POST방식으로 나뉘는데 POST방식으로
// 보내는 값이 한글일 경우 깨지지 않게 전달하기 위해 사용
String name = request.getParameter("name");
// 하나의 파라미터가 하나의 값을 가질 때
// String name = name의 파라미터를 받아옴
out.println("이름 : <b>" + name + "</b><br>");
String gender = request.getParameter("gender");
out.println("성별 : ");
if (gender.equals("male")) {
   out.println("<b>남자</b><br>");
} else {
   out.println("<b>여자</b><br>");
}
String seasonArr[] = request.getParameterValues("season");
out.println("당신이 좋아하는 계절은 ");
for (String season : seasonArr) {
   int n = Integer.parseInt(season);
   switch (n) {
   case 1:
      out.println("<b> 봄 </b>입니다.");
      break;
   case 2:
      out.println("<b> 여름 </b>입니다.");
      break;
   case 3:
      out.println("<b> 가을 </b>입니다.");
      break;
   case 4:
      out.println("<b> 겨울 </b>입니다.");
      break;
   }
}
%>
<br>
<b><a href='javascript:history.go(-1)'></a></b>
</body>
</html>