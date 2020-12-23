<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
 isELIgnored="false"  %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSON 테스트</title>
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>
     $(function() {
         $("#checkJson").click(function() {
        	//members 배열에 회원정보를 객체의 name/value 로 저장
	        var jsonStr = '{"name":"박지성","age":25,"gender":"남자","nickname":"날센돌이"}';
	        //jsonStr에 값 저장
	        var jsonObj = JSON.parse(jsonStr);
	     // JSON 형식의 문자열을 자바스크립트 객체로 변환  jsonStr을 jsonObj로 저장
	      	var output ="회원 정보<br>";
	      	output += "=======<br>";
	      	output += "이름: " + jsonObj.name+"<br>";
	       	output += "나이: " + jsonObj.age+"<br>";
	      	output += "성별: " + jsonObj.gender+"<br>";
	      	output += "별명: " + jsonObj.nickname+"<br>"; 
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
