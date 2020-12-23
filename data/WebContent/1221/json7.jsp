<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<!DOCTYPE html>
<html>
<head>
  <title>JSON 테스트</title>
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>
     $(function() {
        $("#checkJson").click(function() {
    	  $.ajax({
            type:"post",
          //post 타입으로 
            async:false, 
          //async를 false로 설정하면 함수의 다음 명령문을 호출하기 전에 호출중인 명령문이 완료되어야합니다. 
            //async : true로 설정하면 해당 명령문이 실행을 시작하고 비동기 명령문이 아직 완료되었는지 여부에 관계없이 다음 명령문이 호출
            url:"${contextPath}/json3",
            //url 경로설정
            success:function (data,textStatus){
            	  // 통신이 성공적으로 이루어졌을 때 이 함수를 탐
               var jsonInfo = JSON.parse(data);
        	   // JSON 형식의 문자열을 자바스크립트 객체로 변환  jsonStr을 jsonObj로 저장
             var memberInfo ="회원 정보<br>";
	     memberInfo += "=======<br>";
	     for(var i in jsonInfo.members){
	    	// 배열이름 members로 회원 정보를 출력.
	       memberInfo += "이름: " + jsonInfo.members[i].name+"<br>";
	       memberInfo += "나이: " + jsonInfo.members[i].age+"<br>";
	       memberInfo += "성별: " + jsonInfo.members[i].gender+"<br>";
	       memberInfo += "별명: " + jsonInfo.members[i].nickname+"<br><br><br>";
	     }
	        	
	     var booksInfo = "<br><br><br>도서 정보<br>";
	     booksInfo += "===========<br>";
	     for(var i in jsonInfo.books) {
	    	// 배열이름 members로 책 정보를 출력.
	        booksInfo += "제목: " + jsonInfo.books[i].title+"<br>";
	        booksInfo += "저자: " + jsonInfo.books[i].writer+"<br>";
	        booksInfo += "가격: " + jsonInfo.books[i].price+"원 <br>";
	        booksInfo += "장르: " + jsonInfo.books[i].genre+"<br>";
            imageURL = jsonInfo.books[i].image;
            booksInfo += "<img src="+imageURL+" />"+"<br><br><br>";
	     }
	     $("#output").html(memberInfo+"<br>"+booksInfo);
	   },
	   error:function(data,textStatus){
	      alert("에러가 발생했습니다.");
	      //에러일떄
	   }
	  });  //end ajax	
        });
     });
</script>
</head>
<body>
   <a id="checkJson" style="cursor:pointer">데이터 수신하기</a><br><br>
    <div id="output"></div>
</body>
</html>