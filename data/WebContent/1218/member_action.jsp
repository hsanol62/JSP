<%@ page language="java" contentType="text/html; charset=UTF-8"
    import ="java.util.*,Dec14.*"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
      <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <!-- fmt 포멧은 지역, 메시지 형식, 숫자 및 날짜형식 등 표시할 때 사용-->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <!-- common.jsp에 커스텀 액션을 사용하겠다 정의 선언  -->
       <%
       request.setCharacterEncoding("utf-8");
       %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="m" class="Dec14.MemberBean" />
<!-- 
jsp:useBean :데이터를 표현하는 것을 목적으로하는 클래스
데이터를 저장하는 필드, 데이터를 읽어올 때 사용하는 메서드, 값을 저장할 때 사용하는 메서드로 구성
 -->
<jsp:setProperty name="m" property="*"/>
<!-- setProperty 액션태그는 useBean 액션태그로 생성한 자바빈 객체의 속성값을 설정하는 태그
 m,set* 호출-->
<%
MemberDAO memDAO =new MemberDAO();
/* MemberDAO 객체 memberDAO 변수 생성*/
memDAO.addMember(m);
List membersList = memDAO.listMembers();
/* list 객체 membersList 생성 */
request.setAttribute("membersList", membersList);
/* membersList에 membersList 호출 저장 */
%>
<title>JSTL 다국어 기능</title>
</head>
<body>
<jsp:forward page="membersList.jsp" />
<!-- <jsp:forward> 액션태그는 하나의 JSP 페이지에서 다른 JSP페이지로 요청 처리를 전달할때 사용 -->
</body>
</html>
