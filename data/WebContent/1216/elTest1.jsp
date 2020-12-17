<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>


<html><head><meta charset="UTF-8">
<title>표현 언어에서 사용되는 데이터들 </title></head>
<body>
    <h1>표현 언어로 여러가지 데이터 출력하기 </h1> <h1>
    ${100} : ${100} <br>
    %{"안녕하세요}" : ${"안녕하세요"}<br>
    ${10+1} : ${10+1}<br>
    {"10"+1} : ${"10"+1}<br>

<%-- ${null + 10 } : %{null + 10} <br> --%>
<%-- ${"안녕" + 11 } : %{"안녕"  + 11} <br> --%>
<%-- ${"hello" + "world" } : %{"hello" + "world"} <br> --%>
<!-- 문자열끼리 결합을 못함 문자열과 숫자도 결합 X -->


    </h1>
</body>



</html>
<%-- 표현 언어의 특징
기존 표현식보다 편리하게 값을 출력
변수와 여러가지 연산자를 포함할 수 있음
JSP 내장 객체에 저장된 속성 및 자바의 빈 속성도 표현 언어에서 출력할 수 있음
표현언어 자체 내장 객체도 제공됨
JSP 페이지 생성 시 기본 설정은 표현 언어를 사용할 수 없음
페이지 디렉티브 태그에서는 반드시 isELIgnored = false로 설정해야함
${표현식 or 값}
--%>