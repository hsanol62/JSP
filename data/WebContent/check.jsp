<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ page import="java.sql.*, java.util.*"
%><html>
<head>
<title>JSP Oracle, JDBC 예제</title>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
</head>
<body>
<%
 Connection conn = null;
//DB 접속 변수

 try {
 String DB_HOST = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//jdbc:oracle:thin:@127.0.0.1:1521:myoracle 코드의 마지막 myorcle은 위에서 xe로 설정하였을경우에는
//xe로 해준다 // 아이피, 포트, 서비스(SID) 정보를 수정
 String DB_USER = "scott";
 // 아이디(유저) - 11g 이상시 대소문자 구분 확인
 String DB_PASS = "tiger";
 // 패스워 드- 11g 이상시 대소문자 구분 확인

 Class.forName("oracle.jdbc.driver.OracleDriver");
 //내 DB 드라이브

 conn = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
 //DB 접속 변수에 DB 경로 및 SID 와 아이디 ,비밀번호를 넣음

 out.println("연결성공!");
 //성공시에 연결성공
 } catch (ClassNotFoundException e) {
 //클래스가 없으면 나옴
 out.println("연결 드라이버 없음");
 } catch (SQLException e) {
 // 연결 실패시 나옴
 out.print("연결실패 : ");
 if(e.getMessage().indexOf("ORA-28009") > -1)
 //오라클 권한이 없으면 뜨는 에러가 뜰시
 out.println("허용되지 않는 접속 권한 없음");
 else if(e.getMessage().indexOf("ORA-01017") > -1)
//오라클 유저/ 패스워드가 안맞을때 나오는 에러가 뜰 시
 out.println("유저/패스워드 확인"); 
 else if(e.getMessage().indexOf("IO") > -1)
 // IO 연결이 안되는 에러가 뜰시
 out.println("IO - 연결확인!");
 else
 // 그 외의 에러가 뜰 시
 out.println("기본 연결확인!");

 } finally {
 if(conn != null)
 //conn이 null이면 닫음
 conn.close();
 }
%></body>
</html>