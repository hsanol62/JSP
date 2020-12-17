<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
      <!--표현언어를 사용하기 위해서는 반드시, 페이지 디렉티브에 isELIgnored="false" 을 선언해야 한다.  -->
<%
   request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="m" class="Dec14.MemberBean"/>
<%--자바 인스턴스 꺼내거나 자바 인스턴스 새로 만들어 보관소에 저장하는 코드를 생성한다. --%>
<jsp:setProperty name="m" property="*" />
<!-- 모든프로퍼티를 적용시키기 위해 property=""적용시키기 -->
<jsp:useBean id="addr" class="Dec14.Address"/>
<jsp:setProperty name="addr" property="city" value="서울" />
<jsp:setProperty name="addr" property="zipcode" value="07654"/>

<%--<jsp:useBean>은 application, session, request, page 보관소에 저장된 자바 객체를 꺼낼 수 있다.
만약 보관소에 저장된 객체가 없다면 새로 생성하여 해당 보관소에 저장한다.--%>
<%--자바 빈의 프로퍼티 값을 설정 ,자바 객체의 setter()를 호출하는 코드를 생성한다. --%>
<%--Address 빈을 생성한 후 도시(city)와 우편번호(zipcode)를 설정한다--%>
<%
 m.setAddr(addr);
%>
<%-- %{부모빈이름.자식속성이름.속성이름} --%>

<html><head><meta charset="UTF-8">
<title>회원 정보 출력창</title></head>
<body>
<table align=center border="1">
    <tr align="center" bgcolor="#99ccff">
       <td width="7%"><b>아이디</b></td>
       <td width="7%"><b>비밀번호</b></td>
       <td width="5%" ><b>이름</b></td>
       <td width="5%"><b>이메일</b></td>
       <td width="5%"><b>도시</b></td>
       <td width="5%"><b>우편번호</b></td>
    </tr>
    <tr align="center">
        <td>${m.id }</td>
        <td>${m.pwd }</td>
        <td>${m.name }</td>
        <td>${m.email }</td>
        <td><%=m.getAddr().getCity()%></td>
        <td><%=m.getAddr().getZipcode()%></td>
        <%--속성들의 getter를 두번 호출해서 주소를 출력 --%>
    </tr>
    <tr align="center">
      <td>${m.id }   </td>
      <td>${m.pwd }   </td>
      <td>${m.name }   </td>
      <td>${m.email }   </td>

      <%--자바 빈의 속성 이름과 .(마침표)연산자를 이용해 주소를 출력한다. --%>
      <td>${m.addr.city }</td>
      <td>${m.addr.zipcode }</td>
    </tr>
</table>
</body>
</html>