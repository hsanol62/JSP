<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"  %>

<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSON 테스트</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(function() {
	 $("#checkJson").click(function() {	      
	    var jsonStr = '{"members":[{"name":"박지성","age":"25","gender":"남자","nickname":"날센돌이"}'
	    	           +', {"name":"손흥민","age":"30","gender":"남자","nickname":"탱크"}] }';
	    	           //jsonStr에 값 저장 여러개 저장
	    var jsonInfo = JSON.parse(jsonStr);
	    // JSON 형식의 문자열을 자바스크립트 객체로 변환  jsonStr을 jsonObj로 저장
	    var output ="회원 정보<br>";
	    output += "=======<br>";
	    for(var i in jsonInfo.members){
               output += "이름: " + jsonInfo.members[i].name+"<br>";
               //jsaonInfo의 members 값 출력
	       output += "나이: " + jsonInfo.members[i].age+"<br>";
	       output += "성별: " + jsonInfo.members[i].gender+"<br>";
	       output += "별명: " +jsonInfo.members[i].nickname+"<br><br><br>";
	    }
	    $("#output").html(output);
	 });
     });
</script>
</head>
<body>
    <a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <div id="output"></div>
</body>
</html>
