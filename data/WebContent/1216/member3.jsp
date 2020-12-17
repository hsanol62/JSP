<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.util.*" import="Dec14.*"%>
   <%
    request.setCharacterEncoding("UTF-8");
 %>
 
 <jsp:useBean id="m" class ="Dec14.MemberBean" scope="page"/>
 <%--자바 인스턴스 꺼내거나 자바 인스턴스 새로 만들어 보관소에 저장하는 코드를 생성한다. --%>
 
 <jsp:setProperty name="m" property="id" value='<%= request.getParameter("id") %>'/>
<!-- 모든프로퍼티를 적용시키기 위해 property=""적용시키기 -->
 <jsp:setProperty name="m" property="pwd" value='<%= request.getParameter("pwd") %>'/>

 <jsp:setProperty name="m" property="name" value='<%= request.getParameter("name") %>'/>

 <jsp:setProperty name="m" property="email" value='<%= request.getParameter("email") %>'/>
 
  <%-- 아래 형식을 위형식으로 변경함 
  아아디/비밀번호/이름/이메일을 셋프로퍼티로 새로 값을 지정해주는것 겟파라미터로  불러온것을 
 <% 
    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    
       
    m.setId(id);
    m.setPwd(pwd);
    m.setName(name);
    m.setEmail(email);
    %> --%>
 <%
    MemberDAO memberDAO = new MemberDAO();
   memberDAO.addMember(m);
   //회원정보를 테이블에 추가합니다.
   /*memberDAO에 정보추가  */
   List membersList = memberDAO.listMembers();
   /* membersList 생성해 memberDAO에 있는 listMembers 를 저장 */
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
  <!--테이블 형식 만드는 부분 위에 -->
  <%
     if(membersList.size()==0){
        /* 멤버 리스트가 비었을때  */
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
           /*bean 불러오는 객체 부분 */
        
  %>
     <tr align ="center">
        <td><%=bean.getId() %></td>
        <!--bean에 있는 id 객체 불러옴  -->
        <td><%=bean.getPwd() %></td>
        <td><%=bean.getName() %></td>
        <td><%=bean.getEmail() %></td>
        <td><%=bean.getJoinDate() %></td>
        </tr>
        <!-- bean 데이터를 테이블 형식으로 출력 -->
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