<%@ page language="java" contentType="text/html; charsetUTF-8"
    pageEncoding="UTF-8"%>
 
<%
int age = Integer.parseInt(request.getParameter("age"));
 /* 숫자형의 문자열을 Interger 형으로 반환해줌  
*/

 if ( age <= 19){
 /* 나이가 19세 이하이면 Script로 넘어감  */  
%>    
<script type="text/javascript">  
alert("19세 미만으로 입장 불가능")  
history.go(-1) 
 /* 이전 페이지로 */  
</script>   
 <% } else { 
request.setAttribute("name","홍길동"); 
/*request.setParameter() 와 request.getParameter()를 이용하면 String의 값 밖에는 주고 받을 수 없다. 그럼 Action으로 넘어온 값을 변경시킨후 JSP 페이지로 넘겨주기 위해서는  request.setAttribute() 를 써서 넘겨주고 JSP 페이지에서는 request.getAttribute()를 써서 받아야한다.
 */ 
RequestDispatcher dispatcher 
//sendRedirect를 이용한 페이지 전환 예제   
=request.getRequestDispatcher("05_forwardResult.jsp"); dispatcher.forward(request,response); 
 /*RequestDispatcher는 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나, 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스입니다. 즉 /a.jsp 로 들어온 요청을 /a.jsp 내에서 RequestDispatcher를 사용하여b.jsp로 요청을 보낼 수 있습니다. 또는 a.jsp에서 b.jsp로 처리를 요청하고 b.jsp에서 처리한 결과 내용을a.jsp의 결과에 포함시킬 수 있습니다.   */ 
}  %>   
