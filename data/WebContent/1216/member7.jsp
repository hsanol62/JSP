<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.util.*" import="Dec14.*"%>
   <%
    request.setCharacterEncoding("UTF-8");
 %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록창</title>
</head>
<body>
  <table width='100%' align='center'>
      <tr align='center' bgcolor = '#99ccff'>
        <td width="7%">아이디</td>
        <td width="7%">비밀번호</td>
        <td width="5%">이름</td>
        <td width="11%">이메일</td>

  </tr>
           <tr align = "center">
           <!-- 회원 가입창에서 전달받은 회원 정보를 setProperty 액션 태그 이용해 useBean에 저장 jsp:getProperty 을 사용해 useBean 속성에 접근하여 값 출력 -->
               <td> <jsp:getProperty property="id" name="m"/></td>
               <td> <jsp:getProperty property="pwd" name="m"/></td>
               <td> <jsp:getProperty property="name" name="m"/></td>
               <td> <jsp:getProperty property="email" name="m"/></td>
       
    </tr>
    <tr height = "1" bgcolor=#99ccff">
    <td colspan="5"></td>
</tr>
</table>

</body>
</html>