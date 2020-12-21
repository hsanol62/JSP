<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
    <%
    request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 다운로드 요청하기</title>
</head>
<body>
<form method="post" action="result.jsp">
<!-- result.jsp 연결 -->
<input type=hidden name="param1" value="01.png"/> <br>
<!-- input type=“hidden”은 사용자에게는 보이지 않는 숨겨진 입력 필드를 정의
숨겨진 입력 필드는 폼 제출 시 사용자가 변경해서는 안 되는 데이터를 함께 보낼 때 유용하게 사용됩 -->
<input type=hidden name="param2" value="02.jpg"/> <br>
<input type="submit" value="이미지 다운로드">
</form>
</body>
</html>