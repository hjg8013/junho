<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% System.out.println("abcd"); %>
</head>
<body>
<!-- 이걸 추가하면 파일을 추가해서 사용할수 있다 <%@ include file="header.jsp" %> 코드가 전체가 들어오기때문에 코드에서 닫는것은 지워야한다-->
<%@ include file="header.jsp" %>
index.jsp<br>
<a href="sample/member?id=abcd&pw=1234&name=정자바">회원가입</a>
<form action="sample/memberDTO" method="post">
	<div>id:<input type="text" name="id" value="abcd"></div>
	<div>pw:<input type="password" name="pw" value="1234"></div>
	<div>name:<input type="text" name="name" value="정자바"></div>
	<input type="submit" value="회원가입">
</form>

<!-- name = String로 만든것이고 value는 변수에 값을 준것이다 
	서버주소를 어떤식으로 적느냐에따라 가는 방향을 정해줄수 있다
	name와 변수의 이름이 같으면 자동으로 데이터를 넣어준다 만약 이름이 다르면 안된다
-->
</body>
</html>