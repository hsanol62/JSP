<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!--  reponse 객체 메소드
		getCharacterEncoding() : 응답할 때 문자 인코딩을 얻어올때
		addCookie(Cookie): 쿠키를 지정할 때
		sendRedirect(URL) 이동하고자 하는 URL 지정할 떄
		 -->
		 <%! int age; %>
		 <% 
		 String str = request.getParameter("age");
		 age = Integer.parseInt(str);
		 
		 if(age >=19) {
		 response.sendRedirect("a.jsp?age="+age);
		 }else{
		 response.sendRedirect("b.jsp?age="+age);
		 }
		 %>
</body>
</html>