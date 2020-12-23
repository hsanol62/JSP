<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<!DOCTYPE html>
<html><head><title>JSON ajax 연습</title>
 <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
 <script>
    $(function() {
        $("#checkJson").click(function() {
    	   var _jsonInfo ='{"name":"박지성","age":"25","gender":"남자","nickname":"날센돌이"}';
    	   //jsonInfo에 값 저장
    	   $.ajax({
             type:"post",
             //post 타입으로
             async:false, 
             //async를 false로 설정하면 함수의 다음 명령문을 호출하기 전에 호출중인 명령문이 완료되어야합니다. 
             //async : true로 설정하면 해당 명령문이 실행을 시작하고 비동기 명령문이 아직 완료되었는지 여부에 관계없이 다음 명령문이 호출
             url:"${contextPath}/json",
             data : {jsonInfo: _jsonInfo},
             success:function (data,textStatus){
	     },
	     // 통신이 성공적으로 이루어졌을 때 이 함수를 탐
	     error:function(data,textStatus){
	        alert("에러가 발생했습니다.");ㅣ
	        //에러 났을떄
	     },
	     complete:function(data,textStatus){
	     }
	     // 통신이 실패했어도 완료가 되었을 때 이 함수를 탐
	   });  //end ajax	

       });
    });
 </script></head>
<body>
   <a id="checkJson" style="cursor:pointer">전송</a><br><br>
    <div id="output"></div>
</body></html>
