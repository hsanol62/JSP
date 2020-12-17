<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*"
	import="Dec14.*"

    pageEncoding="UTF-8"
%>
 <%
     request.setCharacterEncoding("UTF-8");
 %>
 <jsp:useBean id="m" class="1215.MemberBean" scope="page"/>
 <jsp:setProperty name="m" property="id" param="id"/>
 <jsp:setProperty name="m" property="pwd" param="pwd"/>
 <jsp:setProperty name="m" property="name" param="name"/>
 <jsp:setProperty name="m" property="email" param="email"/>
 <!-- 회원가입 창에서 전달된 매개변수 이름과 속성이름이 같으면 같은 이름으로 한다.  -->
 <%
   /*  String id = request.getParameter("id");
     String pwd = request.getParameter("pwd");
     String name = request.getParameter("name");
     String email = request.getParameter("email");

m.setId(id);
m.setPwd(pwd);
m.setName(name);
m.setEmail(email); 
    */
    
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
        <td width="5%">가입일</td>
  </tr>
  <%
      if(membersList.size()==0){
  %>
      <tr>
          <td colspan ="5">
              <p align = "center"><b><span style="font-szie:9pt;">
                          등록된 회원이 없습니다.</span></b></p>
         </td>
     </tr>

  <%
      } else{
          for(int i=0; i<membersList.size();i++){
              MemberBean bean = (MemberBean) membersList.get(i);

  %>
      <tr align ="center">
          <td><%=bean.getId() %></td>
          <td><%=bean.getPwd() %></td>
          <td><%=bean.getName() %></td>
          <td><%=bean.getEmail() %></td>
          <td><%=bean.getJoinDate() %></td>
          </tr>
  <%
    } //end for
  }//end if
  %>
  <tr height = "1" bgcolor="#99ccff">
      <td colspan="5"></td>
      </tr>
  </table>
</body>
</html>