<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"  %>
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
             var jsonStr = '{"age": [22, 33, 44]}';  
             // JSON 형식의 문자열
             var jsonInfo = JSON.parse(jsonStr);
             //parse 메소드로 JSON 데이터를 가져옴
             var output ="회원 나이<br>";
             output += "=======<br>";
        
             for(var i in jsonInfo.age) {
            	//배열 요소에서 jsonInfo name으로 value를 출력
                output += jsonInfo.age[i]+"<br>";
                //jsonInfo 출력
             }
             $("#output").html(output);
        });
     });
</script>
</head>
<body>
    <a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <!-- 커서 모양 바꿈  -->
    <div id="output"></div>
</body>
</html>
