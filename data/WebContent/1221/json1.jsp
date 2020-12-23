<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

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
        var jsonStr  = '{"name": ["홍길동", "이순신", "임꺽정"] }';     
        // JSON 형식의 문자열
        var jsonInfo = JSON.parse(jsonStr);
        // JSON 형식의 문자열을 자바스크립트 객체로 변환
        //parse 메소드로 JSON 데이터를 가져옴 / jsonStr을 jsonInfo로 저장
        var output ="회원 이름<br>";
        //출력
        output += "=======<br>";
        //출력
        for(var i in jsonInfo.name) {
        	//배열 요소에서 jsonInfo name으로 value를 출력
            output += jsonInfo.name[i]+"<br>";
        }
        $("#output").html(output);
        //output 출력
      });
    });
        
</script>
  </head>
  <body>
    <a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <div id="output"></div>
  </body>
</html>
