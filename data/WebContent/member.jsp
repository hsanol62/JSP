<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*"
	import="Dec14.*"

    pageEncoding="UTF-8"
%>
	<%--  import로 불러옴 java들--%>
<!DOCTYPE html>
<html>
<head>
	<style>
		h1 {
			text-align: center;
		}
		/*센터로 정렬  */
	</style>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>회원 정보 출력</h1>
<%
	request.setCharacterEncoding("utf-8");
	String _name = request.getParameter("name");
	/* name 불러와 String name 으로 저장 */
	MemberVO memberVO = new MemberVO();
	//MemberVo 객체 생성
	memberVO.setName(_name);
	MemberDAO dao = new MemberDAO();
	//memberDAO를 dao를 
	List membersList = dao.listMembers(memberVO);
	// 조회조건에 해당되는 회원정보를 조회.
	/*memberVo 의 listMembers를 membersList에 저장  */
%>
	<table border=1 width=800 align=center>
		<tr align=center bgcolor="#FFFF66">
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>이메일</td>
		<td>가입일자</td>
		</tr>
<%
	// 데이터 루프 ( 출력 부분 )
	for(int i=0; i < membersList.size(); i++){
		MemberVO vo = (MemberVO) membersList.get(i);
		String id = vo.getId();
		String pwd = vo.getPwd();
		String name = vo.getName();
		String email = vo.getEmail();
		Date joinDate = vo.getJoinDate();
%>
	<tr align=center>
		<td><%= id %></td>
		<td><%= pwd %></td>
		<td><%= name %></td>
		<td><%= email %></td>
		<td><%= joinDate %></td>
	</tr>
<%
	}
%>
	</table>
</body>
</html>