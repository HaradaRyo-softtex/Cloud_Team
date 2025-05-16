<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.Dog" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<%=request.getContextPath()%>/css/style.css"
		rel="stylesheet" />
	<title>Chapter06_02</title>
</head>
<body>
	<h2>入力された犬の情報</h2>
	<%
	Dog dog = (Dog) request.getAttribute("dog");
	%>
	<ul>
		<li>名前：<%= dog.getDogName() %></li>
		<li>年齢：<%= dog.getAge() %> 歳</li>
		<li>体重：<%= dog.getWeight() %> kg</li>
	</ul>
</body>
</html>