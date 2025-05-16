<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.LoginBean" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<%=request.getContextPath()%>/css/style.css"
		rel="stylesheet" />
	<title>Chapter06_03</title>
</head>
<body>
	<h2>ログイン完了</h2>
	<% LoginBean login = (LoginBean) request.getAttribute("LoginBean");%>
	<p>名前：<%= login.getName() %></p>
	<br/>
	<form action="<%=request.getContextPath()%>/Chapter06_04" method="post">
		<input type="submit" value="ログアウト" />
	</form>
</body>
</html>