<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*, Dec14.*" pageEncoding="UTF-8"
   isELIgnored="false"%>
   <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
<%
   request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="m1" class="Dec14.MemberBean" />
 <!-- 자바 인스턴스 꺼내거나 자바 인스턴스 새로 만들어 보관소에 저장하는 코드를 생성한다.  -->
<jsp:setProperty name="m1" property="*" />
<!-- 자바 빈의 프로퍼티 값을 설정 ,자바 객체의 setter()를 호출하는 코드를 생성한다. 
setProperty로 파라미터들을 담아옴 -->
<jsp:useBean id="membersList" class="java.util.ArrayList" />
<%
MemberBean m2 = new MemberBean("son", "1234", "손흥민", "son@test.com");
/*m2에 정보 저장  */
/*memberbean 에 정보 추가  */
membersList.add(m1);
membersList.add(m2);
%>
<%-- ${Collection객체이름[index].속성이름 --%>


<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
   <table border=1 align="center">
      <tr align="center" bgcolor="#99cccff">
         <td width="20%"><b>아이디</b></td>
         <td width="20%"><b>비밀번호</b></td>
         <td width="20%"><b>이름</b></td>
         <td width="20%"><b>이메일</b></td>
      </tr>
      <tr align="center">
         <td>${membersList[0].id}</td>
         <td>${membersList[0].pwd}</td>
         <td>${membersList[0].name}</td>
         <td>${membersList[0].email}</td>
      </tr>
      <tr align="center">
         <td>${membersList[1].id}</td>
         <td>${membersList[1].pwd}</td>
         <td>${membersList[1].name}</td>
         <td>${membersList[1].email}</td>
      </tr>
   </table>
   <!--컬렉션 프레임워크의 "ArrayList" 는 객체를 배열하는 배열이기 때문에, 인덱스로 접근한다.  
   getter 메서드를 통해 값을 출력하지 않아도 되므로, 편리-->
</body>
</html>