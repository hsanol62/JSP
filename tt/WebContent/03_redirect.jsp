<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
�ε�� Ȩ�������� �̵�
<% 
response.sendRedirect("http://www.readbook.co.kr");
%>
</body>
</html>
<!--  ������ ���
�ϳ��� �������� �ٸ� �����̳� JSP �� �����ϴ� ���
������ ����� �뵵
��û(request) �� ���� �߰� �۾��� �ٸ� �������� �����ϰ���
��û(request)�� ���Ե� ������ �ٸ� �����̳� JSP �� ������
��û�� ������ ���Խ��� �ٸ� ������ ������ �� ����
��2 ���� �� �������� JSP�� �����͸� �����ϴµ� ����
������ ������ ���
redirect ���
HttpServletResponse ��ü�� sendRedirecr() �޼��带 �̿�
�� �������� ���û�ϴ� ��� 
����:response.addHeader("Refresh",����ð�(��);url=��û�� ���� �Ǵ� JSP")

location ���
�ڹٽ�ũ��Ʈ location ��ü�� href �Ӽ� �̿�
�ڹٽ�ũ��Ʈ���� ���û�ϴ� ���
����: location.href ='��û�� ���� �Ǵ� JSP';
dispatch ���
�Ϲ������� ������ ����� ��Ī
������ ���� ��û�ϴ� ���
RequestDispatcher Ŭ������ forward()�޼��带 �̿�
����: RequestDispatcher
	dis= request.getRequestFispatcher("�������� ���� �Ǵ� JSP");
	dis.forward(request,response);
	
	redirect ����� ������ ��û�� Ŭ���̾�Ʈ�� �� �������� �ٽ� ���� ��û�Ǵ� ���
	Ŭ���̾�Ʈ�� �� ���������� ù ��° ������ ��û
	ù��° ������ sendRedirect() �޼��带 �̿���
	�� ��° ������ �� �������� ���ؼ� ��û
	�� �������� sendRedirect() �޼��尡 ������ �ι�° ������ �ٽ� ��û-->