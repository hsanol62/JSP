<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����</title>
</head>
<body>
	<form method = "get" action="MethodServlet">
	 //�����͸� �����ϱ� ���� ���۹�ư
		<input type = "submit" value="get ������� ȣ���ϱ�">
		</form>
		<br>
		<br>
		<form method= "post" action="MethodServlet">
		<input type = "submit" value="post ������� ȣ���ϱ�">
		<input type = "reset" value="���">
		</form>
</body>
</html>